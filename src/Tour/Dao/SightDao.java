package Tour.Dao;

import Tour.connetion.ConnectionPool;
import Tour.entity.Sight;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SightDao {

    private static final SightDao INSTANCE = new SightDao();

    private static final String SAVE =
            "INSERT INTO tour_guide.sight (name, city_id, year_of_construction, category_id, style_id, is_list_unesco, rating_tripadvisor)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String DELETE =
            "DELETE FROM tour_guide.sight WHERE id = ?";

    private static final String FIND_ANY_SIGHT =
            "SELECT" +
                    "  s.id AS sight_id," +
                    "  s.name AS sight_name," +
                    "  s.city_id AS city_id," +
                    "  s.year_of_construction AS year," +
                    "  s.category_id AS category_id," +
                    "  s.style_id AS style_id," +
                    "  s.is_list_unesco AS is_list_unesco," +
                    "  s.rating_tripadvisor AS rating_tripAdvisor " +
                    "FROM tour_guide.sight s" +
                    "  INNER JOIN tour_guide.city C" +
                    "    ON C.id = S.city_id" +
                    "  INNER JOIN tour_guide.country co" +
                    "    ON co.id = C.country_id" +
                    "  INNER JOIN tour_guide.category ca" +
                    "    ON ca.id = S.category_id" +
                    "  INNER JOIN tour_guide.style st" +
                    "    ON st.id = S.style_id " +
                    "WHERE co.id = ? AND C.id = ? AND ca.id = ? AND st.id = ?";

    private static final String FIND_SIGHT_BY_ID =
            "SELECT" +
                    "  s.id AS sight_id," +
                    "  s.name AS sight_name," +
                    "  s.city_id AS city_id," +
                    "  s.year_of_construction AS year," +
                    "  s.category_id AS category_id," +
                    "  s.style_id AS style_id," +
                    "  s.is_list_unesco AS is_list_unesco," +
                    "  s.rating_tripadvisor AS rating_tripAdvisor " +
                    "FROM tour_guide.sight s " +
                    "WHERE s.id = ?";

    public Sight save(Sight sight) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, sight.getName());
            preparedStatement.setLong(2, sight.getCityId());
            preparedStatement.setInt(3, sight.getYearOfBuilding());
            preparedStatement.setLong(4, sight.getCategoryId());
            preparedStatement.setLong(5, sight.getStyleId());
            preparedStatement.setBoolean(6, sight.getIsListUNESCO());
            preparedStatement.setDouble(7, sight.getRatingOfTripAdvisor());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                sight.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sight;
    }

    public void delete(Long id) {
        Connection connection = null;
        PreparedStatement countryStatement = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);

            countryStatement = connection.prepareStatement(DELETE);
            countryStatement.setLong(1, id);
            countryStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (countryStatement != null) {
                    countryStatement.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public List<Sight> findAnySight() {
        List<Sight> sights = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ANY_SIGHT);
            while (resultSet.next()) {
                Sight sight = getSightFromResultSet(resultSet);
                sights.add(sight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sights;
    }

    public Optional<Sight> findById(Long id) {
        Optional<Sight> sight = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_SIGHT_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sight = Optional.of(getSightFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sight;
    }

    private Sight getSightFromResultSet(ResultSet resultSet) throws SQLException {
        return Sight.builder()
                .id(resultSet.getLong("sight_id"))
                .name(resultSet.getString("sight_name"))
                .cityId(resultSet.getLong("city_id"))
                .yearOfBuilding(resultSet.getInt("year_of_construction"))
                .categoryId(resultSet.getInt("category_id"))
                .styleId(resultSet.getInt("style_id"))
                .isListUNESCO(resultSet.getBoolean("is_list_unesco"))
                .ratingOfTripAdvisor(resultSet.getDouble("rating_tripAdvisor"))
                .build();
    }

    public static SightDao getInstance() {
        return INSTANCE;
    }
}

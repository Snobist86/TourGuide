package Tour.Dao;

import Tour.Dto.sight.SearchSightDto;
import Tour.Dto.sight.SightDto;
import Tour.Dto.sight.ViewSightDto;
import Tour.connetion.ConnectionPool;
import Tour.entity.Category;
import Tour.entity.City;
import Tour.entity.Sight;
import Tour.entity.Style;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SightDao {

    private static final SightDao INSTANCE = new SightDao();

    private static final String SAVE =
            "INSERT INTO tour_guide.sight (name, city_id, year_of_construction, category_id, style_id, is_list_unesco, rating_tripadvisor)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_ANY_SIGHT =
            "SELECT" +
                    "  s.id AS sight_id," +
                    "  s.name AS sight_name," +
                    "  s.city_id AS city_id," +
                    "  c.name AS city_name," +
                    "  s.year_of_construction," +
                    "  s.category_id AS category_id," +
                    "  ca.name AS category_name," +
                    "  s.style_id AS style_id," +
                    "  st.name AS style_name," +
                    "  s.is_list_unesco," +
                    "  s.rating_tripadvisor " +
                    "FROM tour_guide.sight s" +
                    "  INNER JOIN tour_guide.city c" +
                    "    ON c.id = s.city_id" +
                    "  INNER JOIN tour_guide.country co" +
                    "    ON co.id = c.country_id" +
                    "  INNER JOIN tour_guide.category ca" +
                    "    ON ca.id = s.category_id" +
                    "  INNER JOIN tour_guide.style st" +
                    "    ON st.id = s.style_id " +
                    "WHERE (? IS NULL OR co.id = ?) AND (? IS NULL OR c.id = ?) AND (? IS NULL OR ca.id = ?) AND (? IS NULL OR st.id = ?)";

    private static final String FIND_SIGHT_BY_ID =
            "SELECT" +
                    "  s.id AS sight_id," +
                    "  s.name AS sight_name," +
                    "  s.city_id AS city_id," +
                    "  c.name AS city_name," +
                    "  s.year_of_construction," +
                    "  s.category_id AS category_id," +
                    "  ca.name AS category_name," +
                    "  s.style_id AS style_id," +
                    "  st.name AS style_name," +
                    "  s.is_list_unesco," +
                    "  s.rating_tripadvisor " +
                    "FROM tour_guide.sight s " +
                    "  INNER JOIN tour_guide.city c" +
                    "    ON c.id = s.city_id" +
                    "  INNER JOIN tour_guide.country co" +
                    "    ON co.id = c.country_id" +
                    "  INNER JOIN tour_guide.category ca" +
                    "    ON ca.id = s.category_id" +
                    "  INNER JOIN tour_guide.style st" +
                    "    ON st.id = s.style_id " +
                    "WHERE s.id = ?";

    private static final String LIST_UNESCO =
            "SELECT" +
                    "  s.id AS sight_id," +
                    "  s.name AS sight_name," +
                    "  s.city_id AS city_id," +
                    "  c.name AS city_name," +
                    "  s.year_of_construction," +
                    "  s.category_id AS category_id," +
                    "  ca.name AS category_name," +
                    "  s.style_id AS style_id," +
                    "  st.name AS style_name," +
                    "  s.is_list_unesco," +
                    "  s.rating_tripadvisor " +
                    "FROM tour_guide.sight s " +
                    "  INNER JOIN tour_guide.city c" +
                    "    ON c.id = s.city_id" +
                    "  INNER JOIN tour_guide.country co" +
                    "    ON co.id = c.country_id" +
                    "  INNER JOIN tour_guide.category ca" +
                    "    ON ca.id = s.category_id" +
                    "  INNER JOIN tour_guide.style st" +
                    "    ON st.id = s.style_id " +
                    "WHERE s.is_list_unesco = 'TRUE'";

    public Sight save(Sight sight) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, sight.getName());
            preparedStatement.setLong(2, sight.getCity().getId());
            preparedStatement.setInt(3, sight.getYearOfBuilding());
            preparedStatement.setLong(4, sight.getCategory().getId());
            preparedStatement.setLong(5, sight.getStyle().getId());
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

    public List<Sight> listUnesco() {
        List<Sight> sights = new LinkedList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LIST_UNESCO)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Sight sight = getSightFromResultSet(resultSet);
                sights.add(sight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sights;
    }


    public List<Sight> findAnySight(SearchSightDto dto) {
        List<Sight> sights = new LinkedList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ANY_SIGHT)) {
            preparedStatement.setObject(1, dto.getCountryId(), Types.BIGINT);
            preparedStatement.setObject(2, dto.getCountryId(), Types.BIGINT);
            preparedStatement.setObject(3, dto.getCityId(), Types.BIGINT);
            preparedStatement.setObject(4, dto.getCityId(), Types.BIGINT);
            preparedStatement.setObject(5, dto.getCategoryId(), Types.BIGINT);
            preparedStatement.setObject(6, dto.getCategoryId(), Types.BIGINT);
            preparedStatement.setObject(7, dto.getStyleId(), Types.BIGINT);
            preparedStatement.setObject(8, dto.getStyleId(), Types.BIGINT);
            ResultSet resultSet = preparedStatement.executeQuery();
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
                .city(City.builder()
                        .id(resultSet.getLong("city_id"))
                        .name(resultSet.getString("city_name"))
                        .build())
                .yearOfBuilding(resultSet.getInt("year_of_construction"))
                .category(Category.builder()
                        .id(resultSet.getInt("category_id"))
                        .name(resultSet.getString("category_name"))
                        .build())
                .style(Style.builder()
                        .id(resultSet.getInt("style_id"))
                        .name(resultSet.getString("style_name"))
                        .build())
                .isListUNESCO(resultSet.getBoolean("is_list_unesco"))
                .ratingOfTripAdvisor(resultSet.getDouble("rating_tripAdvisor"))
                .build();
    }

    public static SightDao getInstance() {
        return INSTANCE;
    }
}

package Tour.Dao;

import Tour.connetion.ConnectionPool;
import Tour.entity.Airport;
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
public final class AirportDao {

    private static final AirportDao INSTANCE = new AirportDao();

    private static final String SAVE =
            "INSERT INTO tour_guide.airport (name, city_id) VALUES (?, ?)";

    private static final String FIND_ANY_AIRPORT =
            "SELECT" +
                    "  a.id   AS airport_id," +
                    "  a.name AS airport_name " +
                    "FROM tour_guide.airport a" +
                    "  INNER JOIN tour_guide.city c" +
                    "    ON c.id = a.city_id" +
                    "  INNER JOIN tour_guide.country co" +
                    "    ON co.id = c.country_id " +
                    "WHERE co.id = ? AND c.id = ?";



    private static final String FIND_AIRPORT_BY_ID =
            "SELECT" +
                    "  a.id   AS airport_id," +
                    "  a.name AS airport_name " +
                    "FROM tour_guide.airport a " +
                    "WHERE a.id = ?";

    private static final String DELETE =
            "DELETE FROM tour_guide.airport WHERE id = ?";

    public Airport save(Airport airport) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, airport.getName());
            preparedStatement.setLong(2, airport.getCityId());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                airport.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return airport;
    }

    public List<Airport> findAnyAirport() {
        List<Airport> airports = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ANY_AIRPORT);
            while (resultSet.next()) {
                Airport airport = getAirportFromResultSet(resultSet);
                airports.add(airport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return airports;
    }

    public Optional<Airport> findById(Long id) {
        Optional<Airport> airport = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_AIRPORT_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                airport = Optional.of(getAirportFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return airport;
    }

    private Airport getAirportFromResultSet(ResultSet resultSet) throws SQLException {
        return Airport.builder()
                .id(resultSet.getLong("airport_id"))
                .name(resultSet.getString("airport_name"))
                .build();
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

    public static AirportDao getInstance() {
        return INSTANCE;
    }
}

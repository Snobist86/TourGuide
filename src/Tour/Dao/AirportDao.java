package Tour.Dao;

import Tour.Dto.airport.FullInfoAirportDto;
import Tour.Dto.airport.SearchAirportDto;
import Tour.connetion.ConnectionPool;
import Tour.entity.Airport;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AirportDao {

    private static final AirportDao INSTANCE = new AirportDao();

    private static final String SAVE =
            "INSERT INTO tour_guide.airport (name, city_id) VALUES (?, ?)";

    private static final String FIND_ANY_AIRPORT =
            "SELECT" +
                    "  a.id AS airport_id," +
                    "  a.name AS airport_name," +
                    "  a.city_id AS city_id," +
                    "  c.name AS city_name " +
                    "FROM tour_guide.airport a" +
                    "  INNER JOIN tour_guide.city c" +
                    "    ON c.id = a.city_id" +
                    "  INNER JOIN tour_guide.country co" +
                    "    ON co.id = c.country_id " +
                    "WHERE (? IS NULL OR co.id = ?) AND (? IS NULL OR c.id = ?)";

//    private static final String FIND_AIRPORT_BY_ID =
//            "SELECT" +
//                    "  a.id   AS airport_id," +
//                    "  a.name AS airport_name " +
//                    "FROM tour_guide.airport a " +
//                    "WHERE a.id = ?";

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

    public List<FullInfoAirportDto> findAnySight(SearchAirportDto dto) {
        List<FullInfoAirportDto> airports = new LinkedList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ANY_AIRPORT)) {
            preparedStatement.setObject(1, dto.getCountryId(), Types.BIGINT);
            preparedStatement.setObject(2, dto.getCountryId(), Types.BIGINT);
            preparedStatement.setObject(3, dto.getCityId(), Types.BIGINT);
            preparedStatement.setObject(4, dto.getCityId(), Types.BIGINT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                FullInfoAirportDto airport = getAirportFromResultSet(resultSet);
                airports.add(airport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return airports;
    }

//    public Optional<Airport> findById(Long id) {
//        Optional<Airport> airport = Optional.empty();
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_AIRPORT_BY_ID)) {
//            preparedStatement.setLong(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                airport = Optional.of(getAirportFromResultSet(resultSet));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return airport;
//    }

    private FullInfoAirportDto getAirportFromResultSet(ResultSet resultSet) throws SQLException {
        return FullInfoAirportDto.builder()
                .id(resultSet.getLong("airport_id"))
                .name(resultSet.getString("airport_name"))
                .city(resultSet.getString("city_name"))
                .build();
    }

    public static AirportDao getInstance() {
        return INSTANCE;
    }
}

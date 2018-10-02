package Tour.Dao;

import Tour.connetion.ConnectionPool;
import Tour.entity.Country;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CountryDao {

    private static final CountryDao INSTANCE = new CountryDao();

    private static final String SAVE =
            "INSERT INTO tour_guide.country (name) VALUES (?)";

    private static final String DELETE =
            "DELETE FROM tour_guide.country WHERE id = ?";

    private static final String FIND_ALL =
            "SELECT id, name FROM tour_guide.country";

    public Country save(Country country) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, country.getName());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                country.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country;
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

    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Country country = Country.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
               countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }

    public static CountryDao getInstance() {
        return INSTANCE;
    }
}

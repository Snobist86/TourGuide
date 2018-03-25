package Tour.Dao;

import Tour.connetion.ConnectionPool;
import Tour.entity.Style;
import Tour.util.ConnectionManager;
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
public final class StyleDao {

    private static final StyleDao INSTANCE = new StyleDao();

    private static final String SAVE =
            "INSERT INTO tour_guide.style (name) VALUES (?)";

    private static final String DELETE =
            "DELETE FROM tour_guide.style WHERE id = ?";

    private static final String FIND_ALL =
            "SELECT id, name FROM tour_guide.style";

    public Style save(Style style) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, style.getName());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                style.setId(generatedKeys.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return style;
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

    public List<Style> findAll() {
        List<Style> styles = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Style style = Style.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
                styles.add(style);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return styles;
    }

    public static StyleDao getInstance() {
        return INSTANCE;
    }
}

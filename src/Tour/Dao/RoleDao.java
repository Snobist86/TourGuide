package Tour.Dao;

import Tour.connetion.ConnectionPool;
import Tour.entity.Role;
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
public final class RoleDao {

    private static final RoleDao INSTANCE = new RoleDao();

    private static final String SAVE =
            "INSERT INTO tour_guide.role (name) VALUES (?)";

    private static final String FIND_ALL =
            "SELECT id, name FROM tour_guide.role";

    public void save(Role role) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, role.getName());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                role.setId(generatedKeys.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Role role = Role.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }

    public static RoleDao getInstance() {
        return INSTANCE;
    }
}

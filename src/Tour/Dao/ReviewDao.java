package Tour.Dao;

import Tour.Dto.review.ViewReviewDto;
import Tour.connetion.ConnectionPool;
import Tour.entity.Review;
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
public final class ReviewDao {

    private static final ReviewDao INSTANCE = new ReviewDao();

    private static final String SAVE =
            "INSERT INTO tour_guide.comment (text, user_id, sight_id) VALUES (?, ?, ?)";

    private static final String SEARCH_BY_SIGHT_ID =
            "SELECT " +
                    "text, " +
                    "sight_id, " +
                    "u.login AS login " +
                    "FROM tour_guide.comment c " +
                    "  INNER JOIN tour_guide.application_user u" +
                    "    ON u.id = c.user_id " +
                    "WHERE c.sight_id = ?";



    public Review save(Review review) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, review.getValue());
            preparedStatement.setLong(2, review.getUserId());
            preparedStatement.setLong(3, review.getSightId());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                review.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return review;
    }

    public List<ViewReviewDto> findBySight(String bySight) {
        List<ViewReviewDto> reviews = new LinkedList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_SIGHT_ID)) {
            preparedStatement.setObject(1, Long.valueOf(bySight));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ViewReviewDto review =  ViewReviewDto.builder()
                        .value(resultSet.getString("text"))
                        .user(resultSet.getString("login"))
                        .sightId(resultSet.getLong("sight_id"))
                        .build();

                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }

    public static ReviewDao getInstance() {
        return INSTANCE;
    }
}

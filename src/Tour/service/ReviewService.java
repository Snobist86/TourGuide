package Tour.service;

import Tour.Dao.ReviewDao;
import Tour.Dto.review.ReviewDto;
import Tour.Dto.review.ViewReviewDto;
import Tour.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReviewService {

    private static final ReviewService INSTANCE = new ReviewService();

    public void save(ReviewDto dto) {
        Review review = Review.builder()
                .sightId(dto.getSightId())
                .userId(dto.getUserId())
                .value(dto.getValue())
                .build();
        ReviewDao.getInstance().save(review);
    }

    public List<ViewReviewDto> search(String bySight) {
        return ReviewDao.getInstance().findBySight(bySight);
    }

    public static ReviewService getInstance() {

        return INSTANCE;
    }
}

package Tour.service;

import Tour.Dao.SightDao;
import Tour.Dto.sight.BasicInfoSightDto;
import Tour.Dto.sight.CreateSightDto;
import Tour.entity.Sight;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SightService {

    private static final SightService INSTANCE = new SightService();

    public CreateSightDto findById(Long Id) {
        return SightDao.getInstance().findById(Id)
                .map(it -> CreateSightDto.builder()
                        .name(it.getName())
                        .cityId(it.getCityId())
                        .yearOfBuilding(it.getYearOfBuilding())
                        .categoryId(it.getCategoryId())
                        .styleId(it.getStyleId())
                        .isListUNESCO(it.getIsListUNESCO())
                        .ratingOfTripAdvisor(it.getRatingOfTripAdvisor())
                        .build())
                .orElse(null);
    }

    public BasicInfoSightDto save(CreateSightDto dto) {
        Sight savedSight = SightDao.getInstance().save(
                Sight.builder()
                        .name(dto.getName())
                        .cityId(dto.getCityId())
                        .yearOfBuilding(dto.getYearOfBuilding())
                        .categoryId(dto.getCategoryId())
                        .styleId(dto.getStyleId())
                        .isListUNESCO(dto.getIsListUNESCO())
                        .ratingOfTripAdvisor(dto.getRatingOfTripAdvisor())
                        .build());

        return new BasicInfoSightDto(savedSight.getId(), savedSight.getName());
    }

    public static SightService getInstance() {

        return INSTANCE;
    }
}

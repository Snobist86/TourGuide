package Tour.service;

import Tour.Dao.SightDao;
import Tour.Dto.sight.BasicInfoSightDto;
import Tour.Dto.sight.SearchSightDto;
import Tour.Dto.sight.SightDto;
import Tour.Dto.sight.ViewSightDto;
import Tour.entity.Category;
import Tour.entity.City;
import Tour.entity.Sight;
import Tour.entity.Style;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SightService {

    private static final SightService INSTANCE = new SightService();

    public ViewSightDto findById(Long sightId) {
        return SightDao.getInstance().findById(sightId)
                .map(it -> ViewSightDto.builder()
                        .name(it.getName())
                        .city(it.getCity().getName())
                        .yearOfBuilding(it.getYearOfBuilding())
                        .category(it.getCategory().getName())
                        .style(it.getStyle().getName())
                        .isListUNESCO(it.getIsListUNESCO())
                        .ratingOfTripAdvisor(it.getRatingOfTripAdvisor())
                        .build())
                .orElse(null);
    }

    public List<BasicInfoSightDto> search(SearchSightDto searchSightDto) {
        return SightDao.getInstance().findAnySight(searchSightDto).stream()
                .map(it -> new BasicInfoSightDto(it.getId(), it.getName()))
                .collect(Collectors.toList());
    }

    public List<BasicInfoSightDto> listUnesco() {
        return SightDao.getInstance().listUnesco().stream()
                .map(it -> new BasicInfoSightDto(it.getId(), it.getName()))
                .collect(Collectors.toList());
    }

    public void save(SightDto dto) {
        SightDao.getInstance().save(
                Sight.builder()
                        .name(dto.getName())
                        .city(City.builder()
                                .id(dto.getCityId())
                                .build())
                        .yearOfBuilding(dto.getYearOfBuilding())
                        .category(Category.builder()
                                .id(dto.getCategoryId())
                                .build())
                        .style(Style.builder()
                                .id(dto.getStyleId())
                                .build())
                        .isListUNESCO(dto.getIsListUNESCO())
                        .ratingOfTripAdvisor(dto.getRatingOfTripAdvisor())
                        .build());
    }

    public static SightService getInstance() {

        return INSTANCE;
    }
}

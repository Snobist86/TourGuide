package Tour.service;

import Tour.Dto.airport.AirportDto;
import Tour.Dao.AirportDao;
import Tour.Dto.airport.FullInfoAirportDto;
import Tour.Dto.airport.SearchAirportDto;
import Tour.entity.Airport;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AirportService {

    private static final AirportService INSTANCE = new AirportService();

    public void save(AirportDto dto) {
        Airport airport = Airport.builder()
                .name(dto.getName())
                .cityId(dto.getCityId())
                .build();
        AirportDao.getInstance().save(airport);
    }

    public List<FullInfoAirportDto> search(SearchAirportDto searchAirportDto) {
        return AirportDao.getInstance().findAnySight(searchAirportDto);
    }

    public static AirportService getInstance() {

        return INSTANCE;
    }
}
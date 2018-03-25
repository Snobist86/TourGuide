package Tour.service;

import Tour.Dto.airport.AirportDto;
import Tour.Dao.AirportDao;
import Tour.entity.Airport;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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

    public static AirportService getInstance() {

        return INSTANCE;
    }
}
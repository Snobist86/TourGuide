package Tour.service;

import Tour.Dao.CityDao;
import Tour.Dto.city.CityDto;
import Tour.entity.City;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CityService {

    private static final CityService INSTANCE = new CityService();

    public List<City> findAll() { return CityDao.getInstance().findAll(); }

    public void save(CityDto dto) {
        City city = City.builder()
                .name(dto.getName())
                .countryId(dto.getCountryId())
                .build();
        CityDao.getInstance().save(city);
    }

    public static CityService getInstance() {

        return INSTANCE;
    }
}

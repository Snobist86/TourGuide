package Tour.service;

import Tour.Dao.CountryDao;
import Tour.Dto.country.CountryDto;
import Tour.entity.Country;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CountryService {

    private static final CountryService INSTANCE = new CountryService();

    public List<Country> findAll() {
        return CountryDao.getInstance().findAll();
    }

    public void save(CountryDto dto) {
        Country country = Country.builder()
                .name(dto.getName())
                .build();
        CountryDao.getInstance().save(country);
    }

    public static CountryService getInstance() {

        return INSTANCE;
    }
}

package Tour.service;

import Tour.Dao.StyleDao;
import Tour.Dto.style.StyleDto;
import Tour.entity.Style;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class StyleService {

    private static final StyleService INSTANCE = new StyleService();

    public List<Style> findAll() {
        return StyleDao.getInstance().findAll();
    }

    public void save(StyleDto dto) {
        Style style = Style.builder()
                .name(dto.getName())
                .build();
        StyleDao.getInstance().save(style);
    }

    public static StyleService getInstance(){

        return INSTANCE;
    }
}

package Tour.service;

import Tour.Dao.CategoryDao;
import Tour.Dto.category.CategoryDto;
import Tour.entity.Category;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class CategoryService {

    private static final CategoryService INSTANCE = new CategoryService();

    public List<Category> findAll() {
        return CategoryDao.getInstance().findAll();
    }

    public void save(CategoryDto dto) {
        Category category = Category.builder()
                .name(dto.getName())
                .build();
        CategoryDao.getInstance().save(category);
    }

    public static CategoryService getInstance() {

        return INSTANCE;
    }
}

package Tour.service;

import Tour.Dao.RoleDao;
import Tour.entity.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RoleService {

    private static final RoleService INSTANCE = new RoleService();

    public List<Role> findAll() { return RoleDao.getInstance().findAll(); }

    public static RoleService getInstance() {

        return INSTANCE;
    }


}

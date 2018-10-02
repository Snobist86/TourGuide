package Tour.service;

import Tour.Dao.UserDao;
import Tour.Dto.user.ChangeRoleUserDto;
import Tour.Dto.user.FullInfoUserDto;
import Tour.Dto.user.UserDto;
import Tour.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserService {

    private static final UserService INSTANCE = new UserService();

    public void save(FullInfoUserDto dto) {
        User user = User.builder()
                .login(dto.getLogin())
                .password(dto.getPassword())
                .eMail(dto.getEMail())
                .build();
        UserDao.getInstance().save(user);
    }

    public UserDto findByLogin(String login) {
        return UserDao.getInstance().findByLogin(login)
                .map(it -> UserDto.builder()
                        .id(it.getId())
                        .login(it.getLogin())
                        .build())
                .orElse(null);
    }

    public void changeRole(String login, String roleId) {
        UserDao.getInstance().findByLogin(login)
                .map(it -> ChangeRoleUserDto.builder()
                        .id(it.getId())
                        .login(it.getLogin())
                        .roleId(Integer.valueOf(roleId))
                        .build()).ifPresent(user -> UserDao.getInstance().changeRole(user));
    }

    public UserDto findById(Long sightId) {
        return UserDao.getInstance().findById(sightId)
                .map(it -> UserDto.builder()
                        .id(it.getId())
                        .login(it.getLogin())
                        .build())
                .orElse(null);
    }

    public static UserService getInstance() {

        return INSTANCE;
    }
}

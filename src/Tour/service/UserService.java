package Tour.service;

import Tour.Dao.UserDao;
import Tour.Dto.user.UserDto;
import Tour.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserService {

    private static final UserService INSTANCE = new UserService();

    public void save(UserDto dto) {
        User user = User.builder()
                .login(dto.getLogin())
                .password(dto.getPassword())
                .eMail(dto.getEMail())
                .build();
        UserDao.getInstance().save(user);
    }

    public static UserService getInstance(){

        return INSTANCE;
    }
}
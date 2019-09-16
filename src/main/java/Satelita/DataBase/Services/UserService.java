package Satelita.DataBase.Services;

import Satelita.DataBase.Enum.EnrollEnum;
import Satelita.DataBase.Enum.LoginEnum;
import Satelita.DataBase.Enum.RoleEnum;
import Satelita.DataBase.Models.Auth;
import Satelita.DataBase.Models.SigninPayload;
import Satelita.DataBase.Models.User;
import Satelita.DataBase.Repository.UserRepository;
import Satelita.DataBase.Tools.ServiceResult;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

@Service("userService")
@EnableTransactionManagement
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession httpSession;

    @Override
    public ServiceResult<SigninPayload, LoginEnum> loginUser(Auth auth) {

        ServiceResult<SigninPayload, LoginEnum> serviceResult = new ServiceResult<>();

        if (auth.getLogin() == null || auth.getLogin().isEmpty()) {
            serviceResult.setEnumValue(LoginEnum.MissingLogin);
            return serviceResult;
        }

        if (auth.getPassword() == null || auth.getPassword().isEmpty()) {
            serviceResult.setEnumValue(LoginEnum.MissingPassword);
            return serviceResult;
        }

        String password = Hashing.sha256()
                .hashString(
                        auth.getPassword(),
                        StandardCharsets.UTF_8)
                .toString();

        User u = userRepository.findByLoginAndPasswordAndDeletedFalse(auth.getLogin(), password);

        if (u != null) {
            serviceResult.setEnumValue(LoginEnum.Success);
            return serviceResult;
        }

        u = userRepository.findByLoginAndDeletedFalse(auth.getLogin());

        if (u != null) {
            serviceResult.setEnumValue(LoginEnum.WrongPassword);
        } else {
            serviceResult.setEnumValue(LoginEnum.LoginNotFound);
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<User, EnrollEnum> registerUser(Auth authData) {

        ServiceResult<User, EnrollEnum> result = new ServiceResult<>();
        User u;

        if (authData.getLogin() == null || authData.getLogin().isEmpty()) {
            result.setEnumValue(EnrollEnum.EmptyLogin);
            return result;
        }

        if (authData.getPassword() == null || authData.getPassword().isEmpty()) {
            result.setEnumValue(EnrollEnum.EmptyPassword);
            return result;
        }

        u = userRepository.findByLoginAndDeletedFalse(authData.getLogin());

        if (u != null) {
            result.setEnumValue(EnrollEnum.Exist);
            return result;
        }

        String password = Hashing.sha256()
                .hashString(
                        authData.getPassword(),
                        StandardCharsets.UTF_8)
                .toString();

        u = new User();
        u.setLogin(authData.getLogin());
        u.setPassword(password);
        u.setDeleted(false);
        u.setRole(RoleEnum.ROLE_USER.toString());

        userRepository.save(u);

        u = userRepository.findByLoginAndPasswordAndDeletedFalse(authData.getLogin(), password);

        if (u == null) {
            result.setEnumValue(EnrollEnum.Failure);
        } else {
            result.setEnumValue(EnrollEnum.Success);
            result.setData(u);
        }

        return result;
    }

    @Override
    public ServiceResult<User, LoginEnum> authorizeUser(Auth auth) {

        ServiceResult<User, LoginEnum> serviceResult = new ServiceResult<>();

        if (auth.getLogin() == null || auth.getLogin().isEmpty()) {
            serviceResult.setEnumValue(LoginEnum.MissingLogin);
            return serviceResult;
        }

        User user = userRepository.findByLoginAndDeletedFalse(auth.getLogin());

        if (user != null) {
            serviceResult.setEnumValue(LoginEnum.Success);
            serviceResult.setData(user);
        } else {
            serviceResult.setEnumValue(LoginEnum.LoginNotFound);
        }

        return serviceResult;
    }
}

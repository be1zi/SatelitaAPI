package Satelita.DataBase.Services;

import Satelita.DataBase.Enum.EnrollEnum;
import Satelita.DataBase.Enum.LoginEnum;
import Satelita.DataBase.Models.Auth;
import Satelita.DataBase.Models.User;
import Satelita.DataBase.Repository.UserRepository;
import Satelita.DataBase.Tools.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Service("userService")
@EnableTransactionManagement
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ServiceResult<User, LoginEnum> loginUser(Auth auth) {

        ServiceResult<User, LoginEnum> serviceResult = new ServiceResult<>();

        if (auth.getLogin() == null || auth.getLogin().isEmpty()) {
            serviceResult.setEnumValue(LoginEnum.MissingLogin);
            return serviceResult;
        }

        if (auth.getPassword() == null || auth.getPassword().isEmpty()) {
            serviceResult.setEnumValue(LoginEnum.MissingPassword);
            return serviceResult;
        }

        User u = userRepository.findByLoginAndPasswordAndDeletedFalse(auth.getLogin(), auth.getPassword());
        serviceResult.setData(u);

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

        if (authData.getLogin() == null ||
                authData.getLogin().isEmpty() ||
                authData.getPassword() == null ||
                authData.getPassword().isEmpty()) {

            result.setEnumValue(EnrollEnum.EmptyField);

            return result;
        }

        u = userRepository.findByLoginAndDeletedFalse(authData.getLogin());

        if (u != null) {
            result.setEnumValue(EnrollEnum.Exist);
            result.setData(u);
            return result;
        }

        u = new User();
        u.setLogin(authData.getLogin());
        u.setPassword(authData.getPassword());
        u.setDeleted(false);

        userRepository.save(u);

        u = userRepository.findByLoginAndPasswordAndDeletedFalse(authData.getLogin(), authData.getPassword());

        if (u == null) {
            result.setEnumValue(EnrollEnum.Failure);
        } else {
            result.setEnumValue(EnrollEnum.Success);
        }
        result.setData(u);

        return result;
    }
}

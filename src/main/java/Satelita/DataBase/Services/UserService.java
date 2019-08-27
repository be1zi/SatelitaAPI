package Satelita.DataBase.Services;

import Satelita.DataBase.Enum.EnrollEnum;
import Satelita.DataBase.Enum.LoginEnum;
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
    public ServiceResult<User, LoginEnum> loginUser(User user) {

        ServiceResult<User, LoginEnum> serviceResult = new ServiceResult<>();

        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            serviceResult.setEnumValue(LoginEnum.MissingLogin);
            return serviceResult;
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            serviceResult.setEnumValue(LoginEnum.MissingPassword);
            return serviceResult;
        }

        User u = userRepository.findByLoginAndPasswordAndDeletedFalse(user.getLogin(), user.getPassword());
        serviceResult.setData(u);

        if (u != null) {
            serviceResult.setEnumValue(LoginEnum.Success);
            return serviceResult;
        }

        u = userRepository.findByLoginAndDeletedFalse(user.getLogin());

        if (u != null) {
            serviceResult.setEnumValue(LoginEnum.WrongPassword);
        } else {
            serviceResult.setEnumValue(LoginEnum.LoginNotFound);
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<User, EnrollEnum> registerUser(User user) {

        return null;
    }
}

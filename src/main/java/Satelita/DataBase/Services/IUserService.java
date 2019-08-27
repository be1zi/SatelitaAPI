package Satelita.DataBase.Services;

import Satelita.DataBase.Enum.EnrollEnum;
import Satelita.DataBase.Enum.LoginEnum;
import Satelita.DataBase.Models.User;
import Satelita.DataBase.Tools.ServiceResult;

public interface IUserService {

    ServiceResult<User, LoginEnum> loginUser(User user);
    ServiceResult<User, EnrollEnum> registerUser(User user);
}

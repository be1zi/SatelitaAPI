package Satelita.DataBase.Services;

import Satelita.DataBase.Enum.EnrollEnum;
import Satelita.DataBase.Enum.LoginEnum;
import Satelita.DataBase.Models.Auth;
import Satelita.DataBase.Models.SigninPayload;
import Satelita.DataBase.Models.User;
import Satelita.DataBase.Tools.ServiceResult;

public interface IUserService {

    ServiceResult<SigninPayload, LoginEnum> loginUser(Auth auth);
    ServiceResult<User, EnrollEnum> registerUser(Auth authData);
    ServiceResult<User, LoginEnum> authorizeUser(Auth auth);
}

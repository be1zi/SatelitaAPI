package Satelita.Network.Resolver;

import Satelita.DataBase.Enum.LoginEnum;
import Satelita.DataBase.Models.Auth;
import Satelita.DataBase.Models.SigninPayload;
import Satelita.DataBase.Models.User;
import Satelita.DataBase.Services.IUserService;
import Satelita.DataBase.Tools.ServiceResult;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;

public class Mutation implements GraphQLMutationResolver {

    @Autowired
    IUserService iUserService;

    public User createUser(Auth authData) {

        return iUserService.registerUser(authData).getData();
    }

    public SigninPayload signinUser(Auth authData) throws IllegalAccessException {

        ServiceResult<SigninPayload, LoginEnum> serviceResult = iUserService.loginUser(authData);

        switch (serviceResult.getEnumValue()) {
            case LoginNotFound: {
                throw new GraphQLException("Invalid credentials: login not exist");
            }
            case MissingLogin: {
                throw new GraphQLException("Invalid credentials: login is required");
            }
            case MissingPassword: {
                throw new GraphQLException("Invalid credentials: password ir required");
            }
            case WrongPassword: {
                throw new GraphQLException("Invalid credentials: incorrect password");
            }
            case Failure: {
                throw new GraphQLException("Internal error");
            }
            case Success: {
                break;
            }
        }

        return iUserService.loginUser(authData).getData();
    }
}

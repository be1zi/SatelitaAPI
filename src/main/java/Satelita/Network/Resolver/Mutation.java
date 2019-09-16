package Satelita.Network.Resolver;

import Satelita.DataBase.Enum.EnrollEnum;
import Satelita.DataBase.Models.Auth;
import Satelita.DataBase.Models.User;
import Satelita.DataBase.Services.IUserService;
import Satelita.DataBase.Tools.ServiceResult;
import Satelita.Network.Authorization.Unsecured;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;

public class Mutation implements GraphQLMutationResolver {

    @Autowired
    IUserService iUserService;

    @Unsecured
    public User createUser(Auth authData) {

        ServiceResult<User, EnrollEnum> serviceResult = iUserService.registerUser(authData);

        switch (serviceResult.getEnumValue()) {
            case Success: {
                break;
            }
            case Failure: {
                throw new GraphQLException("Internal error");
            }
            case Exist: {
                throw new GraphQLException("User with login: " + authData.getLogin() + " currently exist");
            }
            case EmptyLogin: {
                throw new GraphQLException("Login is required");
            }
            case EmptyPassword: {
                throw new GraphQLException("Password is required ");
            }
        }

        return serviceResult.getData();
    }

//    @Unsecured
//    public SigninPayload signinUser(Auth authData) throws IllegalAccessException {
//
//        ServiceResult<SigninPayload, LoginEnum> serviceResult = iUserService.loginUser(authData);
//
//        switch (serviceResult.getEnumValue()) {
//            case LoginNotFound: {
//                throw new GraphQLException("Invalid credentials: login not exist");
//            }
//            case MissingLogin: {
//                throw new GraphQLException("Invalid credentials: login is required");
//            }
//            case MissingPassword: {
//                throw new GraphQLException("Invalid credentials: password ir required");
//            }
//            case WrongPassword: {
//                throw new GraphQLException("Invalid credentials: incorrect password");
//            }
//            case Failure: {
//                throw new GraphQLException("Internal error");
//            }
//            case Success: {
//                break;
//            }
//        }
//
//        return serviceResult.getData();
//    }
}

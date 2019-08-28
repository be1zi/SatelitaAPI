package Satelita.Network.Resolver;

import Satelita.DataBase.Models.Auth;
import Satelita.DataBase.Models.User;
import Satelita.DataBase.Services.IUserService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Mutation implements GraphQLMutationResolver {

    @Autowired
    IUserService iUserService;

    public User createUser(Auth authData) {
        return iUserService.registerUser(authData).getData();
    }

    public boolean signinUser(Auth authData) {
        return true;
    }
}

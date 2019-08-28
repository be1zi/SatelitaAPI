package Satelita.Network.Resolver;

import Satelita.DataBase.Models.Auth;
import Satelita.DataBase.Models.User;
import Satelita.DataBase.Services.IUserService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;

public class Query implements GraphQLQueryResolver {

    @Autowired
    IUserService iUserService;

    public User getUser(Auth auth) {
        return iUserService.loginUser(auth).getData();
    }

    public int getNumber() {
        return 1234;
    }
}

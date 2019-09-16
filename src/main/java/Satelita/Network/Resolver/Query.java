package Satelita.Network.Resolver;

import Satelita.DataBase.Services.IUserService;
import Satelita.Network.Authorization.Unsecured;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;

public class Query implements GraphQLQueryResolver {

    @Autowired
    IUserService iUserService;

    @Unsecured
    public int getNumber() {
        return 1234;
    }
}

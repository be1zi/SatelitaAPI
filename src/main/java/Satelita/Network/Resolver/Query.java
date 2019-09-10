package Satelita.Network.Resolver;

import Satelita.DataBase.Services.IUserService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;

public class Query implements GraphQLQueryResolver {

    @Autowired
    IUserService iUserService;

    //@PreAuthorize("hasRole('USER')")
    public int getNumber() {
        return 1234;
    }
}

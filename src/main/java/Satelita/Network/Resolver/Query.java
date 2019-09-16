package Satelita.Network.Resolver;

import Satelita.DataBase.Services.IUserService;
import Satelita.Network.Authorization.Unsecured;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

public class Query implements GraphQLQueryResolver {

    @Autowired
    IUserService iUserService;

    @Unsecured
    public int getNumber() {
        return 1234;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public int getSecuredNumber() {
        return 4321;
    }
}

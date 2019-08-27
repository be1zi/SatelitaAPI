//package Satelita.Network.Resolver;
//
//import Satelita.DataBase.Models.Auth;
//import Satelita.DataBase.Models.User;
//import Satelita.DataBase.Services.IUserService;
//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class Mutation implements GraphQLMutationResolver {
//
//    @Autowired
//    IUserService iUserService;
//
//    public User createUser(Auth authData) {
//        User u = new User();
//        u.setLogin(authData.getLogin());
//        u.setPassword(authData.getPassword());
//        u.setDeleted(false);
//
//        return iUserService.registerUser(u).getData();
//    }
//}

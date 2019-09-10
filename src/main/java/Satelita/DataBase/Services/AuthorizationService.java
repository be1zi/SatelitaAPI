package Satelita.DataBase.Services;

import Satelita.DataBase.Models.Auth;
import Satelita.DataBase.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("AuthorizationService")
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private IUserService iUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserService.authorizeUser(new Auth(username, null)).getData();
        List<GrantedAuthority> authorities = buildUserAuthority(user);

        return buildUserForAuthentication(user, authorities);
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        if (user == null) {
            return null;
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                !user.isDeleted(),
                true,
                true,
                true,
                authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(User user) {

        if (user == null) {
            return null;
        }

        List<GrantedAuthority> result = new ArrayList<>();
        result.add(new SimpleGrantedAuthority(user.getRole()));

        return result;
    }
}

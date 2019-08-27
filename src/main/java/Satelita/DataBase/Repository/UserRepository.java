package Satelita.DataBase.Repository;

import Satelita.DataBase.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLoginAndPasswordAndDeletedFalse(String login, String password);
    User findByLoginAndDeletedFalse(String login);
}

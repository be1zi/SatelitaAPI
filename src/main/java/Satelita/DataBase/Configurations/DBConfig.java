package Satelita.DataBase.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(env.getProperty("driverClassName"));
        dataSourceBuilder.url(env.getProperty("url"));
        return dataSourceBuilder.build();
    }
}

@Configuration
@Profile("dev")
@PropertySource("classpath:application.properties")
class Config {

}

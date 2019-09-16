package Satelita;

import Satelita.Network.Resolver.Mutation;
import Satelita.Network.Resolver.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SatelitaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SatelitaApplication.class, args);
    }


    @Bean
    public Query queryResolver() {
        return new Query();
    }

    @Bean
    public Mutation mutationResolver() {
        return new Mutation();
    }
}

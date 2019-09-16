package Satelita.Network.Authorization;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Order(1)
public class SecurityQraphQLAspect {

    @Before("allGraphQLResolverMethods() && isDefinedInApplication() && isMethodAnnotatedAsUncensured()")
    public void doSecureCheck() {
        if (SecurityContextHolder.getContext() == null ||
                SecurityContextHolder.getContext().getAuthentication() == null ||
                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated() ||
                AnonymousAuthenticationToken.class.isAssignableFrom(SecurityContextHolder.getContext().getAuthentication().getClass())) {
            throw new AccessDeniedException("User not authenticated");
        }
    }

    @Pointcut("target(com.coxautodev.graphql.tools.GraphQLResolver)")
    private void allGraphQLResolverMethods() {

    }

    @Pointcut("within(Satelita.Network.Resolver..*)")
    private void isDefinedInApplication() {

    }

    @Pointcut("@annotation(Satelita.Network.Authorization.Unsecured)")
    private void isMethodAnnotatedAsUncensured() {
        System.out.println("Unsecured");
    }
}

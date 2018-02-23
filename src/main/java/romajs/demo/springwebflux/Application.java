package romajs.demo.springwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import romajs.demo.springwebflux.http.GreetingRouteHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(GreetingRouteHandler routeHandler) {
        return nest(path("/greeting"),
                route(RequestPredicates.GET(""), routeHandler::findAll)
                .andRoute(RequestPredicates.GET("/{id}"), routeHandler::findById)
                .andRoute(RequestPredicates.GET("/{id}/stream"), routeHandler::streams)
        );
    }

}
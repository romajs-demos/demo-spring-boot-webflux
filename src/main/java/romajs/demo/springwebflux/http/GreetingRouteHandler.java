package romajs.demo.springwebflux.http;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import romajs.demo.springwebflux.model.Greeting;
import romajs.demo.springwebflux.model.GreetingEvent;
import romajs.demo.springwebflux.usecase.GreetingService;

@Component
public class GreetingRouteHandler {

    private final GreetingService greetingService;

    public GreetingRouteHandler(GreetingService greetingService) {
         this.greetingService = greetingService;
    }

    public Mono<ServerResponse> findAll(final ServerRequest serverRequest) {
         return ServerResponse.ok()
                .body(greetingService.findAll(), Greeting.class)
                .doOnError(throwable -> new IllegalStateException("*** TODO ***"));
    }

    public Mono<ServerResponse> findById(final ServerRequest serverRequest) {
         final String id = serverRequest.pathVariable("id");
         return ServerResponse.ok()
               .body(greetingService.findById(id), Greeting.class)
               .doOnError(throwable -> new IllegalStateException("*** TODO ***"));
    }

    public Mono<ServerResponse> streams(final ServerRequest serverRequest) {
         final String id = serverRequest.pathVariable("id");
         return ServerResponse.ok()
               .contentType(MediaType.TEXT_EVENT_STREAM)
               .body(greetingService.streamById(id), GreetingEvent.class)
               .doOnError(throwable -> new IllegalStateException("*** TODO ***"));
    }
}
package romajs.demo.springwebflux.repository;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import romajs.demo.springwebflux.model.Greeting;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class GreetingRepository {

    private static final Greeting FIRST_GREETING = new Greeting("First greeting");
    private static final Greeting SECOND_GREETING = new Greeting("Second greeting");
    private static final Greeting THIRDY_GREETING = new Greeting("Thirdy greeting");

    public static final Map<String, Greeting> greetingsById = new LinkedHashMap<>();

    static {
        greetingsById.put("1", FIRST_GREETING);
        greetingsById.put("2", SECOND_GREETING);
        greetingsById.put("3", THIRDY_GREETING);
    }

    public Flux<Greeting> findAll() {
        return Flux.just(FIRST_GREETING, SECOND_GREETING, THIRDY_GREETING);
    }

    public Mono<Greeting> findById(final String id) {
        return Mono.just(greetingsById.get(id));
    }
}

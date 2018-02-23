package romajs.demo.springwebflux.usecase;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import romajs.demo.springwebflux.model.Greeting;
import romajs.demo.springwebflux.model.GreetingEvent;
import romajs.demo.springwebflux.repository.GreetingRepository;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class GreetingService {

    private GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Flux<Greeting> findAll() {
        return greetingRepository.findAll();
    }

    public Mono<Greeting> findById(final String id) {
        return greetingRepository.findById(id);
    }

    public Flux<GreetingEvent> streamById(final String id) {
        return findById(id).flatMapMany(greeting -> {
            final Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
            final Stream<GreetingEvent> greetingEventsStream = Stream.generate(() -> new GreetingEvent(greeting, new Date()));
            final Flux<GreetingEvent> events = Flux.fromStream(greetingEventsStream);
            return Flux.zip(interval, events).map(Tuple2::getT2);
        });
    }
}

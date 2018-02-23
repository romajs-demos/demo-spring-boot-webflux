package romajs.demo.springwebflux.http;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import romajs.demo.springwebflux.model.Greeting;
import romajs.demo.springwebflux.model.GreetingEvent;
import romajs.demo.springwebflux.usecase.GreetingService;

//@RestController
public class GreetingController {

    private GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Flux<Greeting> findAll() {
        return greetingService.findAll();
    }

    @RequestMapping(value = "/greeting/{id}", method = RequestMethod.GET)
    public Mono<Greeting> findBydId(@PathVariable String id) {
        return greetingService.findById(id);
    }

    @RequestMapping(value = "/greeting/{id}/stream", method = RequestMethod.GET)
    public Flux<GreetingEvent> streamById(@PathVariable String id) {
        return greetingService.streamById(id);
    }
}

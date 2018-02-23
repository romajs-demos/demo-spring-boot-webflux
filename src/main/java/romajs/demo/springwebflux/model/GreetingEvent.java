package romajs.demo.springwebflux.model;

import java.util.Date;

public class GreetingEvent {

    private Greeting greeting;

    private Date when;

    public GreetingEvent(Greeting greeting, Date when) {
        this.greeting = greeting;
        this.when = when;
    }

    public Greeting getGreeting() {
        return greeting;
    }

    public void setGreeting(Greeting greeting) {
        this.greeting = greeting;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }
}

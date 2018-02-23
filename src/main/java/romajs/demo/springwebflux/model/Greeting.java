package romajs.demo.springwebflux.model;

public class Greeting {

    private String description;

    public Greeting() {
    }

    public Greeting(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

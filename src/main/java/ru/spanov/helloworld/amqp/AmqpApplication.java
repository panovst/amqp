package ru.spanov.helloworld.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class AmqpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmqpApplication.class, args);
	}

    @StreamListener(target = Sink.INPUT, condition = "headers['type']=='bacall'")
    public void handleBacall(Person person) {
        System.out.println("[bacall] Received: " + person);
    }

    @StreamListener(target = Sink.INPUT, condition = "headers['type']=='bogey'")
    public void handleBogey(Person person) {
        System.out.println("[bogey] Received: " + person);
    }

    public static class Person {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String toString() {
            return this.name;
        }
    }
}

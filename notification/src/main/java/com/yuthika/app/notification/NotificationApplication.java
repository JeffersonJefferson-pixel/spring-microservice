package com.yuthika.app.notification;

import com.yuthika.app.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "com.yuthika.app.amqp",
                "com.yuthika.app.notification"
        }
)
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
@EnableEurekaClient
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//            RabbitMQMessageProducer producer, NotificationConfig config) {
//        return args -> {
//            producer.publish(
//                    new Person("Jim", 22),
//                    config.getInternalExchange(),
//                    config.getRoutingKey()
//            );
//        };
//    }
//
//    record Person(String name, int age) {}
}

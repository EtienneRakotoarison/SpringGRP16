package itu.s5.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ReportApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(TpWsApplication.class, args);
    }
}

package il.george_nika.phrase2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;


@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class, GsonAutoConfiguration.class} )
public class WebApplication  {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}

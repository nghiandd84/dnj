package dnj.book.recommendation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookRecommendationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookRecommendationApplication.class, args);
	}

}

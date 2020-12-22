package dnj.book.catalogue.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dnj.book.catalogue.model.Book;

@RestController
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksController {

	@RequestMapping(method = RequestMethod.GET)
	public List<Book> index() {
		Book buildingMicroservices = new Book("1491950358", "Building Microservices");
		Book releaseIt = new Book("1680502395", "Release It!");
		Book cidelivery = new Book("0321601912", "Continuous Delivery:");
		return Arrays.asList(buildingMicroservices, releaseIt, cidelivery);
	}
}

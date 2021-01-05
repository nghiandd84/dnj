package dnj.book.catalogue.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dnj.sdk.book.resp.Book;
import dnj.common.core.annotations.DataAccess;
import dnj.common.core.annotations.dao.GenericDAO;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksController {

	@DataAccess(entity = dnj.book.catalogue.model.Book.class)
	private GenericDAO<dnj.book.catalogue.model.Book> bookGenericDao;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public List<Book> index() {
		Book buildingMicroservices = new Book(1491950358L, "Building Microservices");
		Book releaseIt = new Book(1680502395L, "Release It!");
		Book cidelivery = new Book(321601912L, "Continuous Delivery:");
		return Arrays.asList(buildingMicroservices, releaseIt, cidelivery);
	}

	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public Book insertBook() {
		Book book = new Book(1491950358L, "Building Microservices");
		dnj.book.catalogue.model.Book bookEntity = new dnj.book.catalogue.model.Book();
		bookEntity.setId(book.getId());
		bookEntity.setName(book.getName());
		this.bookGenericDao.persist(bookEntity);
		return book;
	}

}

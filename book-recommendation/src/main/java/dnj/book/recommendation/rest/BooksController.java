package dnj.book.recommendation.rest;

import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Optional;

import dnj.book.recommendation.model.BookRecommendation;
import dnj.book.recommendation.service.BookCatalogService;
import dnj.book.recommendation.service.BookInventoryService;

@RestController
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksController {

	private Logger log = LoggerFactory.getLogger(BooksController.class);

	@Autowired
	private BookCatalogService bookCatalogService;

	@Autowired
	private BookInventoryService bookInventoryService;

	@RequestMapping(method = RequestMethod.GET)
	public List<BookRecommendation> getRecommendation() {
		this.bookCatalogService.findAll().stream().forEach(book -> {
			log.info("Find Book {}", book);
			Boolean isInStock = this.bookInventoryService.haveInInventory(book.getId());
			log.info("isInStock {}", isInStock);
		});
		return this.bookCatalogService.findAll().stream().filter(book -> {
			Boolean haveInInventory = this.bookInventoryService.haveInInventory(book.getId());
			return Objects.nonNull(haveInInventory) && haveInInventory;
		}).map(book -> new BookRecommendation(book.getName())).collect(Collectors.toList());
	}

}
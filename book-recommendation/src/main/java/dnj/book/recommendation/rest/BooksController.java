package dnj.book.recommendation.rest;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dnj.book.recommendation.model.BookRecommendation;
import dnj.book.recommendation.service.BookCatalogService;
import dnj.book.recommendation.service.BookInventoryService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class BooksController {

//	private Logger log = LoggerFactory.getLogger(BooksController.class);

	@Autowired
	private BookCatalogService bookCatalogService;

	@Autowired
	private BookInventoryService bookInventoryService;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public List<BookRecommendation> getRecommendation() {
		log.info("getRecommendation");
		return this.bookCatalogService.findAll().stream().filter(book -> {
			Boolean haveInInventory = this.bookInventoryService.haveInInventory(book.getId());
			return Objects.nonNull(haveInInventory) && haveInInventory;
		}).map(book -> new BookRecommendation(book.getName())).collect(Collectors.toList());
	}

}
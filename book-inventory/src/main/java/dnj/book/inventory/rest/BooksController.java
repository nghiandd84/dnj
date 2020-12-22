package dnj.book.inventory.rest;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dnj.book.inventory.model.BookInventory;

@RestController
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksController {

	@RequestMapping(value = "/stock/{isbn}", method = RequestMethod.GET)
	public Boolean stock(@PathVariable(name = "isbn") String isbn) {
		return bookInventoryByIsbn(isbn).map(bi -> bi.getStock() > 0).orElse(false);
	}

	private Optional<BookInventory> bookInventoryByIsbn(String isbn) {
		if (isbn.equals("1491950358")) {
			return Optional.of(new BookInventory(isbn, 4));

		} else if (isbn.equals("1680502395")) {
			return Optional.of(new BookInventory(isbn, 0));
		}
		return Optional.empty();
	}
}

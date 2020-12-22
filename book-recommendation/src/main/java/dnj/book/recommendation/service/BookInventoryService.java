package dnj.book.recommendation.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import dnj.book.recommendation.model.BookInventory;

@FeignClient("book-inventory")
public interface BookInventoryService {
	@RequestMapping(method = RequestMethod.GET, value = "/books/stock/{isbn}")
	Boolean haveInInventory(@PathVariable("isbn") String isbn);
}

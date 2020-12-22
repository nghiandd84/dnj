package dnj.book.recommendation.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dnj.book.recommendation.model.Book;

@FeignClient("book-catalogue")
public interface BookCatalogService {
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	List<Book> findAll();
}

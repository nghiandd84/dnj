package dnj.book.recommendation.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dnj.common.core.constant.ServiceInstance;

import dnj.sdk.book.resp.Book;

@FeignClient(ServiceInstance.BOOK_CATALOGUE)
public interface BookCatalogService {
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	List<Book> findAll();
}

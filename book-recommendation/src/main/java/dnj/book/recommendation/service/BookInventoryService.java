package dnj.book.recommendation.service;

import javax.annotation.Nullable;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import dnj.common.core.constant.ServiceInstance;

@FeignClient(ServiceInstance.BOOK_INVENTORY)
public interface BookInventoryService {
	@RequestMapping(method = RequestMethod.GET, value = "/books/stock/{isbn}")
	@Nullable
	Boolean haveInInventory(@PathVariable("isbn") String isbn);
}

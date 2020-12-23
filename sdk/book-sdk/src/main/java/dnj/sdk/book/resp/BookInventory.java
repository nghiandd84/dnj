package dnj.sdk.book.resp;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class BookInventory {
	private String isbn;
	private Integer stock;
}

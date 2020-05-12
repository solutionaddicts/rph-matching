package com.addicts.rph.matching.util;

import com.addicts.rph.matching.exception.RphException;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * @author sravantatikonda
 */
@Data
public class PagedList<T> implements Serializable {

  private static final long serialVersionUID = -6853854678909830559L;

  private static final int MIN_PAGE = 1;
  private static final int DEFAULT_PAGESIZE = 10;

  private long totalCount;
  private int currentPage;
  private int pageSize;
  private int totalPages;

  private transient Collection<T> records;

  public PagedList() {
  }

  public PagedList(Collection<T> collection, Pageable pageable, long totalCount) {
    super();
    this.totalCount = totalCount;
    this.currentPage = pageable.getPageNumber() == 0 ? 1 : pageable.getPageNumber() + 1;
    this.records = collection;
    this.pageSize = this.getRecords().isEmpty() ? DEFAULT_PAGESIZE : this.getRecords().size();
    this.totalPages = this.fetchTotalPages(pageable, this.pageSize, this.totalCount);
  }

  public static Sort getSorts(String[] properties) throws RphException {

    if (properties != null && properties.length > 0) {
      LinkedList orders = new LinkedList();
      int var3 = properties.length;

      for (int var4 = 0; var4 < var3; ++var4) {
        String order = properties[var4];
        String property = order;
        Direction direction = Direction.ASC;
        if (order.contains("|")) {
          property = order.substring(0, order.indexOf(124));
          direction = "desc".equalsIgnoreCase(order.substring(order.indexOf(124) + 1).trim())
              ? Direction.DESC : Direction.ASC;
        }

        orders.add(new Order(direction, property));
      }
      return Sort.by(orders);
    } else {
      return Sort.unsorted();
    }
  }

  private int fetchTotalPages(Pageable pageable, int totalPageSize, long totalCount) {
    return pageable.getPageSize() > totalPageSize ? pageable.getPageNumber()
        : (int) Math.ceil((double) totalCount / (double) totalPageSize);
  }
}

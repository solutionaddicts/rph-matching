package com.addicts.rph.matching.dto;

import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class PageableImpl {

  private Integer page;
  private Integer size;
  private String sort[];

  public PageableImpl(Integer page, Integer size, String[] sort) {
    this.page = page;
    this.size = size;
    this.sort = sort;
  }
}

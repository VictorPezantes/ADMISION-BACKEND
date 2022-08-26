package com.pe.ttk.admision.util;

import lombok.*;

import java.util.List;

/**
 * Search criteria object for query string.
 */
@Data
public class SearchCriteria {
  private String key;
  private String operation;
  private Object value;
  private List<String> listValue;

  public SearchCriteria(final String key, final String operation, final Object value) {
    super();
    this.key = key;
    this.operation = operation;
    this.value = value;
  }



}

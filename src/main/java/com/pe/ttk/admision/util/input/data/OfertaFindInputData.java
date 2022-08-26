package com.pe.ttk.admision.util.input.data;


import com.pe.ttk.admision.exceptions.TTKDataException;
import com.pe.ttk.admision.util.SearchCriteria;
import lombok.Data;

import java.util.List;

@Data
public class OfertaFindInputData {

  public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
  private static final String TITULO = "titulo";


  private String titulo;


  /**
   * Fill the data with the query string params
   */
  public void fillData(List<SearchCriteria> params) throws TTKDataException {


    if (params.isEmpty()) {
      throw new TTKDataException("Error: Debe ingresar como mínimo un parámetro de búsqueda.");
    }

    for (SearchCriteria criteria : params) {
      String key = criteria.getKey();
      String value = (String) criteria.getValue();
      List<String> listValue = criteria.getListValue();

      switch (key) {
        case TITULO:
          if (value != null) {
            setTitulo(value);
          }
      }
    }
  }




}

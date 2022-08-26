package com.pe.ttk.admision.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Filter params for a query string. example:   (search=param1:1,param2<20,param3>10)
 */
public class FilterParam {

  public static List<SearchCriteria> filter(String search) {
    List<SearchCriteria> params = new ArrayList<>();
    if (search != null) {
      Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)([áéíóúÁÉÍÓÚñÑ\\w\\s\\-]+?;?),");
      Matcher matcher = pattern.matcher(search + ",");
      while (matcher.find()) {
        SearchCriteria searchCriteria = new SearchCriteria(matcher.group(1), matcher.group(2),
                matcher.group(3));

        /** Funcionalidad para recibir listas vía GET */
        String[] valueList = matcher.group(3).split("\\-");
        if (valueList.length > 1) {
          searchCriteria.setListValue(Arrays.stream(valueList).collect(Collectors.toList()));
        }

        params.add(searchCriteria);
      }
    }
    return params;
  }
}



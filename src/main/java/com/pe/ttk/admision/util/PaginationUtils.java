package com.pe.ttk.admision.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.ui.Model;

public class PaginationUtils {

  private static final String RESULT = "result";

  private PaginationUtils() {
    throw new IllegalAccessError("Utility class");
  }

  public static String getPaginationedResults(List result, Integer page, Integer size,
      Model model) {

    /**
     * Added the Paginaiton Logic Below
     */
    Integer totalSize = result.size();
    PagedListHolder pagedListHolder = new PagedListHolder<>(
        result);

    if (size == 0) {
      pagedListHolder.setPageSize(totalSize);
    } else {
      pagedListHolder.setPageSize(size);
    }

    model.addAttribute("maxPages", pagedListHolder.getPageCount());
    model.addAttribute("totalElements", pagedListHolder.getNrOfElements());

    model.addAttribute("page", page);
    if (page == null || page < 1) {
      pagedListHolder.setPage(0);
      model.addAttribute(RESULT, pagedListHolder.getPageList());
    } else if (page <= pagedListHolder.getPageCount()) {
      pagedListHolder.setPage(page - 1);
      model.addAttribute(RESULT, pagedListHolder.getPageList());
    } else if (page > pagedListHolder.getPageCount()) {
      model.addAttribute(RESULT, "");
    }
    //convert java object to JSON format
    // SerializeNulls is to return attributes which are null because by default Gson ignores null
    Gson gson = new GsonBuilder().serializeNulls().create();
    return gson.toJson(model);

  }

}

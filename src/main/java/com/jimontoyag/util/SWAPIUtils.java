package com.jimontoyag.util;

import com.jimontoyag.client.dto.ListDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface SWAPIUtils {

  static int extractIdFromUrl(String url) {
    String[] split = url.replace("/", " ").trim().split(" ");
    return Integer.parseInt(split[split.length - 1]);
  }

  static <T, R> List<R> joinListPages(
      Function<Map<String, Integer>, ListDTO<T>> pageFunction, Function<T, R> mapper) {
    int page = 1;
    ListDTO<T> listDTO;
    var planets = new ArrayList<R>();
    do {
      listDTO = pageFunction.apply(Map.of("page", page));
      planets.addAll(listDTO.results().stream().map(mapper).toList());
      page++;
    } while (!listDTO.isLast());
    return planets;
  }
}

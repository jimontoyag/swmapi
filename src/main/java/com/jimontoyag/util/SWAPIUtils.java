package com.jimontoyag.util;

import com.jimontoyag.client.dto.ListDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class SWAPIUtils {

  public static int extractIdFromUrl(String url) {
    String[] split = url.replaceAll("/", " ").trim().split(" ");
    return Integer.parseInt(split[split.length - 1]);
  }

  public static <T, R> List<R> joinListPages(
      Function<Map, ListDTO<T>> pageFunction, Function<T, R> mapper) {
    int page = 1;
    ListDTO<T> listDTO;
    var planets = new ArrayList<R>();
    do {
      listDTO = pageFunction.apply(Map.of("page", page));
      planets.addAll(listDTO.results().stream().map(mapper).collect(Collectors.toList()));
      page++;
    } while (!listDTO.isLast());
    return planets;
  }
}

package com.jimontoyag.client.dto;

import com.jimontoyag.util.SWAPIUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.jimontoyag.util.SWAPIUtils.extractIdFromUrl;

public record StarshipDTO(String name, String url, String crew, List<String> pilots, String passengers) {

    public int id() {
        return extractIdFromUrl(url);
    }

    public List<Integer> pilotsId() {
        return pilots.stream()
                .map(SWAPIUtils::extractIdFromUrl)
                .toList();
    }

    public Long totalPassengers() {
        return Optional.ofNullable(passengers)
                .map(this::parseLong)
                .orElse(0L);
    }

    public Long maxCrew() {
        return
        Optional.ofNullable(crew)
                .map(String::trim)
                .filter(str -> !str.isEmpty())
                .filter(str -> !str.equals("unknown"))
                .map(this::split)
                .map(this::totalCrew)
                .orElse(0L);
    }

    private Long totalCrew(String[] strings) {
       return Stream.of(strings)
                .map(this::parseLong)
                .reduce(Long::max)
               .orElse(1L);
    }

    private Long parseLong(String str) {
        try{
            return Long.parseLong(str);
        } catch (NumberFormatException numberFormatException) {
            return 0L;
        }
    }

    private String[] split(String s) {
       return s.contains(",")? s.split(",") : s.split("-");
    }
}

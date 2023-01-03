package com.jimontoyag.model;

import java.time.OffsetDateTime;
import java.util.List;

public record Mission(OffsetDateTime date, Starship starship, List<People> captains, Integer passengers, List<Planet> planets, Long duration, Long reward) {
}

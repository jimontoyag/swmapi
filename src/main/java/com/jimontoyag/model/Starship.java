package com.jimontoyag.model;

import java.util.List;

public record Starship(int id, String name, Long crew, List<Integer> pilots, Long passengers) {
}

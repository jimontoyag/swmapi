package com.jimontoyag.model;

import java.util.Optional;

public record Planet(int id, String name, Optional<Long> diameter) {
}

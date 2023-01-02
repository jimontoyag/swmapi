package com.jimontoyag.client.dto;

import java.util.List;

public record ListDTO<T>(String next, String previous, List<T> results) {

    public boolean isLast() {
        return next == null;
    }

}

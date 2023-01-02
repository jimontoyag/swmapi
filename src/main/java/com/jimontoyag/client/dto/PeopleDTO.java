package com.jimontoyag.client.dto;

import static com.jimontoyag.util.SWAPIUtils.extractIdFromUrl;

public record PeopleDTO(String name, String url) {

    public int id() {
        return extractIdFromUrl(url);
    }
}

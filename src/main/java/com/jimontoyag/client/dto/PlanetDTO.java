package com.jimontoyag.client.dto;

import static com.jimontoyag.util.SWAPIUtils.extractIdFromUrl;

public record PlanetDTO(String name, String diameter, String url) {

    public int id() {
        return extractIdFromUrl(url);
    }

    public Long parseDiameter() {
        return !diameter.equals("unknown") ? Long.parseLong(diameter) : null;
    }
}

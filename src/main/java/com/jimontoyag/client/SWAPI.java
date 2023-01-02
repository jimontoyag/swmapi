package com.jimontoyag.client;

import com.jimontoyag.client.dto.ListDTO;
import com.jimontoyag.client.dto.PeopleDTO;
import com.jimontoyag.client.dto.PlanetDTO;
import com.jimontoyag.client.dto.StarshipDTO;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

public interface SWAPI {

  @RequestLine("GET /planets")
  ListDTO<PlanetDTO> getPlanets(@QueryMap Map page);

  @RequestLine("GET /planets/{id}")
  PlanetDTO planetById(@Param("id") int id);

  @RequestLine("GET /starships")
  ListDTO<StarshipDTO> getStarships(@QueryMap Map page);

  @RequestLine("GET /starships/{id}")
  StarshipDTO starshipById(@Param("id") int id);

  @RequestLine("GET /people")
  ListDTO<PeopleDTO> getPeople(@QueryMap Map page);

  @RequestLine("GET /people/{id}")
  PeopleDTO peopleById(@Param("id") int id);
}

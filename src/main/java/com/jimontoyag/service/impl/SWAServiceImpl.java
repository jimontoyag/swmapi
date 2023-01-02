package com.jimontoyag.service.impl;

import com.jimontoyag.client.SWAPI;
import com.jimontoyag.client.dto.PeopleDTO;
import com.jimontoyag.client.dto.PlanetDTO;
import com.jimontoyag.client.dto.StarshipDTO;
import com.jimontoyag.model.People;
import com.jimontoyag.model.Planet;
import com.jimontoyag.model.Starship;
import com.jimontoyag.service.SWAService;

import java.util.List;
import java.util.Optional;

import static com.jimontoyag.util.SWAPIUtils.joinListPages;

public class SWAServiceImpl implements SWAService {

  private final SWAPI swapi;

  public SWAServiceImpl(SWAPI swapi) {
    this.swapi = swapi;
  }

  @Override
  public List<Planet> getAllPlanets() {
    return joinListPages(swapi::getPlanets, this::mapPlanet);
  }

  @Override
  public Planet getPlanetById(int id) {
    return mapPlanet(swapi.planetById(id));
  }

  @Override
  public List<Starship> getAllStarships() {
    return joinListPages(swapi::getStarships, this::mapStarship);
  }

  @Override
  public Starship getStarshipById(int id) {
    return mapStarship(swapi.starshipById(id));
  }

  @Override
  public List<People> getAllPeople() {
    return joinListPages(swapi::getPeople, this::mapPeople);
  }

  @Override
  public People getPeopleById(int id) {
    return mapPeople(swapi.peopleById(id));
  }

  private Starship mapStarship(StarshipDTO starshipDTO) {
    return new Starship(
        starshipDTO.id(),
        starshipDTO.name(),
        starshipDTO.maxCrew(),
        starshipDTO.pilotsId(),
        starshipDTO.totalPassengers());
  }

  private Planet mapPlanet(PlanetDTO planetDTO) {
    return new Planet(
        planetDTO.id(), planetDTO.name(), Optional.ofNullable(planetDTO.parseDiameter()));
  }

  private People mapPeople(PeopleDTO peopleDTO) {
    return new People(peopleDTO.id(), peopleDTO.name());
  }
}

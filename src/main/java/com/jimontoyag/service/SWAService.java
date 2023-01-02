package com.jimontoyag.service;

import com.jimontoyag.model.People;
import com.jimontoyag.model.Planet;
import com.jimontoyag.model.Starship;

import java.util.List;

public interface SWAService {

  List<Planet> getAllPlanets();

  Planet getPlanetById(int id);

  List<Starship> getAllStarships();

  Starship getStarshipById(int id);

  List<People> getAllPeople();

  People getPeopleById(int id);
}

package fr.tykok.pokeapi.entities

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
public interface PokeApiObject

public interface PokeApiEndpointReference : PokeApiObject

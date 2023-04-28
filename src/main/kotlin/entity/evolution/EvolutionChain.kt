package entity.evolution

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource
import entity.items.Item

/**
 * Evolution chains are essentially family trees.
 * They start with the lowest stage within a family and detail evolution conditions for each as well as Pokémon they can evolve into up through the hierarchy.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Evolution">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#evolution-chains">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class EvolutionChain(

    /**
     * The identifier for this resource.
     */
    @JsonProperty("id")
    val id: Number,

    /**
     * The item that a Pokémon would be holding when mating that would trigger the egg hatching a baby Pokémon rather than a basic Pokémon.
     * @see NamedApiResource
     * @see Item
     */
    @JsonProperty("baby_trigger_item")
    val babyTriggerItem: NamedApiResource?,

    /**
     * The base chain link object. Each link contains evolution details for a Pokémon in the chain.
     * Each link references the next Pokémon in the natural evolution order.
     *
     * @see ChainLink
     */
    @JsonProperty("chain")
    val chain: ChainLink

) {
    override fun toString(): String {
        return "EvolutionChain(id=$id, babyTriggerItem=$babyTriggerItem, chain=$chain)"
    }
}

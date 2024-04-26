package org.shorts.battle;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.shorts.model.Status;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.TooManyTypesException;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.MagicGuard.MAGIC_GUARD;
import static org.shorts.model.items.HeavyDutyBoots.HEAVY_DUTY_BOOTS;

public class Trainer {

    private final String name;
    private final List<Pokemon> team;
    private boolean rocks = false;

    private int spikes = 0;

    private int toxicSpikes = 0;

    private boolean stickyWeb = false;

    public Trainer(String name, List<Pokemon> team) {
        this.name = Objects.requireNonNull(name, "Come on, tell the professor your name!");
        this.team = Objects.requireNonNull(team, "Constructor parameter \"team\" is null!");
        if (team.isEmpty()) {
            throw new IllegalArgumentException("You can't go into the tall grass without a Pokémon! (Team is empty.)");
        } else if (team.size() > 6) {
            throw new IllegalArgumentException("Trainers are permitted to carry a maximum of six Pokémon.");
        }
    }

    public boolean isRocks() {
        return rocks;
    }

    public void setRocks(boolean rocks) {
        this.rocks = rocks;
    }

    public int getSpikes() {
        return spikes;
    }

    public void setSpikes(int spikes) {
        this.spikes = spikes;
    }

    public int getToxicSpikes() {
        return toxicSpikes;
    }

    public void setToxicSpikes(int toxicSpikes) {
        this.toxicSpikes = toxicSpikes;
    }

    public boolean isStickyWeb() {
        return stickyWeb;
    }

    public void setStickyWeb(boolean stickyWeb) {
        this.stickyWeb = stickyWeb;
    }

    public String getName() {
        return name;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public Pokemon getLead() {
        return team.get(0);
    }

    public boolean hasLost() {
        return this.team.stream().allMatch(p -> p.getCurrentHP() == 0);
    }

    public void switchPokemon(int indexA, int indexB) {
        if (indexA != indexB) {
            Pokemon a = this.team.get(indexA);
            this.team.set(indexA, team.get(indexB));
            this.team.set(indexB, a);
        }
    }

    public void applyEntryHazards() {
        Pokemon pokemon = this.getLead();
        boolean boots = pokemon.getHeldItem().equals(HEAVY_DUTY_BOOTS);
        boolean magicGuard = pokemon.getAbility().equals(MAGIC_GUARD);

        if (!boots) {
            //Stealth Rock
            if (rocks && !magicGuard && faintedFromRocks(pokemon)) {
                return;
            }//TODO: What happens if rocks put the mon into Sitrus Berry range before Spikes activates? Does the healing happen before the spikes deal damage?
            if (pokemon.isGrounded()) {
                if (!magicGuard && faintedFromSpikes(pokemon)) {
                    return;
                }
                if (toxicSpikes > 0) {
                    if (pokemon.getTypes().contains(Type.POISON)) {
                        toxicSpikes = 0;
                    } else if (!pokemon.getTypes().contains(Type.STEEL)) {
                        pokemon.setStatus(toxicSpikes == 2 ? Status.TOXIC_POISON : Status.POISON);
                    }
                }
                if (stickyWeb) {
                    pokemon.changeSpeed(-1);
                }
            }
        } else { //A Grounded Poison-type Pokémon with Heavy-Duty Boots should still absorb Toxic Spikes
            if (pokemon.isGrounded() && toxicSpikes > 0 && pokemon.getTypes().contains(Type.POISON)) {
                absorbToxicSpikes(pokemon);
            }
        }
    }

    private boolean faintedFromRocks(Pokemon pokemon) {
        double multiplier = .125;
        try {
            multiplier *= Type.getMultiplier(Set.of(), Type.ROCK, pokemon.getTypes());
        } catch (TooManyTypesException e) {
            throw new IllegalArgumentException("Couldn't calculate Stealth Rock multiplier; see root exception.");
        }
        int damage = (int) (multiplier * pokemon.getMaxHP());
        pokemon.takeDamage(damage);
        return pokemon.hasFainted();
    }

    private boolean faintedFromSpikes(Pokemon pokemon) {
        if (spikes == 1) {
            int damage = pokemon.getMaxHP() / 8;
            pokemon.takeDamage(damage);
        }
        if (spikes == 2) {
            int damage = pokemon.getMaxHP() / 6;
            pokemon.takeDamage(damage);
        }
        if (spikes == 3) {
            int damage = pokemon.getMaxHP() / 4;
            pokemon.takeDamage(damage);
        }
        return pokemon.hasFainted();
    }

    public void addSpikes() {
        if (spikes < 3) {
            spikes++;
        }
    }

    public void addToxicSpikes() {
        if (toxicSpikes < 2) {
            toxicSpikes++;
        }
    }

    public void absorbToxicSpikes(Pokemon pokemon) {
        toxicSpikes = 0;
        System.out.println(pokemon.getSpeciesName() + " absorbed the toxic spikes!");
    }

    public void removeEntryHazards() {
        this.rocks = false;
        this.spikes = 0;
        this.toxicSpikes = 0;
        this.stickyWeb = false;
    }
}

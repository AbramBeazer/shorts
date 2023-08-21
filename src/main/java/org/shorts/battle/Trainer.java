package org.shorts.battle;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.shorts.model.Status;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.TooManyTypesException;
import org.shorts.model.types.Type;

public class Trainer {

    private final String name;
    private final Pokemon[] team = new Pokemon[6];
    private boolean rocks = false;

    private int spikes = 0;

    private int toxicSpikes = 0;

    private boolean stickyWeb = false;

    public Trainer(String name, List<Pokemon> team) {
        this.name = name;
        team.subList(0, Math.min(team.size(), 6)).toArray(this.team);
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

    public Pokemon[] getTeam() {
        return team;
    }

    public Pokemon getLead() {
        return team[0];
    }

    public boolean hasLost() {
        return Arrays.stream(this.team).allMatch(p -> p.getCurrentHP() == 0);
    }

    public void switchPokemon(int indexA, int indexB) {
        if (indexA != indexB) {
            Pokemon a = this.team[indexA];
            Pokemon b = this.team[indexB];
            this.team[indexB] = a;
            this.team[indexA] = b;
        }
    }

    public void applyEntryHazards() {
        Pokemon pokemon = team[0];
        boolean boots = pokemon.getHeldItem().getName().equals("Heavy-Duty Boots");
        boolean magicGuard = pokemon.getAbility().getName().equals("Magic Guard");

        if (!boots) {
            //Stealth Rock
            if (rocks) {
                double multiplier = .125;
                try {
                    multiplier *= Type.getMultiplier(Set.of(), Type.ROCK, team[0].getTypes());
                } catch (TooManyTypesException e) {
                    System.out.println("Couldn't calculate Stealth Rock multiplier; defaulting to 1/8");
                }
                int damage = (int) (multiplier * pokemon.getMaxHP());
                pokemon.takeDamage(damage);
            }
            if (pokemon.isGrounded()) {
                if (!magicGuard) {
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

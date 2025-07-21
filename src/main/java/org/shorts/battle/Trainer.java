package org.shorts.battle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import org.shorts.Main;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.TooManyTypesException;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.MagicGuard.MAGIC_GUARD;
import static org.shorts.model.items.HeavyDutyBoots.HEAVY_DUTY_BOOTS;

public class Trainer {

    private final String name;
    private final List<Pokemon> team;
    private final int activeMonsPerSide;
    private boolean rocks = false;

    private int spikes = 0;

    private int toxicSpikes = 0;

    private boolean stickyWeb = false;
    private int safeguardTurns = 0;
    private int reflectTurns = 0;
    private int lightScreenTurns = 0;
    private int auroraVeilTurns = 0;
    private int luckyChantTurns = 0;
    private int mistTurns = 0;

    public Trainer(String name, List<Pokemon> team) {
        this(name, team, team.size());
    }

    public Trainer(String name, List<Pokemon> team, int activeMonsPerSide) {
        if (activeMonsPerSide > 3 || activeMonsPerSide < 1) {
            throw new IllegalArgumentException("Only Single, Double, and Triple battles are supported.");
        }
        this.activeMonsPerSide = activeMonsPerSide;
        this.name = Objects.requireNonNull(name, "Come on, tell the professor your name!");
        this.team = Objects.requireNonNull(team, "Constructor parameter \"team\" is null!");
        if (team.isEmpty()) {
            throw new IllegalArgumentException("You can't go into the tall grass without a Pokémon! (Team is empty.)");
        } else if (team.size() > 6) {
            throw new IllegalArgumentException("Trainers are permitted to carry a maximum of six Pokémon.");
        }
    }

    public boolean hasRocks() {
        return rocks;
    }

    public void addRocks() {
        if (!rocks) {
            rocks = true;
            //TODO: Output stealth-rock-laying message
        }
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

    public boolean hasStickyWeb() {
        return stickyWeb;
    }

    public void addStickyWeb() {
        if (!stickyWeb) {
            this.stickyWeb = true;
            //TODO: Output sticky web message
        }
    }

    public int getSafeguardTurns() {
        return safeguardTurns;
    }

    public void setSafeguardTurns(int safeguardTurns) {
        this.safeguardTurns = safeguardTurns;
    }

    public int getReflectTurns() {
        return reflectTurns;
    }

    public void setReflectTurns(int reflectTurns) {
        this.reflectTurns = reflectTurns;
    }

    public int getLightScreenTurns() {
        return lightScreenTurns;
    }

    public void setLightScreenTurns(int lightScreenTurns) {
        this.lightScreenTurns = lightScreenTurns;
    }

    public int getAuroraVeilTurns() {
        return auroraVeilTurns;
    }

    public void setAuroraVeilTurns(int auroraVeilTurns) {
        this.auroraVeilTurns = auroraVeilTurns;
    }

    public int getLuckyChantTurns() {
        return luckyChantTurns;
    }

    public void setLuckyChantTurns(int luckyChantTurns) {
        this.luckyChantTurns = luckyChantTurns;
    }

    public int getMistTurns() {
        return mistTurns;
    }

    public void setMistTurns(int mistTurns) {
        this.mistTurns = mistTurns;
    }

    public String getName() {
        return name;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    @Deprecated
    public Pokemon getLead() {
        return team.get(0);
    }

    public List<Pokemon> getActivePokemon() {
        return team.subList(0, activeMonsPerSide);
    }

    public boolean hasLost() {
        return this.team.stream().allMatch(p -> p.getCurrentHP() == 0);
    }

    public void chooseLeads() throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<Integer> leadIndexes = new ArrayList<>();
        System.out.println("\n~~~PICK " + activeMonsPerSide + " TO SEND OUT~~~");

        for (int i = 0; i < team.size(); i++) {
            Pokemon teammate = team.get(i);

            System.out.println(
                (i + 1) + ")" + "\t" + teammate.getDisplayName() + "\t\tAbility: " + teammate.getAbility()
                    + "\t\tItem: " + teammate.getHeldItem());
        }
        System.out.println("\nLEADS:");

        while (leadIndexes.size() < activeMonsPerSide) {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > 6 || leadIndexes.contains(choice - 1)) {
                System.out.println("INVALID CHOICE -- must be " + activeMonsPerSide + " unique choices from 1 to 6.");
                continue;
            }

            leadIndexes.add(choice - 1);
            final Pokemon mon = team.get(choice - 1);
            System.out.println(
                (leadIndexes.size()) + ")" + "\t" + mon.getDisplayName() + "\tability: " + mon.getAbility() + "\titem: "
                    + mon.getHeldItem());
        }

        team.sort(Comparator.comparingInt((Pokemon p) -> {
            final int index = leadIndexes.indexOf(team.indexOf(p));
            if (index < 0) {
                return 1000;
            } else {
                return index;
            }
        }));
    }

    public boolean hasAvailableSwitch() {
        for (int i = activeMonsPerSide; i < team.size(); i++) {
            if (!team.get(i).hasFainted()) {
                return true;
            }
        }
        return false;
    }

    public void switchPokemon(int indexA, int indexB) {
        if (indexA < activeMonsPerSide && indexB < activeMonsPerSide) {
            throw new IllegalArgumentException("Cannot switch two Pokémon that are both in battle!");
        } else if (indexA != indexB) {
            Pokemon a = this.team.get(indexA);
            this.team.set(indexA, team.get(indexB));
            this.team.set(indexB, a);
        }
    }

    public void forceRandomSwitch(Pokemon pokemon) {
        //Why pass in a Pokemon instead of just 0? I'm thinking ahead to if I ever implement Double or Triple battles.
        if (hasAvailableSwitch()) {
            int knownIndex = team.indexOf(pokemon);
            int switchIndex = Main.RANDOM.nextInt(6) + activeMonsPerSide;
            while (switchIndex % team.size() == knownIndex || team.get(switchIndex % team.size()).hasFainted()) {
                switchIndex++;
            }
            switchPokemon(knownIndex, switchIndex % team.size());
        }
    }

    public void applyEntryHazards(Pokemon pokemon) {
        boolean boots = pokemon.getHeldItem().equals(HEAVY_DUTY_BOOTS);
        boolean magicGuard = pokemon.getAbility().equals(MAGIC_GUARD);

        if (!boots) {
            //Stealth Rock
            if (rocks && !magicGuard && faintedFromRocks(pokemon)) {
                return;
            } //TODO: What happens if rocks put the mon into Sitrus Berry range before Spikes activates? Does the healing happen before the spikes deal damage? I think the berry activates at the end of the turn.
            if (pokemon.isGrounded()) {
                if (!magicGuard && faintedFromSpikes(pokemon)) {
                    return;
                }
                if (toxicSpikes > 0) {
                    if (pokemon.getTypes().contains(Type.POISON)) {
                        absorbToxicSpikes(pokemon);
                    } else if (!pokemon.getTypes().contains(Type.STEEL)) {
                        if (toxicSpikes == 2) {
                            System.out.println(pokemon.getDisplayName() + " is badly poisoned by the Toxic Spikes!");
                            pokemon.setStatus(Status.createToxic());
                        } else if (toxicSpikes == 1) {
                            System.out.println(pokemon.getDisplayName() + " is poisoned by the Toxic Spikes!");
                            pokemon.setStatus(Status.POISON);
                        }
                    }
                }
                if (stickyWeb && pokemon.isDropPossible(StatEnum.SPEED)) {
                    System.out.println(pokemon.getDisplayName() + " is slowed by the Sticky Web!");
                    pokemon.changeStat(-1, StatEnum.SPEED);
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
            multiplier *= Type.getTypeMultiplier(Type.ROCK, pokemon.getTypes());
        } catch (TooManyTypesException e) {
            throw new IllegalArgumentException("Couldn't calculate Stealth Rock multiplier; see root exception.");
        }
        int damage = (int) (multiplier * pokemon.getMaxHP());
        pokemon.takeDamage(damage, String.format("Pointed stones dug into %s.", pokemon.getDisplayName()));
        return pokemon.hasFainted();
    }

    private boolean faintedFromSpikes(Pokemon pokemon) {
        if (spikes > 0) {
            final String message = String.format("Spikes dug into %s.", pokemon.getDisplayName());
            switch (spikes) {
                case 1:
                    pokemon.takeDamage(pokemon.getMaxHP() / 8);
                    break;
                case 2:
                    pokemon.takeDamage(pokemon.getMaxHP() / 6);
                    break;
                case 3:
                    pokemon.takeDamage(pokemon.getMaxHP() / 4);
                    break;
                default:
                    break;
            }
        }
        return pokemon.hasFainted();
    }

    public void addSpikes() {
        if (spikes < 3) {
            spikes++;
            //TODO: Output spike-laying message
        }
    }

    public void addToxicSpikes() {
        if (toxicSpikes < 2) {
            toxicSpikes++;
            //TODO: Output toxic-spike-laying message
        }
    }

    public void absorbToxicSpikes(Pokemon pokemon) {
        toxicSpikes = 0;
        System.out.println(pokemon.getDisplayName() + " absorbed the Toxic Spikes!");
    }

    public void removeEntryHazards() {
        this.rocks = false;
        this.spikes = 0;
        this.toxicSpikes = 0;
        this.stickyWeb = false;
    }

    public void decrementAllCounters() {
        countDownSafeguard();
        countDownReflect();
        countDownLightScreen();
        countDownAuroraVeil();
        countDownLuckyChant();
        countDownMist();
    }

    private void countDownSafeguard() {
        if (safeguardTurns > 0) {
            safeguardTurns--;
            if (safeguardTurns == 0) {
                System.out.println("Safeguard ended for " + getName() + "'s team.");
            }
        }
    }

    private void countDownReflect() {
        if (reflectTurns > 0) {
            reflectTurns--;
            if (reflectTurns == 0) {
                System.out.println("Reflect ended for " + getName() + "'s team.");
            }
        }
    }

    private void countDownLightScreen() {
        if (lightScreenTurns > 0) {
            lightScreenTurns--;
            if (lightScreenTurns == 0) {
                System.out.println("Light Screen ended for " + getName() + "'s team.");
            }
        }
    }

    private void countDownAuroraVeil() {
        if (auroraVeilTurns > 0) {
            auroraVeilTurns--;
            if (auroraVeilTurns == 0) {
                System.out.println("Aurora Veil ended for " + getName() + "'s team.");
            }
        }
    }

    private void countDownLuckyChant() {
        if (luckyChantTurns > 0) {
            luckyChantTurns--;
            if (luckyChantTurns == 0) {
                System.out.println("Lucky Chant ended for " + getName() + "'s team.");
            }
        }
    }

    private void countDownMist() {
        if (mistTurns > 0) {
            mistTurns--;
            if (mistTurns == 0) {
                System.out.println("Mist ended for " + getName() + "'s team.");
            }
        }
    }
}

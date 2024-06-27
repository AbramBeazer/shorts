package org.shorts.battle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.shorts.model.abilities.HailImmuneAbility;
import org.shorts.model.abilities.SandImmuneAbility;
import org.shorts.model.moves.MeFirst;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.shorts.Main.RANDOM;
import static org.shorts.model.abilities.MagicGuard.MAGIC_GUARD;
import static org.shorts.model.abilities.PoisonHeal.POISON_HEAL;
import static org.shorts.model.abilities.VictoryStar.VICTORY_STAR;
import static org.shorts.model.items.AssaultVest.ASSAULT_VEST;
import static org.shorts.model.items.SafetyGoggles.SAFETY_GOGGLES;
import static org.shorts.model.status.VolatileStatusType.CHOICE_LOCKED;
import static org.shorts.model.status.VolatileStatusType.DISABLED;
import static org.shorts.model.status.VolatileStatusType.HEAL_BLOCKED;

public class Battle {

    private Scanner scanner = new Scanner(System.in);
    private final int activeMonsPerSide;

    protected final Trainer playerOne;
    protected final Trainer playerTwo;
    protected int weatherTurns = Weather.INFINITE_WEATHER_DURATION;
    protected Weather weather = Weather.NONE;

    protected boolean weatherSuppressed = false;
    protected int terrainTurns = -1;
    protected Terrain terrain = Terrain.NONE;
    protected int fairyLockTurns;
    protected int gravityTurns;
    protected int magicRoomTurns;

    public Battle(Trainer player1, Trainer player2, int activeMonsPerSide) {
        this.playerOne = player1;
        this.playerTwo = player2;
        this.activeMonsPerSide = activeMonsPerSide;
    }

    public int getWeatherTurns() {
        return weatherTurns;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather, int turns) {
        this.weather = weather;
        this.weatherTurns = turns;
        if (!weatherSuppressed) {
            for (int i = 0; i < activeMonsPerSide; i++) {
                playerOne.getTeam().get(i).onWeatherChange(this);
                playerTwo.getTeam().get(i).onWeatherChange(this);
            }
        }
    }

    public boolean isWeatherSuppressed() {
        return weatherSuppressed;
    }

    public void setWeatherSuppressed(boolean weatherSuppressed) {
        this.weatherSuppressed = weatherSuppressed;
        for (int i = 0; i < activeMonsPerSide; i++) {
            playerOne.getTeam().get(i).onWeatherChange(this);
            playerTwo.getTeam().get(i).onWeatherChange(this);
        }
    }

    public int getTerrainTurns() {
        return terrainTurns;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain, int turns) {
        this.terrain = terrain;
        this.terrainTurns = turns;
        for (int i = 0; i < activeMonsPerSide; i++) {
            playerOne.getTeam().get(i).onTerrainChange(this);
            playerTwo.getTeam().get(i).onTerrainChange(this);
        }
    }

    public int getFairyLockTurns() {
        return fairyLockTurns;
    }

    public void setFairyLockTurns(int fairyLockTurns) {
        this.fairyLockTurns = fairyLockTurns;
    }

    public int getGravityTurns() {
        return gravityTurns;
    }

    public void setGravityTurns(int gravityTurns) {
        this.gravityTurns = gravityTurns;
    }

    public int getMagicRoomTurns() {
        return magicRoomTurns;
    }

    public void setMagicRoomTurns(int magicRoomTurns) {
        if (this.magicRoomTurns > 0) {
            magicRoomTurns = 0;
        } else {
            this.magicRoomTurns = magicRoomTurns;
        }
    }

    public void countDownWeather() {
        if (weatherTurns > 0) {
            weatherTurns--;
            if (weatherTurns == 0) {
                System.out.println(weather.getDeactivationMessage());
                setWeather(Weather.NONE, Weather.INFINITE_WEATHER_DURATION);
            }
        }
    }

    public void countDownTerrain() {
        if (terrainTurns > 0) {
            terrainTurns--;
            if (terrainTurns == 0) {
                //            System.out.println(terrain.getDeactivationMessage());
                setTerrain(Terrain.NONE, Terrain.INFINITE_TERRAIN_DURATION);
            }
        }

    }

    public void countDownFairyLock() {
        if (fairyLockTurns > 0) {
            fairyLockTurns--;
            if (fairyLockTurns == 0) {
                System.out.println("The effects of Fairy Lock ended.");
            }
        }
    }

    public void countDownGravity() {
        if (gravityTurns > 0) {
            gravityTurns--;
            if (gravityTurns == 0) {
                System.out.println("The effects of Gravity ended.");
            }
        }
    }

    public void countDownMagicRoom() {
        if (magicRoomTurns > 0) {
            magicRoomTurns--;
            if (magicRoomTurns == 0) {
                System.out.println("The effects of Magic Room ended.");
            }
        }
    }

    public Trainer getPlayerOne() {
        return playerOne;
    }

    public Trainer getPlayerTwo() {
        return playerTwo;
    }

    public int getActiveMonsPerSide() {
        return activeMonsPerSide;
    }

    public Trainer getCorrespondingTrainer(Pokemon pokemon) {
        if (this.playerOne.getTeam().contains(pokemon)) {
            return playerOne;
        } else {
            return playerTwo;
        }
    }

    public Trainer getOpposingTrainer(Trainer trainer) {
        if (this.playerOne == trainer) {
            return playerTwo;
        } else {
            return playerOne;
        }
    }

    public Trainer getOpposingTrainer(Pokemon pokemon) {
        if (this.playerOne.getTeam().contains(pokemon)) {
            return playerTwo;
        } else {
            return playerOne;
        }
    }

    public List<Pokemon> getOpposingActivePokemon(Pokemon pokemon) {
        if (this.playerOne.getTeam().contains(pokemon)) {
            return playerTwo.getTeam().subList(0, activeMonsPerSide);
        } else {
            return playerOne.getTeam().subList(0, activeMonsPerSide);
        }
    }

    public int getNumberOfActivePokemonWithVictoryStar(Trainer trainer) {
        int num = 0;
        for (int i = 0; i < activeMonsPerSide; i++) {
            if (trainer.getTeam().get(i).getAbility() == VICTORY_STAR) {
                num++;
            }
        }
        return num;
    }

    public void run() throws Exception {
        //Team Preview
        System.out.println("\n" + playerOne.getName() + "'s team:");
        StringBuilder teamOne = new StringBuilder();
        for (Pokemon mon : playerOne.getTeam()) {
            teamOne.append(mon.getDisplayName()).append("\t");
        }
        System.out.println(teamOne);

        System.out.println("\n" + playerTwo.getName() + "'s team:");
        StringBuilder teamTwo = new StringBuilder();
        for (Pokemon mon : playerTwo.getTeam()) {
            teamTwo.append(mon.getDisplayName()).append("\t");
        }
        System.out.println(teamTwo);

        if (playerOne.getTeam().size() > activeMonsPerSide) {
            playerOne.chooseLeads();
        }
        if (playerTwo.getTeam().size() > activeMonsPerSide) {
            playerTwo.chooseLeads();
        }

        while (!(playerOne.hasLost() || playerTwo.hasLost())) {
            takeTurns();
            endOfTurn();
        }
    }

    public void takeTurns() throws Exception {
        for (int i = 0; i < activeMonsPerSide; i++) {
            playerOne.getTeam().get(i).setMovedThisTurn(false);
            playerTwo.getTeam().get(i).setMovedThisTurn(false);
        }

        //take player input
        List<Turn> turns = new ArrayList<>();
        turns.addAll(pollPlayerInput(playerOne));
        turns.addAll(pollPlayerInput(playerTwo));

        turns.sort(Comparator.comparing(turn -> turn.getMove().getPriority(turn.getUser(), this) + turn.getMove()
            .getAbilityPriorityBonus(turn.getUser()), (a, b) -> Integer.compare(b, a)));

        Move moveOne = null;
        Move moveTwo = null;

        //PRIORITY 6

        //        handleSwitches(choiceOne, choiceTwo);
        //        if (choiceOne <= 4) {
        //            moveOne = playerOne.getLead().getMoves()[choiceOne - 1];
        //        }
        //        if (choiceTwo <= 4) {
        //            moveTwo = playerTwo.getLead().getMoves()[choiceTwo - 1];
        //        }

        int priorityOne = moveOne.getPriority(playerOne.getLead(), this);
        int abilityPriorityBonusOne = moveOne.getAbilityPriorityBonus(playerOne.getLead());
        int priorityTwo = moveTwo.getPriority(playerTwo.getLead(), this);
        int abilityPriorityBonusTwo = moveTwo.getAbilityPriorityBonus(playerTwo.getLead());

        // TODO:
        //  Dark-type Pokémon are now immune to opposing Pokémon's moves that gain priority due to Prankster, including moves called by another move.
        //  (such as Assist and Nature Power) and excluding moves that are repeated as a result of Prankster-affected Instruct
        //  or moves that occur earlier than their usual order due to Prankster-affected After You. Ally Dark-type Pokémon are still affected by the user's status moves.
        //  Dark-type Pokémon can still bounce moves back with Magic Bounce or Magic Coat; moves that have increased priority due to Prankster which are reflected
        //  by Magic Bounce or Magic Coat can affect Dark-type Pokémon, unless the Pokémon that bounced the move with Magic Coat also has Prankster.
        //  Moves that target all Pokémon (except Perish Song and Rototiller, which cannot affect Dark-type opponents if boosted by Prankster) and moves that set traps are successful regardless of the presence of Dark-type Pokémon.

        //TODO: Should I have an "onCalcPriority" method in Pokémon, Ability, and HeldItem? -- I can override getPriority in individual moves, at least.
        //        if (priorityOne > priorityTwo) {
        //            moveOne.determineTargetAndExecuteMove(playerOne.getLead(), playerTwo.getLead(), this);
        //            moveTwo.determineTargetAndExecuteMove(playerTwo.getLead(), playerOne.getLead(), this);
        //        } else if (priorityTwo > priorityOne) {
        //            moveTwo.determineTargetAndExecuteMove(playerTwo.getLead(), playerOne.getLead(), this);
        //            moveOne.determineTargetAndExecuteMove(playerOne.getLead(), playerTwo.getLead(), this);
        //        } else {
        //            double speedOne = playerOne.getLead().calculateSpeed();
        //            double speedTwo = playerTwo.getLead().calculateSpeed();
        //
        //            if (speedOne > speedTwo) {
        //                //playerOne.getLead() goes first
        //                moveOne.determineTargetAndExecuteMove(playerOne.getLead(), playerTwo.getLead(), this);
        //                moveTwo.determineTargetAndExecuteMove(playerTwo.getLead(), playerOne.getLead(), this);
        //            } else if (speedTwo > speedOne) {
        //                //playerTwo.getLead() goes first
        //                moveTwo.determineTargetAndExecuteMove(playerTwo.getLead(), playerOne.getLead(), this);
        //                moveOne.determineTargetAndExecuteMove(playerOne.getLead(), playerTwo.getLead(), this);
        //            } else {
        //                int rand = Main.RANDOM.nextInt(2);
        //                if (rand == 0) {
        //                    //playerOne.getLead() goes first
        //                    moveOne.determineTargetAndExecuteMove(playerOne.getLead(), playerTwo.getLead(), this);
        //                    moveTwo.determineTargetAndExecuteMove(playerTwo.getLead(), playerOne.getLead(), this);
        //                } else {
        //                    //playerTwo.getLead() goes first
        //                    moveTwo.determineTargetAndExecuteMove(playerTwo.getLead(), playerOne.getLead(), this);
        //                    moveOne.determineTargetAndExecuteMove(playerOne.getLead(), playerTwo.getLead(), this);
        //                }
        //            }
        //        }
    }

    //    private void handleSwitches(int choiceOne, int choiceTwo) {
    //        if (choiceOne > 4) {
    //            playerOne.getLead().setLastMoveUsed(null);
    //            playerOne.switchPokemon(0, choiceOne);
    //            playerOne.applyEntryHazards();
    //        }
    //
    //        if (choiceTwo > 4) {
    //            playerTwo.getLead().setLastMoveUsed(null);
    //            playerTwo.switchPokemon(0, choiceTwo);
    //            playerTwo.applyEntryHazards();
    //        }
    //
    //        //TODO: Neutralizing Gas should stop this from happening, right?
    //        if (choiceOne > 4) {
    //            playerOne.getLead().getAbility().afterEntry(playerOne.getLead(), this);
    //        }
    //
    //        if (choiceTwo > 4) {
    //            playerTwo.getLead().getAbility().afterEntry(playerTwo.getLead(), this);
    //        }
    //    }

    private List<Turn> pollPlayerInput(Trainer trainer) throws IOException {
        final List<Turn> turns = new ArrayList<>();
        for (int i = 0; i < trainer.getActivePokemon().size(); i++) {

            Pokemon pokemon = trainer.getActivePokemon().get(i);

            Set<Move> disabledMoves = new HashSet<>();
            Set<Move> invalidMoves = new HashSet<>();
            for (Move move : pokemon.getMoves()) {
                if (pokemon.hasVolatileStatus(DISABLED) && pokemon.getVolatileStatus(DISABLED).getMove().equals(move)) {
                    disabledMoves.add(move);
                    invalidMoves.add(move);
                }

                if ((pokemon.getHeldItem() == ASSAULT_VEST && (move.getCategory() == Move.Category.STATUS
                    && !(move instanceof MeFirst))) || (pokemon.hasVolatileStatus(CHOICE_LOCKED)
                    && !pokemon.getVolatileStatus(CHOICE_LOCKED).getMove().equals(move)) || move.getCurrentPP() <= 0) {
                    invalidMoves.add(move);
                }
            }
            int option = 1;

            Set<Move> movesToUse;
            System.out.println("\n\nWhat will " + pokemon.getDisplayName() + " do?");
            System.out.println("~~~MOVES~~~");
            if (invalidMoves.size() == pokemon.getMoves().length) {
                System.out.println("1. Struggle");
            } else {
                for (Move move : pokemon.getMoves()) {
                    String invalidLabel = "";
                    if (move.getCurrentPP() <= 0) {
                        invalidLabel = " Out of PP";
                    } else if (disabledMoves.contains(move)) {
                        invalidLabel =
                            " Disabled, " + pokemon.getVolatileStatus(DISABLED).getTurnsRemaining() + " turn(s) left.";
                    }
                    System.out.println(
                        option + ") " + move.getName() + "\t(" + move.getCurrentPP() + "/" + move.getMaxPP() + ")"
                            + invalidLabel);
                    option++;
                }
            }

            printBench(trainer);

            int choice;
            boolean choiceValid;
            //Choice is invalid if that Pokémon has fainted or if the move has no PP.
            do {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice <= 0 || choice > 9) {
                    choiceValid = false;
                } else if (choice <= 4 && invalidMoves.contains(pokemon.getMoves()[choice - 1])) {
                    choiceValid = false;
                } else if (choice > 4 && (trainer.getTeam().get(choice - 4).hasFainted() || pokemon.isTrapped(this))) {
                    choiceValid = false;
                } else {
                    choiceValid = true;

                    final Move move = pokemon.getMoves()[choice - 1];
                    final Range range = move.getRange(pokemon);
                    final List<Pokemon> possibleTargets = getPokemonWithinRange(pokemon, move.getRange(pokemon));
                    if (possibleTargets.size() > 1 && range.isPromptForTargetChoice()) {
                        int singleTargetIndex = promptChoiceOfTarget(pokemon, trainer, possibleTargets);
                        if (singleTargetIndex == 6) {
                            continue;
                        }
                        turns.add(new Turn(pokemon, move, singleTargetIndex));
                    } else {
                        turns.add(new Turn(pokemon, move));
                    }
                }

            } while (!choiceValid);
            if (choice >= 4 + activeMonsPerSide) {
                turns.add(new Turn(pokemon, null, choice));
            }
        }
        return turns;
    }

    public List<Pokemon> getPokemonWithinRange(Pokemon user, Range range) {
        final int activeMonsPerSide = getActiveMonsPerSide();
        final Trainer player = getCorrespondingTrainer(user);
        final Trainer opponent = getOpposingTrainer(user);
        final int userIndex = player.getTeam().indexOf(user);

        final List<Pokemon> possibleTargets = new ArrayList<>();

        switch (range) {
            case SELF:
            case VARIES:
                return List.of(user);
            case ALL:
            case BOTH_SIDES: //TODO: Figure out what needs to be different between ALL and BOTH_SIDES.
                final List<Pokemon> allPokemon = new ArrayList<>();
                allPokemon.addAll(opponent.getActivePokemon());
                allPokemon.addAll(player.getActivePokemon());
                return allPokemon;
            case OWN_PARTY:
                return player.getTeam();
            case OWN_SIDE:
                return player.getActivePokemon();
            case OTHER_SIDE:
                return opponent.getActivePokemon();
            case RANDOM_OPPONENT:
                return List.of(opponent.getActivePokemon().get(RANDOM.nextInt(activeMonsPerSide)));
            case ALL_ALLIES_EXCEPT_SELF:
                final List<Pokemon> allies = player.getActivePokemon();
                allies.remove(userIndex);
                return allies;
            case ALL_ADJACENT_OPPONENTS:
            case SINGLE_ADJACENT_OPPONENT:
                for (int i = 0; i < activeMonsPerSide; i++) {
                    if (Math.abs(i - userIndex) <= 1) {
                        possibleTargets.add(opponent.getTeam().get(i));
                    }
                }
                return possibleTargets;
            case RANDOM_ADJACENT_OPPONENT:
                for (int i = 0; i < activeMonsPerSide; i++) {
                    if (Math.abs(i - userIndex) <= 1) {
                        possibleTargets.add(opponent.getTeam().get(i));
                    }
                }
                return List.of(possibleTargets.get(RANDOM.nextInt(possibleTargets.size())));
            case ALL_ADJACENT:
            case SINGLE_ADJACENT_ANY:
                for (int i = 0; i < activeMonsPerSide; i++) {
                    if (Math.abs(i - userIndex) <= 1) {
                        possibleTargets.add(opponent.getTeam().get(i));
                        if (i != userIndex) {
                            possibleTargets.add(player.getTeam().get(i));
                        }
                    }
                }
                return possibleTargets;
            case SINGLE_ANY:
                for (int i = 0; i < activeMonsPerSide; i++) {
                    possibleTargets.add(opponent.getTeam().get(i));
                    if (i != userIndex) {
                        possibleTargets.add(player.getTeam().get(i));
                    }
                }
                return possibleTargets;
            case SINGLE_ADJACENT_ALLY:
                for (int i = 0; i < activeMonsPerSide; i++) {
                    if (Math.abs(i - userIndex) <= 1 && i != userIndex) {
                        possibleTargets.add(player.getTeam().get(i));
                    }
                }
                return possibleTargets;
            case SINGLE_SELF_OR_ADJACENT_ALLY:
                for (int i = 0; i < activeMonsPerSide; i++) {
                    if (Math.abs(i - userIndex) <= 1) {
                        possibleTargets.add(player.getTeam().get(i));
                    }
                }
                return possibleTargets;
            default:
                return possibleTargets;
        }
    }

    private int promptChoiceOfTarget(Pokemon pokemon, Trainer player, List<Pokemon> pokemonInRange) throws IOException {
        System.out.println("SELECT TARGET");
        List<Pokemon> opponents = getOpposingTrainer(player).getActivePokemon();
        List<Pokemon> selfAndAllies = player.getActivePokemon();
        for (Pokemon possibleTarget : pokemonInRange) {
            if (opponents.contains(possibleTarget)) {
                final int index = opponents.indexOf(possibleTarget);
                System.out.println(index + ") " + possibleTarget.getDisplayName() + " (opponent) ");
            } else {
                final String selfOrAlly = pokemon == possibleTarget ? " (self) " : " (ally) ";
                final int index = selfAndAllies.indexOf(possibleTarget);
                System.out.println((index + 3) + ") " + possibleTarget.getDisplayName() + selfOrAlly);
            }
        }

        System.out.println("6) Back");

        int choice;
        do {
            choice = Integer.parseInt(scanner.nextLine());
        } while (choice < 0 || choice >= 7);

        return choice;
    }

    public void promptSwitchCausedByUserMove(Trainer trainer) {
        if (trainer.hasAvailableSwitch()) {
            printBench(trainer);
            int choice;
            //Choice is invalid if that Pokémon has fainted or is already in battle
            do {
                choice = Integer.parseInt(scanner.nextLine());
            } while (choice <= 4 || choice > 9 || trainer.getTeam().get(choice - 4).hasFainted());
            trainer.switchPokemon(0, choice - 4);

            //TODO: Is this the right place to do this? At the beginning of a turn, if both trainers switch, the abilities don't trigger until both new Pokemon are in.
            //      This is probably fine if only one switch happens, but what if the attacker uses U-Turn and activates the opponent's Eject Button? Which switch happens first?
            trainer.getLead().afterEntry(this);
        }
    }

    private void printBench(Trainer trainer) {
        System.out.println("\n~~~SWITCH POKÉMON~~~");
        for (int i = activeMonsPerSide; i < trainer.getTeam().size(); i++) {
            Pokemon teammate = trainer.getTeam().get(i);

            String status = teammate.getStatus() == Status.NONE ? "" : teammate.getStatus().getType().name();
            System.out.println(
                (i + 4) + ")" + "\t(" + teammate.getPokedexEntry().getSpeciesName() + "\t(" + teammate.getCurrentHP()
                    + "/" + teammate.getMaxHP() + ")\t" + status + "\t" + teammate.getHeldItem());
        }
    }

    public void printField(Trainer player) {
        final Trainer opponent = this.getOpposingTrainer(player);
        final String blankSpace = "\t\t\t".repeat(Math.max(0, getActiveMonsPerSide()));

        final StringBuilder field = new StringBuilder().append("*")
            .append(blankSpace)
            .append("OPPONENT")
            .append(blankSpace)
            .append("*")
            .append("\n\n")
            .append("|");

        for (int i = 0; i < getActiveMonsPerSide(); i++) {
            final Pokemon mon = opponent.getTeam().get(i);
            field.append("\t\t").append(mon.getDisplayName());
            field.append("\t\t").append("|");
        }

        field.append("\n").append("|");
        for (int i = 0; i < getActiveMonsPerSide(); i++) {
            final Pokemon mon = opponent.getTeam().get(i);
            field.append("\t\t").append("HP: ").append(mon.getCurrentHP()).append("/").append(mon.getMaxHP());
            field.append("\t\t\t\t").append("|");
        }

        field.append("\n\n\n\n");

        field.append("\n").append("|");
        for (int i = 0; i < getActiveMonsPerSide(); i++) {
            final Pokemon mon = player.getTeam().get(i);
            field.append("\t\t").append(mon.getDisplayName());
            field.append("\t\t").append("|");
        }

        field.append("\n").append("|");
        for (int i = 0; i < getActiveMonsPerSide(); i++) {
            final Pokemon mon = player.getTeam().get(i);
            field.append("\t\t").append("HP: ").append(mon.getCurrentHP()).append("/").append(mon.getMaxHP());
            field.append("\t\t\t\t").append("|");
        }

        field.append("\n\n*").append(blankSpace).append("PLAYER").append(blankSpace).append("\t*");

        System.out.println(field);
    }

    private List<Pokemon> getAllActivePokemon() {
        List<Pokemon> allActiveMons = new ArrayList<>();
        allActiveMons.addAll(playerOne.getActivePokemon());
        allActiveMons.addAll(playerTwo.getActivePokemon());
        return allActiveMons;
    }

    private void endOfTurn() {
        List<Pokemon> activeMons = getAllActivePokemon();
        activeMons.sort(Comparator.comparing(Pokemon::calculateSpeed, Double::compareTo));
        for (Pokemon mon : activeMons) {
            if (!isWeatherSuppressed() && mon.getHeldItem() != SAFETY_GOGGLES && (
                (weather == Weather.SAND && !mon.getTypes().contains(Type.ROCK) && !mon.getTypes().contains(Type.GROUND)
                    && !mon.getTypes().contains(Type.STEEL) && !(mon.getAbility() instanceof SandImmuneAbility))
                    || (weather == Weather.HAIL && !mon.getTypes().contains(Type.ICE)
                    && !(mon.getAbility() instanceof HailImmuneAbility)))) {

                mon.takeDamage(mon.getMaxHP() / 16);
            }
        }

        for (Pokemon mon : activeMons) {
            mon.afterTurn(this);
        }

        //TODO: Where do I put LeechSeed, Curse, etc?

        for (Pokemon mon : activeMons) {
            if (mon.getAbility() != MAGIC_GUARD) {
                if (mon.getStatus() == Status.BURN) {
                    mon.takeDamage(mon.getMaxHP() / 16);
                } else if (mon.getAbility() != POISON_HEAL) {
                    if (mon.getStatus() == Status.POISON) {
                        mon.takeDamage(mon.getMaxHP() / 8);
                    } else if (mon.getStatus() == Status.TOXIC_POISON) {
                        mon.takeDamage((mon.getMaxHP() / 16) * mon.getStatus().getTurnsRemaining());
                    }
                } else if (mon.getAbility() == POISON_HEAL && mon.getStatus() == Status.POISON
                    || mon.getStatus() == Status.TOXIC_POISON && !mon.hasVolatileStatus(HEAL_BLOCKED)) {
                    mon.heal(mon.getMaxHP() / 8);
                }
            }
        }

        for (Pokemon mon : activeMons) {
            if (mon.getStatus() == Status.TOXIC_POISON) {
                mon.getStatus().incrementTurns();
            } else {
                mon.getStatus().decrementTurns();
            }
            mon.decrementVolatileStatusTurns();
        }

        //TODO: Check if weather stopping happens before or after taking hail/sand damage
        decrementAllCounters();

    }

    public void decrementAllCounters() {
        this.countDownWeather();
        this.countDownTerrain();
        this.countDownFairyLock();
        this.countDownGravity();
        this.countDownMagicRoom();
        playerOne.decrementAllCounters();
        playerTwo.decrementAllCounters();
    }
}

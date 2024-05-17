package org.shorts.battle;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.shorts.Main;
import org.shorts.model.moves.MeFirst;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.StatusMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;

import static org.shorts.model.items.AssaultVest.ASSAULT_VEST;
import static org.shorts.model.status.VolatileStatusType.CHOICE_LOCKED;
import static org.shorts.model.status.VolatileStatusType.DISABLED;

public class SingleBattle extends Battle {

    private Move moveOne;
    private Move moveTwo;

    public SingleBattle(Trainer player1, Trainer player2) {
        super(player1, player2);
    }

    @Override
    public void run() throws Exception {
        this.chooseLeads();
        while (!(playerOne.hasLost() || playerTwo.hasLost())) {
            takeTurns();
        }
    }

    @Override
    public void chooseLeads() throws IOException {
        int leadIndexOne = pollPlayerInput(playerOne);
        int leadIndexTwo = pollPlayerInput(playerTwo);
        handleSwitches(leadIndexOne, leadIndexTwo);
    }

    @Override
    public void takeTurns() throws Exception {
        //take player input
        int choiceOne = pollPlayerInput(playerOne);
        int choiceTwo = pollPlayerInput(playerTwo);
        Move moveOne = null;
        Move moveTwo = null;

        //PRIORITY 6
        handleSwitches(choiceOne, choiceTwo);
        if (choiceOne <= 4) {
            moveOne = playerOne.getLead().getMoves()[choiceOne - 1];
        }
        if (choiceTwo <= 4) {
            moveTwo = playerTwo.getLead().getMoves()[choiceTwo - 1];
        }

        int priorityOne = moveOne.getPriority(playerOne.getLead(), playerTwo.getLead(), this);
        int priorityTwo = moveTwo.getPriority(playerTwo.getLead(), playerOne.getLead(), this);

        //TODO: Should I have an "onCalcPriority" method in Pokémon, Ability, and HeldItem? -- I can override getPriority in individual moves, at least.
        if (priorityOne > priorityTwo) {
            moveOne.doMove(playerOne, playerTwo, this);
            moveTwo.doMove(playerTwo, playerOne, this);
        } else if (priorityTwo > priorityOne) {
            moveTwo.doMove(playerTwo, playerOne, this);
            moveOne.doMove(playerOne, playerTwo, this);
        } else {
            int speedOne = playerOne.getLead().calculateSpeed();
            int speedTwo = playerTwo.getLead().calculateSpeed();

            if (speedOne > speedTwo) {
                //playerOne goes first
                moveOne.doMove(playerOne, playerTwo, this);
                moveTwo.doMove(playerTwo, playerOne, this);
            } else if (speedTwo > speedOne) {
                //playerTwo goes first
                moveTwo.doMove(playerTwo, playerOne, this);
                moveOne.doMove(playerOne, playerTwo, this);
            } else {
                int rand = Main.RANDOM.nextInt(2);
                if (rand == 0) {
                    //playerOne goes first
                    moveOne.doMove(playerOne, playerTwo, this);
                    moveTwo.doMove(playerTwo, playerOne, this);
                } else {
                    //playerTwo goes first
                    moveTwo.doMove(playerTwo, playerOne, this);
                    moveOne.doMove(playerOne, playerTwo, this);
                }
            }
        }
    }

    private void handleSwitches(int choiceOne, int choiceTwo) {
        if (choiceOne > 4) {
            playerOne.switchPokemon(0, choiceOne);
            playerOne.applyEntryHazards();
        }

        if (choiceTwo > 4) {
            playerTwo.switchPokemon(0, choiceTwo);
            playerTwo.applyEntryHazards();
        }

        if (choiceOne > 4) {
            playerOne.getLead().getAbility().afterEntry(playerOne.getLead(), playerTwo.getLead(), this);
        }

        if (choiceTwo > 4) {
            playerTwo.getLead().getAbility().afterEntry(playerTwo.getLead(), playerOne.getLead(), this);
        }
    }

    private int pollPlayerInput(Trainer trainer) throws IOException {
        //TODO: Move the invalid checking to the beginning -- this lets us display only Struggle if PP is gone or the only available moves are invalid due to Disable, Taunt, Torment, etc.
        Pokemon pokemon = trainer.getLead();

        Set<Move> disabledMoves = new HashSet<>();
        Set<Move> invalidMoves = new HashSet<>();
        for (Move move : pokemon.getMoves()) {
            if (pokemon.hasVolatileStatus(DISABLED) && pokemon.getVolatileStatus(DISABLED).getMove().equals(move)) {
                disabledMoves.add(move);
                invalidMoves.add(move);
            }

            if ((pokemon.getHeldItem() == ASSAULT_VEST && (move instanceof StatusMove && !(move instanceof MeFirst)))
                || (pokemon.hasVolatileStatus(CHOICE_LOCKED) && !pokemon.getVolatileStatus(CHOICE_LOCKED)
                .getMove()
                .equals(move)) || move.getCurrentPP() <= 0) {
                invalidMoves.add(move);
            }
        }
        int option = 1;

        Set<Move> movesToUse;
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

        printTeam(trainer);

        int choice = 0;
        //Choice is invalid if that Pokémon has fainted or if the move has no PP.
        do {
            choice = System.in.read();
        } while (choice <= 0 || choice > 9 || trainer.getTeam().get(choice - 4).hasFainted() || invalidMoves.contains(
            pokemon.getMoves()[choice - 1]));
        return choice;
    }

    @Override
    public void promptSwitch(Trainer trainer) {
        try {
            if (trainer.hasAvailableSwitch()) {
                printTeam(trainer);
                int choice = 0;
                //Choice is invalid if that Pokémon has fainted or is already in battle
                do {
                    choice = System.in.read();
                } while (choice <= 4 || choice > 9 || trainer.getTeam().get(choice - 4).hasFainted());
                trainer.switchPokemon(0, choice - 4);

                //TODO: Is this the right place to do this? At the beginning of a turn, if both trainers switch, the abilities don't trigger until both new Pokemon are in.
                //      This is probably fine if only one switch happens, but what if the attacker uses U-Turn and activates the opponent's Eject Button? Which switch happens first?
                trainer.getLead()
                    .afterEntry(getPlayerOne() == trainer ? playerTwo.getLead() : playerOne.getLead(), this);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printTeam(Trainer trainer) {
        System.out.println("\n~~~SWITCH POKÉMON~~~");
        for (int i = 1; i < trainer.getTeam().size(); i++) {
            Pokemon teammate = trainer.getTeam().get(i);

            String status = teammate.getStatus() == Status.NONE ? "" : teammate.getStatus().getType().name();
            System.out.println((i + 4) + ")" + "\t(" + teammate.getSpeciesName() + "\t(" + teammate.getCurrentHP() + "/"
                + teammate.getMaxHP() + ")\t" + status + "\t" + teammate.getHeldItem());
        }
    }
}

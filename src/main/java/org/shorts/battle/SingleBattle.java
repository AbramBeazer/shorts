package org.shorts.battle;

import java.io.IOException;

import org.shorts.Main;
import org.shorts.model.Status;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

public class SingleBattle extends Battle {

    private final Trainer playerOne;
    private final Trainer playerTwo;

    private Move moveOne;
    private Move moveTwo;

    public SingleBattle(Trainer player1, Trainer player2) {
        this.playerOne = player1;
        this.playerTwo = player2;
    }

    @Override
    public void run() throws IOException {
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
    public void takeTurns() throws IOException {
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

        if (moveOne.getPriority() > moveTwo.getPriority()) {
            moveOne.doMove(playerOne, playerTwo, this);
            moveTwo.doMove(playerTwo, playerOne, this);
        } else if (moveTwo.getPriority() > moveOne.getPriority()) {
            moveTwo.doMove(playerTwo, playerOne, this);
            moveOne.doMove(playerOne, playerTwo, this);
        } else if (moveOne.getPriority() == moveTwo.getPriority()) {
            int speedOne = playerOne.getLead().getSpeed();
            int speedTwo = playerTwo.getLead().getSpeed();

            if (speedOne > speedTwo) {
                //playerOne goes first
            } else if (speedTwo > speedOne) {
                //playerTwo goes first
            } else {
                int rand = Main.RANDOM.nextInt(2);
                if (rand == 0) {
                    //playerOne goes first
                } else {
                    //playerTwo goes first
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
        Pokemon active = trainer.getLead();
        int option = 1;
        System.out.println("~~~MOVES~~~");
        for (Move move : active.getMoves()) {
            System.out.println(
                option + ") " + move.getName() + "\t(" + move.getCurrentPP() + "/" + move.getMaxPP() + ")");
            option++;
        }
        System.out.println("\n~~~SWITCH POKÉMON~~~");
        for (int i = 1; i < trainer.getTeam().size(); i++) {
            Pokemon teammate = trainer.getTeam().get(i);
            String status = teammate.getStatus() == Status.NONE ? "" : teammate.getStatus().name();
            System.out.println(
                (i + option) + ")" + "\t(" + teammate.getSpeciesName() + "\t(" + teammate.getCurrentHP() + "/"
                    + teammate.getMaxHP() + ")\t" + status + "\t" + teammate.getHeldItem());
        }
        return System.in.read();
    }
}

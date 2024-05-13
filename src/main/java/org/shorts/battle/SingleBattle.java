package org.shorts.battle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.shorts.Main;
import org.shorts.model.moves.MeFirst;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.StatusMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;

import static org.shorts.model.items.AssaultVest.ASSAULT_VEST;

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
            int speedOne = playerOne.getLead().getSpeed();
            int speedTwo = playerTwo.getLead().getSpeed();

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
        Pokemon pokemon = trainer.getLead();
        int option = 1;
        System.out.println("~~~MOVES~~~");
        List<Integer> invalidChoices = new ArrayList<>();
        for (Move move : pokemon.getMoves()) {
            boolean invalid = pokemon.getVolatileStatuses()
                .stream()
                .anyMatch(vs -> vs.getType() == VolatileStatus.VolatileStatusType.DISABLED && move.equals(vs.getMove()))
                || (pokemon.getHeldItem() == ASSAULT_VEST && (move instanceof StatusMove
                && !(move instanceof MeFirst)))
                || (pokemon.getVolatileStatuses()
                .stream()
                .anyMatch(vs -> vs.getType() == VolatileStatus.VolatileStatusType.CHOICE_LOCKED
                    && !move.equals(vs.getMove())))
                || move.getCurrentPP() <= 0;
            System.out.println(
                option + ") " + move.getName() + "\t(" + move.getCurrentPP() + "/" + move.getMaxPP() + ")" + (invalid
                    ? " DISABLED"
                    : ""));
            invalidChoices.add(option);
            option++;
        }
        System.out.println("\n~~~SWITCH POKÉMON~~~");
        for (int i = 1; i < trainer.getTeam().size(); i++) {
            Pokemon teammate = trainer.getTeam().get(i);
            String status = teammate.getStatus() == Status.NONE ? "" : teammate.getStatus().getType().name();
            System.out.println(
                (i + option) + ")" + "\t(" + teammate.getSpeciesName() + "\t(" + teammate.getCurrentHP() + "/"
                    + teammate.getMaxHP() + ")\t" + status + "\t" + teammate.getHeldItem());

            if (teammate.hasFainted()) {
                invalidChoices.add(i + option);
            }
        }
        int choice = 0;
        //Choice is invalid if that Pokémon has fainted or if the move has no PP.
        do {
            choice = System.in.read();
        } while (choice <= 0 || choice > 10 || invalidChoices.contains(choice));
        return choice;
    }
}

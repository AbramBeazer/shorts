package org.shorts.battle;

import java.io.IOException;

import org.shorts.Main;
import org.shorts.model.Status;
import org.shorts.model.abilities.Ability;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.moves.NullMove.NULL_MOVE;

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
        Move moveOne = NULL_MOVE;
        Move moveTwo = NULL_MOVE;

        //PRIORITY 6
        handleSwitches(choiceOne, choiceTwo);
        if (choiceOne <= 4) {
            moveOne = playerOne.getLead().getMoves()[choiceOne - 1];
        }
        if (choiceTwo <= 4) {
            moveTwo = playerTwo.getLead().getMoves()[choiceTwo - 1];
        }

        if (moveOne.getPriority() > moveTwo.getPriority()) {
            doMove(playerOne, moveOne, playerTwo);
            doMove(playerTwo, moveTwo, playerOne);
        } else if (moveTwo.getPriority() > moveOne.getPriority()) {
            doMove(playerTwo, moveTwo, playerOne);
            doMove(playerOne, moveOne, playerTwo);
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

    private void doMove(Trainer user, Move move, Trainer target) {
        if (!move.equals(NULL_MOVE)) {
            if (move.getMoveGroup().equals(Move.MoveGroup.STATUS) &&
                Type.getMultiplier(user.getLead().getTypes(), move.getType(), target.getLead().getTypes())
                    != Type.IMMUNE) {
                move.secondaryEffect(user.getLead(), target.getLead());
            } else {
                int damage = calculateDamage(user, move, target);
                target.getLead().takeDamage(damage);
                //TODO: Handle Sturdy, Focus Sash, Endure, etc.
                if (target.getLead().getCurrentHP() == 0) {
                    //TODO: Handle fainting and subsequent switch-in.
                }
                //TODO: Handle recoil damage
                //if(user.getLead().getCurrentHP() == 0) {
                //  //TODO: Handle fainting and subsequent switch-in.
                //}
            }
        }
    }

    private int calculateDamage(Trainer user, Move move, Trainer target) {
        int damage = 0;
        double typeMultiplier = Type.getMultiplier(
            user.getLead().getTypes(),
            move.getType(),
            target.getLead().getTypes());
        if (typeMultiplier == Type.IMMUNE) {
            //TODO: LOGGER.info("It didn't affect {}", target.getLead().getNickname());
            return 0;
        }
        //If the target isn't immune to the attack,
        return damage <= 0 ? 1 : (int) (damage * typeMultiplier);
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

        //TODO: apply effects of switch-in abilities like Drizzle, Intimidate, etc.
        if (choiceOne > 4) {
            Ability abilityOne = playerOne.getLead().getAbility();
        }

        if (choiceTwo > 4) {
            Ability abilityTwo = playerTwo.getLead().getAbility();
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
        for (int i = 1; i < trainer.getTeam().length; i++) {
            Pokemon teammate = trainer.getTeam()[i];
            String status = teammate.getStatus() == Status.NONE ? "" : teammate.getStatus().name();
            System.out.println(
                (i + option) + ")" + "\t(" + teammate.getSpeciesName() + "\t(" + teammate.getCurrentHP()
                    + "/"
                    + teammate.getMaxHP() + ")\t" + status + "\t" + teammate.getHeldItem());
        }
        return System.in.read();
    }
}

package org.shorts.battle;

import java.io.IOException;

import org.shorts.Main;
import org.shorts.model.Status;
import org.shorts.model.abilities.Ability;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.StatusMove;
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
            if (move instanceof StatusMove && target.getLead()
                .getAbility()
                .getName()
                .equals("Magic Bounce")) {
                target = user;
            }
            Pokemon userMon = user.getLead();
            Pokemon targetMon = target.getLead();

            if (move instanceof StatusMove) {
                move.trySecondaryEffect(userMon, targetMon, this);
            } else {
                int previousTargetHP = targetMon.getCurrentHP();
                Integer damage = calculateDamage(userMon, move, targetMon);
                userMon.beforeAttack(userMon, targetMon, this, damage, move.getType());
                targetMon.beforeHit(targetMon, userMon, this, damage, move.getType());
                targetMon.takeDamage(damage);
                targetMon.afterHit(targetMon, userMon, this, previousTargetHP);

                //TODO: Handle Endure, Destiny Bond, Perish Song, etc.

                if (targetMon.getCurrentHP() == 0) {
                    targetMon.afterFaint(targetMon, userMon, this);
                    userMon.afterKO(userMon, targetMon, this);
                    //TODO: Handle fainting and subsequent switch-in.

                }

                //TODO: Handle recoil damage
                //if(userMon.getCurrentHP() == 0) {
                //  //TODO: Handle fainting and subsequent switch-in.
                //}
            }
        }
    }

    private int calculateDamage(Pokemon userMon, Move move, Pokemon targetMon) {
        int damage = 0;
        double multiplier = Type.getMultiplier(
            userMon.getTypes(),
            move.getType(),
            targetMon.getTypes());

        if (multiplier == Type.IMMUNE) {
            //TODO: LOGGER.info("It didn't affect {}", target.getLead().getNickname());
            return 0;
        }
        //If the target isn't immune to the attack,
        return damage <= 0 ? 1 : (int) (damage * multiplier);
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
                (i + option) + ")" + "\t(" + teammate.getSpeciesName() + "\t(" + teammate.getCurrentHP() + "/"
                    + teammate.getMaxHP() + ")\t" + status + "\t" + teammate.getHeldItem());
        }
        return System.in.read();
    }
}

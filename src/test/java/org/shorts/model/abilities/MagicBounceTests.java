package org.shorts.model.abilities;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Trainer;
import org.shorts.battle.Turn;
import org.shorts.model.abilities.elementabsorb.FlashFire;
import org.shorts.model.moves.CorrosiveGas;
import org.shorts.model.moves.Growl;
import org.shorts.model.moves.Screech;
import org.shorts.model.moves.Spore;
import org.shorts.model.moves.ThunderWave;
import org.shorts.model.moves.WillOWisp;
import org.shorts.model.pokemon.Pokedex;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.abilities.MagicBounce.MAGIC_BOUNCE;
import static org.shorts.model.items.Leftovers.LEFTOVERS;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.status.StatusType.PARALYZE;
import static org.shorts.model.status.StatusType.SLEEP;
import static org.shorts.model.types.Type.ELECTRIC;
import static org.shorts.model.types.Type.GROUND;

class MagicBounceTests {

    private Pokemon user;
    private Pokemon bouncer;
    private List<Pokemon> targets;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        bouncer = getDummyPokemon();
        bouncer.setAbility(MAGIC_BOUNCE);
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testBounceStatusMove() {
        new Spore().execute(user, List.of(bouncer), new DummyBattle(user, bouncer));
        assertThat(bouncer.getStatus()).isEqualTo(Status.NONE);
        assertThat(user.getStatus().getType()).isEqualTo(SLEEP);
    }

    @Test
    void testBouncedStatusMoveStillSubjectToImmunities() {
        user.setAbility(new FlashFire());
        user.setTypes(Set.of(ELECTRIC));

        new ThunderWave().execute(user, List.of(bouncer), new DummyBattle(user, bouncer));
        assertThat(bouncer.getStatus()).isEqualTo(Status.NONE);
        assertThat(user.getStatus()).isEqualTo(Status.NONE);

        user.setTypes(Set.of(GROUND));

        new ThunderWave().execute(user, List.of(bouncer), new DummyBattle(user, bouncer));
        assertThat(bouncer.getStatus()).isEqualTo(Status.NONE);
        assertThat(user.getStatus()).isEqualTo(Status.NONE);

        new WillOWisp().execute(user, List.of(bouncer), new DummyBattle(user, bouncer));
        assertThat(bouncer.getStatus()).isEqualTo(Status.NONE);
        assertThat(user.getStatus()).isEqualTo(Status.NONE);
    }

    @Test
    void testBouncedMoveCannotBeReBounced() {
        user.setAbility(MAGIC_BOUNCE);

        new ThunderWave().execute(user, List.of(bouncer), new DummyBattle(user, bouncer));
        assertThat(bouncer.getStatus()).isEqualTo(Status.NONE);
        assertThat(user.getStatus().getType()).isEqualTo(PARALYZE);
    }

    @Test
    void testBouncedMoveUsesKeepsRangeButStartsFromBouncer() {
        Trainer player = new Trainer("Red", List.of(user, getDummyPokemon(), getDummyPokemon()));
        Trainer opponent = new Trainer("Green", List.of(getDummyPokemon(), bouncer, getDummyPokemon()));
        final Battle battle = new Battle(player, opponent, 3);
        assertThat(player.getTeam().get(0).getStageAttack()).isZero();
        assertThat(player.getTeam().get(1).getStageAttack()).isZero();
        assertThat(player.getTeam().get(2).getStageAttack()).isZero();
        assertThat(opponent.getTeam().get(0).getStageAttack()).isZero();
        assertThat(opponent.getTeam().get(1).getStageAttack()).isZero();
        assertThat(opponent.getTeam().get(2).getStageAttack()).isZero();

        final Turn turn = new Turn(user, new Growl());
        turn.takeTurn(battle);

        assertThat(player.getTeam().get(0).getStageAttack()).isEqualTo(-1);
        assertThat(player.getTeam().get(1).getStageAttack()).isEqualTo(-1);
        assertThat(player.getTeam().get(2).getStageAttack()).isEqualTo(-1);

        assertThat(opponent.getTeam().get(0).getStageAttack()).isZero();
        assertThat(opponent.getTeam().get(1).getStageAttack()).isZero();
        assertThat(opponent.getTeam().get(2).getStageAttack()).isEqualTo(-1);
    }

    @Test
    void testSingleTargetMoveIsBouncedBackOnlyAtUser() {
        Trainer player = new Trainer("Red", List.of(user, getDummyPokemon(), getDummyPokemon()));
        Trainer opponent = new Trainer("Green", List.of(getDummyPokemon(), bouncer, getDummyPokemon()));
        final Battle battle = new Battle(player, opponent, 3);
        assertThat(player.getTeam().get(0).getStageDefense()).isZero();
        assertThat(player.getTeam().get(1).getStageDefense()).isZero();
        assertThat(player.getTeam().get(2).getStageDefense()).isZero();
        assertThat(opponent.getTeam().get(0).getStageDefense()).isZero();
        assertThat(opponent.getTeam().get(1).getStageDefense()).isZero();
        assertThat(opponent.getTeam().get(2).getStageDefense()).isZero();

        final Turn turn = new Turn(user, new Screech(), 1);
        turn.takeTurn(battle);

        assertThat(player.getTeam().get(0).getStageDefense()).isEqualTo(-2);
        assertThat(player.getTeam().get(1).getStageDefense()).isZero();
        assertThat(player.getTeam().get(2).getStageDefense()).isZero();

        assertThat(opponent.getTeam().get(0).getStageDefense()).isZero();
        assertThat(opponent.getTeam().get(1).getStageDefense()).isZero();
        assertThat(opponent.getTeam().get(2).getStageDefense()).isZero();
    }

    @Test
    void testSingleTargetMoveIsNotBouncedIfBouncerIsNotTargeted() {
        Trainer player = new Trainer("Red", List.of(user, getDummyPokemon(), getDummyPokemon()));
        Trainer opponent = new Trainer("Green", List.of(getDummyPokemon(), bouncer, getDummyPokemon()));
        final Battle battle = new Battle(player, opponent, 3);
        assertThat(player.getTeam().get(0).getStageDefense()).isZero();
        assertThat(player.getTeam().get(1).getStageDefense()).isZero();
        assertThat(player.getTeam().get(2).getStageDefense()).isZero();
        assertThat(opponent.getTeam().get(0).getStageDefense()).isZero();
        assertThat(opponent.getTeam().get(1).getStageDefense()).isZero();
        assertThat(opponent.getTeam().get(2).getStageDefense()).isZero();

        final Turn turn = new Turn(user, new Screech(), 0);
        turn.takeTurn(battle);

        assertThat(player.getTeam().get(0).getStageDefense()).isZero();
        assertThat(player.getTeam().get(1).getStageDefense()).isZero();
        assertThat(player.getTeam().get(2).getStageDefense()).isZero();

        assertThat(opponent.getTeam().get(0).getStageDefense()).isEqualTo(-2);
        assertThat(opponent.getTeam().get(1).getStageDefense()).isZero();
        assertThat(opponent.getTeam().get(2).getStageDefense()).isZero();
    }

    @Test
    void testOneMoveCanActivateMultipleMagicBounces() {
        Trainer player = new Trainer("Red", List.of(getDummyPokemon(), user, getDummyPokemon()));
        Trainer opponent = new Trainer("Green", List.of(getDummyPokemon(), getDummyPokemon(), getDummyPokemon()));
        for (Pokemon mon : opponent.getTeam()) {
            mon.setAbility(MAGIC_BOUNCE);
        }
        final Battle battle = new Battle(player, opponent, 3);
        assertThat(player.getTeam().get(0).getStageAttack()).isZero();
        assertThat(player.getTeam().get(1).getStageAttack()).isZero();
        assertThat(player.getTeam().get(2).getStageAttack()).isZero();
        assertThat(opponent.getTeam().get(0).getStageAttack()).isZero();
        assertThat(opponent.getTeam().get(1).getStageAttack()).isZero();
        assertThat(opponent.getTeam().get(2).getStageAttack()).isZero();

        final Turn turn = new Turn(user, new Growl());
        turn.takeTurn(battle);

        assertThat(player.getTeam().get(0).getStageAttack()).isEqualTo(-2);
        assertThat(player.getTeam().get(1).getStageAttack()).isEqualTo(-3);
        assertThat(player.getTeam().get(2).getStageAttack()).isEqualTo(-2);

        assertThat(opponent.getTeam().get(0).getStageAttack()).isZero();
        assertThat(opponent.getTeam().get(1).getStageAttack()).isZero();
        assertThat(opponent.getTeam().get(2).getStageAttack()).isZero();
    }

    @Test
    void testMoveCanBeBouncedBackAtAlly() throws Exception {
        Pokedex.create();
        Trainer player = new Trainer("Red", List.of(user, bouncer));
        Trainer opponent = new Trainer("Green", List.of(getDummyPokemon(), getDummyPokemon()));
        for (int i = 0; i < 2; i++) {
            player.getTeam().get(i).setHeldItem(LEFTOVERS);
            opponent.getTeam().get(i).setHeldItem(LEFTOVERS);
        }
        final Battle battle = new Battle(player, opponent, 2);

        final Turn turn = new Turn(user, new CorrosiveGas());
        turn.takeTurn(battle);

        assertThat(player.getTeam().get(0).getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(player.getTeam().get(1).getHeldItem()).isEqualTo(LEFTOVERS);
        assertThat(opponent.getTeam().get(0).getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(opponent.getTeam().get(0).getHeldItem()).isEqualTo(NO_ITEM);
    }

    @Test
    void testMoveIsRedirectedBeforeMagicBounceApplies() {
        assertThat(false).isTrue();
    }
}

package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

public class NullifyingAbility extends Ability {

    private NullifyingAbility(String name) {
        super(name);
    }

    public static final NullifyingAbility MOLD_BREAKER = new NullifyingAbility("Mold Breaker");
    public static final NullifyingAbility TERAVOLT = new NullifyingAbility("Teravolt");
    public static final NullifyingAbility TURBOBLAZE = new NullifyingAbility("Turboblaze");

    @Override
    public double beforeAttack(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        super.beforeAttack(self, opponent, battle, move);
        opponent.addVolatileStatus(VolatileStatus.ABILITY_IGNORED);
        return 1;
    } //TODO: Not sure if this needs to be beforeAttack or onMovePowerCalc. As early as possible for the purposes of ability ignoring.

    @Override
    public void afterAttack(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        super.afterAttack(self, opponent, battle, move);
        opponent.removeVolatileStatus(VolatileStatusType.ABILITY_IGNORED);
    }

    //    Generation IV onward	Battle Armor, Clear Body, Damp, Dry Skin, Filter, Flash Fire, Flower Gift, Heatproof, Hyper Cutter, Immunity, Inner Focus, Insomnia, Keen Eye, Leaf Guard, Levitate, Lightning Rod, Limber, Magma Armor, Marvel Scale, Motor Drive, Oblivious, Own Tempo, Sand Veil, Shell Armor, Shield Dust, Simple, Snow Cloak, Solid Rock, Soundproof, Sticky Hold, Storm Drain, Sturdy, Suction Cups, Tangled Feet, Thick Fat, Unaware, Vital Spirit, Volt Absorb, Water Absorb, Water Veil, White Smoke, Wonder Guard
    //    Generation V onward	Big Pecks, Contrary, Friend Guard, Heavy Metal, Light Metal, Magic Bounce, Multiscale, Sap Sipper, Telepathy, Wonder Skin
    //    Generation VI onward	Aura Break, Aroma Veil, Bulletproof, Dark Aura[a], Fairy Aura[a], Flower Veil, Fur Coat, Overcoat, Sweet Veil
    //    Generation VII onward	Dazzling, Disguise, Fluffy, Queenly Majesty, Water Bubble
    //    Generation VIII onward	Ice Scales, Ice Face, Mirror Armor, Pastel Veil, Punk Rock
    //    Generation IX	Armor Tail, Earth Eater, Guard Dog, Good as Gold, Illuminate[b], Mind's Eye, Purifying Salt, Tera Shell, Tablets of Ruin, Thermal Exchange, Well-Baked Body, Vessel of Ruin, Wind Rider
    //    From Generation VIII onward, Dark Aura and Fairy Aura can no longer be ignored; however, Aura Break still can be.
    //    Although Illuminate was introduced in Generation III, it did not have an in-battle effect until version 2.0.1 of Pokémon Scarlet and Violet, which is when it became ignorable.
    //    Despite being mechanically similar to ignorable Abilities, Magic Guard, Comatose, Shields Down, Full Metal Body, Shadow Shield, and Prism Armor cannot be ignored in any game.

}

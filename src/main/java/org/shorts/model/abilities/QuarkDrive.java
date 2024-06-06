package org.shorts.model.abilities;

import java.util.ArrayList;
import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.items.BoosterEnergy.BOOSTER_ENERGY;
import static org.shorts.model.items.NoItem.NO_ITEM;

public class QuarkDrive extends Ability implements UnsuppressableAbility {

    protected static final double MULTIPLIER = 1.3;
    protected static final double SPEED_MULTIPLIER = 1.5;
    private StatEnum boostedStat;
    private boolean activatedByTerrain = false;

    public QuarkDrive() {
        super("Quark Drive");
    }

    @Override
    public void beforeSwitchOut(Pokemon self, Pokemon opponent, Battle battle) {
        boostedStat = null;
        activatedByTerrain = false;
    }

    @Override
    public void afterEntry(Pokemon self, Battle battle) {
        checkActivation(self, battle);
    }

    @Override
    public void onGainAbility(Pokemon self, Battle battle) {
        checkActivation(self, battle);
    }

    @Override
    public void onTerrainChange(Pokemon self, Battle battle) {
        checkActivation(self, battle);
    }

    protected void checkActivation(Pokemon self, Battle battle) {
        if (battle.getTerrain() == Terrain.ELECTRIC) {
            activatedByTerrain = true;
            grantStatBoost(self);
        } else if (self.getHeldItem() == BOOSTER_ENERGY) {
            //TODO: Check if this interacts with effects that keep other Pokemon from using their item.
            activatedByTerrain = false;
            boostedStat = null;
            grantStatBoost(self);
            self.setConsumedItem(self.getHeldItem());
            self.setHeldItem(NO_ITEM);
        }
    }

    public StatEnum getBoostedStat() {
        return boostedStat;
    }

    public boolean isActivatedByTerrain() {
        return activatedByTerrain;
    }

    //TODO: Should we check which stat is highest every turn?
    protected void grantStatBoost(Pokemon user) {
        List<Double> values = new ArrayList<>();

        final double attack = user.getStatApplyStage(StatEnum.ATK);
        values.add(attack);
        final double defense = user.getStatApplyStage(StatEnum.DEF);
        values.add(defense);
        final double specialAttack = user.getStatApplyStage(StatEnum.SPATK);
        values.add(specialAttack);
        final double specialDefense = user.getStatApplyStage(StatEnum.SPDEF);
        values.add(specialDefense);
        final double speed = user.getStatApplyStage(StatEnum.SPEED);
        values.add(speed);

        double highestValue = values.stream().max(Double::compareTo).orElse(attack);
        if (highestValue == attack) {
            boostedStat = StatEnum.ATK;
        } else if (highestValue == defense) {
            boostedStat = StatEnum.DEF;
        } else if (highestValue == specialAttack) {
            boostedStat = StatEnum.SPATK;
        } else if (highestValue == specialDefense) {
            boostedStat = StatEnum.SPDEF;
        } else if (highestValue == speed) {
            boostedStat = StatEnum.SPEED;
        }
    }

    @Override
    public double onCalculateAttack(Pokemon self) {
        return boostedStat == StatEnum.ATK ? MULTIPLIER : 1;
    }

    @Override
    public double onCalculateDefense(Pokemon self) {
        return boostedStat == StatEnum.DEF ? MULTIPLIER : 1;
    }

    @Override
    public double onCalculateSpecialAttack(Pokemon self) {
        return boostedStat == StatEnum.SPATK ? MULTIPLIER : 1;
    }

    @Override
    public double onCalculateSpecialDefense(Pokemon self) {
        return boostedStat == StatEnum.SPDEF ? MULTIPLIER : 1;
    }

    @Override
    public double onCalculateSpeed(Pokemon self) {
        return boostedStat == StatEnum.SPEED ? SPEED_MULTIPLIER : 1;
    }

}

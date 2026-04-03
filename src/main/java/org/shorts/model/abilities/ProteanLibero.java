package org.shorts.model.abilities;

import java.util.List;
import java.util.Set;

import org.shorts.model.moves.Move;
import org.shorts.model.moves.recoil.Struggle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.TypeChangeStatus;
import org.shorts.model.status.VolatileStatusType;

public class ProteanLibero extends Ability {

    private boolean activated;

    private ProteanLibero(String name) {
        super(name);
        activated = false;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public void beforeAttack(Pokemon self, List<Pokemon> opponents, Move move) {
        if (!isActivated() && !self.isTera()
            && !self.getTypes().equals(Set.of(move.getType())) && move != Struggle.STRUGGLE) {

            self.addVolatileStatus(new TypeChangeStatus(VolatileStatusType.TYPE_CHANGE, -1, self.getTypes()));
            self.setTypes(Set.of(move.getType()));
            setActivated(true);
        }
    }

    public static ProteanLibero createProtean() {
        return new ProteanLibero("Protean");
    }

    public static ProteanLibero createLibero() {
        return new ProteanLibero("Libero");
    }
}

package org.shorts.model.abilities;

public class ProteanLibero extends Ability {
    private boolean activated;

    private ProteanLibero(String name){
        super(name);
        activated = false;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }



    private static final ProteanLibero PROTEAN = new ProteanLibero("Protean");
    private static final ProteanLibero LIBERO = new ProteanLibero("Libero");
}

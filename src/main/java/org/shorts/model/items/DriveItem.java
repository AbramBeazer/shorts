package org.shorts.model.items;

import org.shorts.model.types.Type;

public class DriveItem extends HeldItem {

    private Type type;

    private DriveItem(String name, Type type) {
        super(name, 70);
        this.type = type;
    }

    public static final DriveItem SHOCK_DRIVE = new DriveItem("Shock Drive", Type.ELECTRIC);
    public static final DriveItem BURN_DRIVE = new DriveItem("Burn Drive", Type.FIRE);
    public static final DriveItem CHILL_DRIVE = new DriveItem("Chill Drive", Type.ICE);
    public static final DriveItem DOUSE_DRIVE = new DriveItem("Douse Drive", Type.WATER);

    //TODO: Implement
}

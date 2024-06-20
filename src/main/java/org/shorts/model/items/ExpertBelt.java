package org.shorts.model.items;

public class ExpertBelt extends HeldItem {

    private ExpertBelt() {
        super("Expert Belt", 10);
    }

    public static final ExpertBelt EXPERT_BELT = new ExpertBelt();
}

package org.shorts.model.moves;

public enum Range {
    NORMAL_SINGLE_ADJACENT_ANY(true),
    SINGLE_ANY(true),
    SINGLE_ADJACENT_OPPONENT(true),
    SINGLE_ADJACENT_ALLY(true),
    SINGLE_SELF_OR_ADJACENT_ALLY(true),
    SELF(false),
    OWN_PARTY(false),
    ALL_ALLIES_EXCEPT_SELF(false),
    RANDOM_ADJACENT_OPPONENT(false),
    RANDOM_OPPONENT(false),
    ALL_ADJACENT_OPPONENTS(false),
    ALL_ADJACENT(false),
    ALL(false),
    BOTH_SIDES(false),
    OTHER_SIDE(false),
    OWN_SIDE(false),
    VARIES(false);

    private boolean promptForTargetChoice;

    Range(boolean promptForTargetChoice) {
        this.promptForTargetChoice = promptForTargetChoice;
    }

    public boolean isPromptForTargetChoice() {
        return promptForTargetChoice;
    }
}

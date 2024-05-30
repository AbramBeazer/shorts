package org.shorts.model.status;

public class HelpingHandStatus extends VolatileStatus {

    private int levels = 1;

    public HelpingHandStatus() {
        super(VolatileStatusType.HELPING_HAND, 1);
    }

    public int getLevels() {
        return levels;
    }

    public void stackHelpingHand() {
        if (levels < 3) {
            levels++;
        }
    }
}

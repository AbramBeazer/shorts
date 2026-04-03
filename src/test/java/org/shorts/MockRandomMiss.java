package org.shorts;

import java.util.Random;

public class MockRandomMiss extends Random {

    @Override
    public int nextInt(int bound) {
        return bound;
    }

    private MockRandomMiss() {
    }

    public static final MockRandomMiss ALWAYS_MISS = new MockRandomMiss();
}

package org.shorts;

import java.util.Random;

public class MockRandomReturnMax extends Random {

    @Override
    public int nextInt(int bound) {
        return Integer.MAX_VALUE;
    }

    public static final MockRandomReturnMax MAX_RANDOM = new MockRandomReturnMax();
}

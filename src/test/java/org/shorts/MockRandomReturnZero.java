package org.shorts;

import java.util.Random;

public class MockRandomReturnZero extends Random {

    @Override
    public int nextInt(int bound) {
        return 0;
    }

    private MockRandomReturnZero() {
    }

    public static final MockRandomReturnZero ZERO_RANDOM = new MockRandomReturnZero();
}

package org.shorts;

import java.util.Random;

public class TestRandom extends Random {

    @Override
    public int nextInt(int bound) {
        return 0;
    }
}

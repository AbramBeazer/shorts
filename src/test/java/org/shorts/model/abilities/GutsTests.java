package org.shorts.model.abilities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GutsTests {

    @Test
    void testDoesNotActivateIfUsesFireMoveAndThaws() {
        assertThat(true).isFalse();
        //Is the solution here just that I shouldn't apply a boost for freeze? It's not like any Pokémon can move while frozen anyway.
    }
}

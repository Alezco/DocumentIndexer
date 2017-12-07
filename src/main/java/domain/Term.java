package domain;

import java.util.List;

public class Term {

    public final String token;

    public final List<Integer> positions;

    public final float frequency;

    public Term(final String token, final List<Integer> positions, final float frequency) {
        this.token = token;
        this.positions = positions;
        this.frequency = frequency;
    }
}

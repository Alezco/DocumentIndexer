package domain;

import java.util.List;

/**
 * Created by Guillaume on 06/12/2017.
 */
public class Term {

    private final String token;

    private final List<Integer> positions;

    private final float frequency;

    public String getToken() {
        return token;
    }

    public List<Integer> getPositions() {
        return positions;
    }

    public float getFrequency() {
        return frequency;
    }

    public Term(final String token, final List<Integer> positions, final float frequency) {
        this.token = token;
        this.positions = positions;
        this.frequency = frequency;
    }


}

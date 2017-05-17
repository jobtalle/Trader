package game.stock;

import noise.CubicNoise;

final class StockNoiseOctave {
    private CubicNoise noise;
    private int influence;

    StockNoiseOctave(final int frequency, final int influence)
    {
        this.influence = influence;

        noise = new CubicNoise((int) (Math.random() * Integer.MAX_VALUE), frequency);
    }

    int sample(final float n)
    {
        return (int) (influence * noise.sample(n));
    }
}

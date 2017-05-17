package game.stock;

import java.util.Stack;

final class StockSampler {
    private Stack<StockNoiseOctave> octaves = new Stack<>();

    private int n = 0;

    StockSampler()
    {
        octaves.push(new StockNoiseOctave(20, 20000));
        octaves.push(new StockNoiseOctave(15, 10000));
        octaves.push(new StockNoiseOctave(10, 6000));
        octaves.push(new StockNoiseOctave(2, 1000));
    }

    long sample()
    {
        int result = 6000;

        for(final StockNoiseOctave octave : octaves)
            result += octave.sample(n);

        ++n;

        return result;
    }
}

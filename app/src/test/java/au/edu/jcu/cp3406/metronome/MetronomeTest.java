package au.edu.jcu.cp3406.metronome;

import org.junit.Test;

import static org.junit.Assert.*;

/** Check the Metronome class is operating properly */
public class MetronomeTest {

    /** Check the constructor sets values correctly */
    @Test
    public void testConstructor() {
        Metronome metronome = new Metronome(8, 1, 120);
        assertEquals(metronome.getBeatsPerMeasure(), 8);
        assertEquals(metronome.getCurrentBeat(), 1);
        assertEquals(metronome.getTempo(), 120);
    }

    /** Check the tick() function increments and wraps correctly */
    @Test
    public void testTicker() {
        Metronome metronome = new Metronome(8, 1, 120);
        metronome.tick();
        assertEquals(metronome.getCurrentBeat(), 2);
        for (int i = 0; i < 7; ++i) {
            metronome.tick();
        }
        assertEquals(metronome.getCurrentBeat(), 1);
    }

    /** Check the delay between ticks in milliseconds is calculated correctly */
    @Test
    public void testDelay() {
        Metronome metronome = new Metronome(8, 1, 157);
        int delay = metronome.getDelay();
        assertEquals(delay, 382);
    }
}
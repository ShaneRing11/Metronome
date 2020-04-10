package au.edu.jcu.cp3406.metronome;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MetronomeTest {
    @Test
    public void testConstructor() {
        Metronome metronome = new Metronome(8, 1, 120);
        assertEquals(metronome.getBeats(), 8);
        assertEquals(metronome.getCurrentBeat(), 1);
        assertEquals(metronome.getTempo(), 120);
    }

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

    @Test
    public void testDelay() {
        Metronome metronome = new Metronome(8, 1, 157);
        int delay = metronome.getDelay();
        assertEquals(delay, 382);
    }
}
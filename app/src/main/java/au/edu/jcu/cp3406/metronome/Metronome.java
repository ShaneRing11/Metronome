package au.edu.jcu.cp3406.metronome;

public class Metronome {

    private int beats;
    private int currentBeat;

    public Metronome(int beats) {
        this.beats = beats;
        currentBeat = 1;
    }

    public void tick() {
        if (currentBeat < beats) {
            ++currentBeat;
        } else {
            currentBeat = 1;
        }
    }

    public int getBeats() {
        return beats;
    }

    public int getCurrentBeat() {
        return currentBeat;
    }

    public int getDelay(float tempo) {
        float beatsPerSecond = tempo / 60;
        return (int) (1000 / beatsPerSecond);
    }

    public void reset() {
        currentBeat = 1;
    }
}

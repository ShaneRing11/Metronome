package au.edu.jcu.cp3406.metronome;

public class Metronome {

    private int beats;
    private int currentBeat;
    private int tempo;

    public Metronome(int beats, int currentBeat, int tempo) {
        this.beats = beats;
        this.currentBeat = currentBeat;
        this.tempo = tempo;
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

    public int getTempo() {
        return tempo;
    }

    public int getDelay() {
        float beatsPerSecond = (float) tempo / 60;
        return (int) (1000 / beatsPerSecond);
    }

    public void reset() {
        currentBeat = 1;
    }
}

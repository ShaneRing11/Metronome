package au.edu.jcu.cp3406.metronome;

public class Metronome {

    private int tempo;
    private int beats;
    private int currentBeat;

    public Metronome(int tempo, int beats) {
        this.tempo = tempo;
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

    public int getTempo() {
        return tempo;
    }

    public int getBeats() {
        return beats;
    }

    public int getCurrentBeat() {
        return currentBeat;
    }
}

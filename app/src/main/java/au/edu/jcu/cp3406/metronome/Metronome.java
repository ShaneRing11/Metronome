package au.edu.jcu.cp3406.metronome;

class Metronome {

    private int beats;
    private int currentBeat;
    private int tempo;

    Metronome(int beats, int currentBeat, int tempo) {
        this.beats = beats;
        this.currentBeat = currentBeat;
        this.tempo = tempo;
    }

    void tick() {
        if (currentBeat < beats) {
            ++currentBeat;
        } else {
            currentBeat = 1;
        }
    }

    int getTempo() {
        return tempo;
    }

    void setTempo(int tempo) {
        this.tempo = tempo;
    }

    int getBeats() {
        return beats;
    }

    void setBeats(int beats) {
        this.beats = beats;
    }

    int getCurrentBeat() {
        return currentBeat;
    }
    int getDelay() {
        float beatsPerSecond = (float) tempo / 60;
        return (int) (1000 / beatsPerSecond);
    }

    void reset() {
        currentBeat = 1;
    }
}
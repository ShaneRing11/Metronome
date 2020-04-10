package au.edu.jcu.cp3406.metronome;

class Metronome {

    private int beatsPerMeasure;
    private int currentBeat;
    private int tempo;

    Metronome(int beats, int currentBeat, int tempo) {
        this.beatsPerMeasure = beats;
        this.currentBeat = currentBeat;
        this.tempo = tempo;
    }

    void tick() {
        if (currentBeat < beatsPerMeasure) {
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

    int getBeatsPerMeasure() {
        return beatsPerMeasure;
    }

    void setBeatsPerMeasure(int beatsPerMeasure) {
        this.beatsPerMeasure = beatsPerMeasure;
    }

    int getCurrentBeat() {
        return currentBeat;
    }

    /** Convert the tempo value into milliseconds between ticks */
    int getDelay() {
        float beatsPerSecond = (float) tempo / 60;
        return (int) (1000 / beatsPerSecond);
    }

    void reset() {
        currentBeat = 1;
    }
}
package au.edu.jcu.cp3406.metronome;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Metronome metronome;
    private TextView currentBeat;
    private TextView tempo;
    private Button toggle;
    private boolean isRunning;
    private Handler handler;
    private int delay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        final int downbeat = soundPool.load(this, R.raw.downbeat, 1);
        final int beat = soundPool.load(this, R.raw.beat, 1);
        metronome = new Metronome(4);
        delay = metronome.getDelay(120);

        toggle = findViewById(R.id.toggle);
        currentBeat = findViewById(R.id.currentBeat);
        currentBeat.setText(Integer.toString(metronome.getCurrentBeat()));
        tempo = findViewById(R.id.tempo);
        tempo.setText(R.string.default_tempo);

        isRunning = false;
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    currentBeat.setText(Integer.toString(metronome.getCurrentBeat()));
                    if (metronome.getCurrentBeat() == 1) {
                        soundPool.play(downbeat, 1, 1, 1, 0, 1);
                    } else {
                        soundPool.play(beat, 1, 1, 1, 0, 1);
                    }
                    metronome.tick();
                }
                handler.postDelayed(this, delay);
            }
        });
    }

    public void toggleClicked(View view) {
        if (isRunning) {
            disableMetronome();
        } else {
            enableMetronome();
        }
    }

    private void enableMetronome() {
        //TODO move runnable into here
        isRunning = true;
        toggle.setText((getText(R.string.stop)));
    }

    private void disableMetronome() {
        //TODO make runnable stop here
        isRunning = false;
        toggle.setText((getText(R.string.start)));
        metronome.reset();
        currentBeat.setText(Integer.toString(metronome.getCurrentBeat()));
    }

    //TODO add settings activity
}

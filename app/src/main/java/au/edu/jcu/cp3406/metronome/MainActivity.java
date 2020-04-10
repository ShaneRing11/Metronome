package au.edu.jcu.cp3406.metronome;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Metronome metronome;
    private TextView currentBeat;
    private TextView metronomeDetails;
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

        toggle = findViewById(R.id.toggle);
        currentBeat = findViewById(R.id.currentBeat);
        metronomeDetails = findViewById(R.id.metronomeDetails);

        if (savedInstanceState == null) {
            metronome = new Metronome(4, 1, 120);
            metronomeDetails.setText(String.format(getResources().getString(R.string.metronome_details),
                    metronome.getBeats(), metronome.getTempo()));
            isRunning = false;
        } else {
            metronome = new Metronome(
                    savedInstanceState.getInt("beats"),
                    savedInstanceState.getInt("currentBeat"),
                    savedInstanceState.getInt("tempo"));
            metronomeDetails.setText(String.format(getResources().getString(R.string.metronome_details),
                    savedInstanceState.getInt("beats"),
                    savedInstanceState.getInt("tempo")));
            isRunning = savedInstanceState.getBoolean("isRunning");
            if (isRunning) {
                toggle.setText(getResources().getString(R.string.stop));
            }
        }

        currentBeat.setText(Integer.toString(metronome.getCurrentBeat()));
        delay = metronome.getDelay();
        handler = new Handler();
        //TODO stop handler from adding new runnable every time onStart is called
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("beats", metronome.getBeats());
        outState.putInt("currentBeat", metronome.getCurrentBeat());
        outState.putInt("tempo", metronome.getTempo());
        outState.putBoolean("isRunning", isRunning);
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

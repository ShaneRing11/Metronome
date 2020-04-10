package au.edu.jcu.cp3406.metronome;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Metronome metronome;
    private SoundPool soundPool;
    private int downbeatSound;
    private int beatSound;
    private TextView tempoText;
    private TextView beatsText;
    private TextView currentBeat;
    private Button toggle;
    private boolean isRunning;
    private Handler handler;
    private Runnable runnable;
    private int delay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        downbeatSound = soundPool.load(this, R.raw.downbeat, 1);
        beatSound = soundPool.load(this, R.raw.beat, 1);

        toggle = findViewById(R.id.toggle);
        currentBeat = findViewById(R.id.currentBeat);
        tempoText = findViewById(R.id.tempo_text);
        beatsText = findViewById(R.id.beats_text);

        if (savedInstanceState == null) {
            metronome = new Metronome(4, 1, 120);
            isRunning = false;
        } else {
            metronome = new Metronome(
                    savedInstanceState.getInt("beats"),
                    savedInstanceState.getInt("currentBeat"),
                    savedInstanceState.getInt("tempo"));
            isRunning = savedInstanceState.getBoolean("isRunning");
        }

        delay = metronome.getDelay();
        setDescription();
        if (isRunning) {
            enableMetronome();
        }
    }

    @Override
    protected void onStop() {
        handler.removeCallbacks(runnable);
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("beats", metronome.getBeats());
        outState.putInt("currentBeat", metronome.getCurrentBeat());
        outState.putInt("tempo", metronome.getTempo());
        outState.putBoolean("isRunning", isRunning);
    }

    private void setDescription() {
        tempoText.setText(String.format(
                getResources().getString(R.string.display_tempo), metronome.getTempo()));
        beatsText.setText(String.format(
                getResources().getString(R.string.display_beats), metronome.getBeats()));
    }

    public void toggleClicked(View view) {
        if (isRunning) {
            disableMetronome();
        } else {
            enableMetronome();
        }
    }

    private void enableMetronome() {
        isRunning = true;
        toggle.setText((getText(R.string.stop)));
        runnable = new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    currentBeat.setText(Integer.toString(metronome.getCurrentBeat()));
                    if (metronome.getCurrentBeat() == 1) {
                        soundPool.play(downbeatSound, 1, 1, 1, 0, 1);
                    } else {
                        soundPool.play(beatSound, 1, 1, 1, 0, 1);
                    }
                    metronome.tick();
                }
                handler.postDelayed(this, delay);
            }
        };
        handler.post(runnable);
    }

    private void disableMetronome() {
        isRunning = false;
        handler.removeCallbacks(runnable);
        currentBeat.setText("");
        toggle.setText((getText(R.string.start)));
        metronome.reset();
    }

    public void settingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("beats", metronome.getBeats());
        intent.putExtra("tempo", metronome.getTempo());
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                metronome.setTempo(data.getIntExtra("tempo", 120));
                delay = metronome.getDelay();
                metronome.setBeats(data.getIntExtra("beats", 4));
                setDescription();
                if (isRunning) {
                    enableMetronome();
                }
            }
        }
    }
}
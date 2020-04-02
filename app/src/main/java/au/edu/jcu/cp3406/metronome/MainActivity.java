package au.edu.jcu.cp3406.metronome;

import androidx.appcompat.app.AppCompatActivity;

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
                    metronome.tick();
                    currentBeat.setText(Integer.toString(metronome.getCurrentBeat()));
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
        isRunning = true;
        toggle.setText((getText(R.string.stop)));
    }

    private void disableMetronome() {
        isRunning = false;
        toggle.setText((getText(R.string.start)));
        metronome.reset();
        currentBeat.setText(Integer.toString(metronome.getCurrentBeat()));
    }
}

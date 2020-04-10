package au.edu.jcu.cp3406.metronome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    protected static int SETTINGS_REQUEST = 751;

    private int tempo;
    private int beats;
    private TextView tempoText;
    private SeekBar tempoBar;
    private TextView beatsText;
    private SeekBar beatsBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        tempo = intent.getIntExtra("tempo", 120);
        beats = intent.getIntExtra("beats", 4);
        tempoText = findViewById(R.id.tempo_text);
        tempoText.setText(String.format(getResources().getString(R.string.display_tempo), tempo));
        tempoBar = findViewById(R.id.tempo_bar);
        tempoBar.setProgress(tempo);
        tempoBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i < 30) {
                    tempoText.setText(
                            String.format(getResources().getString(R.string.display_tempo), 30));
                } else {
                    tempoText.setText(
                            String.format(getResources().getString(R.string.display_tempo), i));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        beatsText = findViewById(R.id.beats_text);
        beatsText.setText(String.format(
                getResources().getString(R.string.display_beats), beats));
        beatsBar = findViewById(R.id.beats_bar);
        beatsBar.setProgress(beats);
        beatsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i < 2) {
                    beatsText.setText(
                            String.format(getResources().getString(R.string.display_beats), 2));
                } else {
                    beatsText.setText(
                            String.format(getResources().getString(R.string.display_beats), i));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void applyClicked(View view) {
        tempo = tempoBar.getProgress();
        if (tempo < 30) {
            tempo = 30;
        }
        beats = beatsBar.getProgress();
        if (beats < 2) {
            beats = 2;
        }
        Intent intent = new Intent();
        intent.putExtra("tempo", tempo);
        intent.putExtra("beats", beats);
        setResult(RESULT_OK, intent);
        finish();
    }
}
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
    private int beatsPerMeasure;
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
        beatsPerMeasure = intent.getIntExtra("beatsPerMeasure", 4);

        tempoText = findViewById(R.id.tempo_text);
        tempoText.setText(String.format(getResources().getString(R.string.display_tempo), tempo));
        tempoBar = findViewById(R.id.tempo_bar);
        tempoBar.setProgress(tempo);
        tempoBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // Raise value if below minimum
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
                getResources().getString(R.string.display_beats), beatsPerMeasure));
        beatsBar = findViewById(R.id.beats_bar);
        beatsBar.setProgress(beatsPerMeasure);
        beatsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // Raise values if below minimum
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

        // Raise values if below minimum
        if (tempo < 30) {
            tempo = 30;
        }
        beatsPerMeasure = beatsBar.getProgress();
        if (beatsPerMeasure < 2) {
            beatsPerMeasure = 2;
        }
        Intent intent = new Intent();
        intent.putExtra("tempo", tempo);
        intent.putExtra("beatsPerMeasure", beatsPerMeasure);
        setResult(RESULT_OK, intent);
        finish();
    }
}
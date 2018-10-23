package com.example.betatech.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.betatech.alex.WaveView;

public class MainActivity extends AppCompatActivity {

    private WaveView waveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        waveView = findViewById(R.id.waveView);
    }

    public void setPaddingWaveView(View view) {
        waveView.setPaddingAround(30);
    }

    public void swapColorWaveView(View view) {
        waveView.setSquareColor(R.color.colorAccent);
    }

    public void resetWaveView(View view) {
        waveView.resetView();
    }
}

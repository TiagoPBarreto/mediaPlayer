package com.cursoandroid.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekVolume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bach);
        inicializarSeekBar();

    }
    private void inicializarSeekBar(){

        seekVolume = findViewById(R.id.seekVolume);

        //Configura o audio manager
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Recupera os valores de volume maximo e o volume atual
        int volumeMaximo = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volumeAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //Configurar os valores maximos para o seekbar
        seekVolume.setMax(volumeMaximo);
        //Configurar o progresso atual do seekbar
        seekVolume.setProgress(volumeAtual);

        //Configuar volume quando o usuario arrasta
        seekVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(audioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void exexultarSom(View view){
            if (mediaPlayer != null){
                mediaPlayer.start();
            }
    }

    public void pausarMusica(View view){

        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }

    }

    public void pararMusica(View view){

        if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();
        }

    }
    //Configura????o para quando a activity for destruida.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;

        }
    }
    //Configura????o para parar a musica quando fechar o app.

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }

    }
}
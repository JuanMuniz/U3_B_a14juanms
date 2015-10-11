package com.example.juanmuniz.tarefa2_a14juanms;

import android.app.Activity;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Tarefa2_a14juanms extends Activity {

    private EditText textoEntrada;
    private CheckBox casilla;
    private TextView verTexto;
    private RadioButton botonrojo;
    private RadioButton botonazul;
    private Spinner desplegable;
    private ImageView imagen;
    private Switch botonSwitch;
    private Chronometer crono;
    private int destruccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa2_a14juanms);

        textoEntrada=(EditText) findViewById(R.id.edittext_principal);
        casilla=(CheckBox) findViewById(R.id.box_casilla);
        verTexto=(TextView) findViewById(R.id.visor_texto);
        botonrojo=(RadioButton) findViewById(R.id.rojo);
        botonazul=(RadioButton) findViewById(R.id.azul);
        desplegable=(Spinner) findViewById(R.id.desplegable);
        imagen=(ImageView) findViewById(R.id.foto);
        botonSwitch=(Switch) findViewById(R.id.suich);
        crono=(Chronometer) findViewById(R.id.crono);




        desplegable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (parent.getItemAtPosition(pos).toString().equalsIgnoreCase(getResources().getString(R.string.Leon))) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.text_toast_no_gal), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.text_toast_gal), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        crono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long tempoPasado = SystemClock.elapsedRealtime() - chronometer.getBase();
                int tempoSeg = (int) tempoPasado / 1000;
                if (tempoSeg == destruccion) {
                    finish();
                }
            }
        });


        botonSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                destruccion = 15;
                if (isChecked) {
                    // The toggle is enabled
                    crono.setBase(SystemClock.elapsedRealtime());
                    crono.start();
                } else {
                    // The toggle is disabled
                    crono.stop();
                    crono.setBase(SystemClock.elapsedRealtime());
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tarefa2_a14juanms, menu);
        return true;
    }
    public void engadirTexto(View v){
        if(casilla.isChecked()){
            verTexto.setText("");
        }else {
            verTexto.setText(verTexto.getText()+" "+textoEntrada.getText());
            textoEntrada.setText("");
        }
    }

    public void cambiarColor(View v){
        switch(v.getId()){
            case R.id.rojo:
                verTexto.setTextColor(getResources().getColor(R.color.red));
                break;
            case R.id.azul:
                verTexto.setTextColor(getResources().getColor(R.color.blue));
                break;
        }
    }
    public void msgFoto(View v){
        Toast.makeText(getApplicationContext(),imagen.getTag().toString(), Toast.LENGTH_SHORT).show();
    }

    public void startCrono(View v){
        destruccion=15;

        if(botonSwitch.isChecked()){
            crono.setBase(SystemClock.elapsedRealtime());
            crono.start();
        }else{
            crono.stop();
            crono.setBase(SystemClock.elapsedRealtime());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

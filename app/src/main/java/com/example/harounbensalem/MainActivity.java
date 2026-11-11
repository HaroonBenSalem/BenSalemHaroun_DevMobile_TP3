package com.example.harounbensalem;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView msg;
    private Button btnConsulter;
    private SeekBar sbAge;
    private RadioButton rboui, rbnon;
    private EditText etValeur;
    private TextView textAge;
    private int age=0;
    private void init(){
        btnConsulter = findViewById(R.id.btnconsulter);
        sbAge = findViewById(R.id.sbAge);
        rboui = findViewById(R.id.rboui);
        rbnon = findViewById(R.id.rbnon);
        etValeur = findViewById(R.id.etValeur);
        msg = findViewById(R.id.msg);
        textAge = findViewById(R.id.textAge);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                age = progress;
                Log.i("Information", "onProgressChanged"+ progress);
                textAge.setText("Votre Age :"+progress+" ans");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consulterGlycemie();
            }
        });



    }
    private void consulterGlycemie(){
        String valeurString = etValeur.getText().toString().trim().replace(",",".");
        if(valeurString.isEmpty()){
            Toast.makeText(this, "Veuillez entrer la valeur mesurée", Toast.LENGTH_SHORT).show();
            return;
        }
        double valeur = Double.parseDouble(valeurString);
        boolean aJeune = rboui.isChecked();
        String resultat;
        double min,max;
        if(aJeune){
            if(age >= 13){
                min = 5.0;
                max = 7.2;
            }else if(age >= 6){
                min = 5.0;
                max = 10.0;
            }else {
                min = 5.5;
                max = 10.0;
            }

        }
        else {
            min = 0.0;
            max = 10.5;
        }
        if(valeur < min){
            resultat = "Le niveau de glycemie est tros bas";
        }else if (valeur > max){
            resultat = "le niveau de glycemie est trop élevé";
        }else {
            resultat = "le niveau de glycemie est normal";
        }
        msg.setText(resultat);
    }
}
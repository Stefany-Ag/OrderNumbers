package com.example.ordernumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordernumbers.R;

public class MainActivity2 extends AppCompatActivity {
    TextView tvOrdenaNumero;
    TextView tvOrdenaNumero1;
    TextView tvOrdenaNumero2;
    TextView tvOrdenaNumero3;
    int aux= 0;
    int[] numeros =  new int[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.sacar,R.anim.mantener);
        setContentView(R.layout.activity_main2);
        tvOrdenaNumero = (TextView)findViewById(R.id.tvNumOrdenado);
        tvOrdenaNumero1 = (TextView)findViewById(R.id.tvNumOrdenado1);
        tvOrdenaNumero2 = (TextView)findViewById(R.id.tvNumOrdenado2);
        tvOrdenaNumero3 = (TextView)findViewById(R.id.tvNumOrdenado3);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        numeros = bundle.getIntArray("arreglo");
        //Se implementa el metodo bubbleSort.
        for (int i = 0; i < numeros.length - 1; i++) {
            for (int j = i; j < numeros.length; j++) {
                if (numeros[i] > numeros[j]) {
                    aux = numeros[i];
                    numeros[i] = numeros[j];
                    numeros[j] = aux;
                }
            }
        }
        tvOrdenaNumero.setText(String.valueOf(numeros[0]));
        tvOrdenaNumero1.setText(String.valueOf(numeros[1]));
        tvOrdenaNumero2.setText(String.valueOf(numeros[2]));
        tvOrdenaNumero3.setText(String.valueOf(numeros[3]));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.mantener, R.anim.sacar);
    }
    public void Regresar(View view)
    {
        Intent vuelve = new Intent(MainActivity2.this, com.example.ordenaapp.MainActivity.class);
        startActivity(vuelve);
    }
}
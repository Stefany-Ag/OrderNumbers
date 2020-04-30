package com.example.ordernumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ordernumbers.R;

/*
    Facultad de Ingeniería
    Computo móvil. Semestre 2020-1.
    Programado por: Cynthia Estefanía Arreola González.
*/
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String Arreglo = "arreglo";
    MediaPlayer mp;
    EditText etNumero;
    int [] numero = new int[4];
    int i = 0;
    Button btnOrdena;
    ImageButton btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(MainActivity.this, R.raw.howlsong);
        mp.start();
        etNumero = (EditText)findViewById(R.id.etNumero);
        btnOrdena = (Button)findViewById(R.id.btnOrdena);
        btnAgregar = (ImageButton)findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(this);
        btnOrdena.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        escondeTeclado(this);
        switch (v.getId()){
            case R.id.btnAgregar:
                if(i==4)
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.datosLlenos), Toast.LENGTH_LONG).show();
                else
                {
                    try {
                        int num = Integer.parseInt(etNumero.getText().toString());
                        numero[i] = num;
                        i++;
                        Toast.makeText(MainActivity.this,getResources().getString(R.string.valorAgregado),Toast.LENGTH_SHORT).show();
                        etNumero.setText("");
                    }catch (Exception e)
                    {
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.faltaValor), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btnOrdena:
                if(validaFormulario())
                {
                    Bundle bundle = new Bundle();
                    bundle.putIntArray(Arreglo,numero);
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;

        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        mp.start();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    public boolean validaFormulario()
    {

        if(!etNumero.getText().toString().isEmpty() && i>3){
            if(i == 4){
                try {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.datosGuardados), Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.faltaValor), Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        else{
            if(i != 4) {
                etNumero.setError(getResources().getString(R.string.faltanDatos));
                Toast.makeText(MainActivity.this, getResources().getString(R.string.faltanDatos), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
    public static void escondeTeclado(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
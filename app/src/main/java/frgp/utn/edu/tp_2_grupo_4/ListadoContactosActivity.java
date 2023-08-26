package frgp.utn.edu.tp_2_grupo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ListadoContactosActivity extends AppCompatActivity {

    private TextView textViewP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_contactos);

        textViewP = (TextView) findViewById(R.id.textViewPrueba);
        SharedPreferences preferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String datos = preferencias.getString("Leo","");

        if(datos.length()==0){}
        else{
            textViewP.setText(datos);
        }
    }
    public void regresar(android.view.View view) {
        android.content.Intent intent = new android.content.Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
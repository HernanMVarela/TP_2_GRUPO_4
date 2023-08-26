package frgp.utn.edu.tp_2_grupo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AgregarContactoView2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto_view2);
    }

    public void Guardar(android.view.View view){
        String InfoNom = getIntent().getStringExtra("InfoNom");

        Toast.makeText(this, InfoNom.toString(), Toast.LENGTH_SHORT).show();

        Set<String> Info = new HashSet(getIntent().getStringArrayListExtra("ArrayInfo"));
        //Collections.sort(Info);
            SharedPreferences preferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE);
            SharedPreferences.Editor obj_editor = preferencias.edit();
            obj_editor.putStringSet(InfoNom,Info);
            Set<String> datos = new HashSet<>();
        if(preferencias.getStringSet("Contactos",null)!=null){
                    datos = preferencias.getStringSet("Contactos",null);
                }
            datos.add(InfoNom);
            obj_editor.putStringSet("Contactos",datos);
            obj_editor.commit();

            Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();

        android.content.Intent intent = new android.content.Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
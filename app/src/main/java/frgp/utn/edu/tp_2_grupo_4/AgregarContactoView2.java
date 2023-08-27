package frgp.utn.edu.tp_2_grupo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import frgp.utn.edu.tp_2_grupo_4.entidades.Contacto;

public class AgregarContactoView2 extends AppCompatActivity {

    private RadioGroup estudios;
    private RadioButton primarioInc, primarioCom, secundarioInc, secundarioCom, otros;
    private CheckBox musica, tecno, deporte, arte;
    private Switch swtInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto_view2);
        primarioInc = (RadioButton) findViewById(R.id.rbPrimInc);
        primarioCom = (RadioButton) findViewById(R.id.rbPrimCom);
        secundarioInc = (RadioButton) findViewById(R.id.rbSecInc);
        secundarioCom = (RadioButton) findViewById(R.id.rbSecCom);
        otros = (RadioButton) findViewById(R.id.rbOtros);

        musica = (CheckBox) findViewById(R.id.chbMusica);
        tecno = (CheckBox) findViewById(R.id.chbTecno);
        deporte = (CheckBox) findViewById(R.id.chbDeporte);
        arte = (CheckBox) findViewById(R.id.chbArte);

        swtInfo = (Switch)findViewById(R.id.swtInfo);

    }

    public void Guardar(android.view.View view){

        Contacto nuevo = (Contacto) getIntent().getSerializableExtra("contacto");

        /** "Contacto nuevo" DEBERÍA TRAER TODOS LOS DATOS DEL CONTACTO CARGADOS EN EL ACTIVITY ANTERIOR **/
        /** DESARROLLAR EL RESTO DE LA CARGA DEL CONTACTO (ESTUDIOS, INTERESES, INFO) **/

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
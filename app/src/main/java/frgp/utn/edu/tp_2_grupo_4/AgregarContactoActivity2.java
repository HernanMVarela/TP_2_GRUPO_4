package frgp.utn.edu.tp_2_grupo_4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import frgp.utn.edu.tp_2_grupo_4.entidades.Contacto;

public class AgregarContactoActivity2 extends AppCompatActivity {

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

        if(checkFormValidity()){
            return;
        }

        if(primarioInc.isChecked()){
            nuevo.setEstudios("Primario Incompleto");
        }else if(primarioCom.isChecked()){
            nuevo.setEstudios("Primario Completo");
        }else if(secundarioInc.isChecked()){
            nuevo.setEstudios("Secundario Incompleto");
        }else if(secundarioCom.isChecked()){
            nuevo.setEstudios("Secundario Completo");
        }else if(otros.isChecked()){
            nuevo.setEstudios("Otros");
        }

        List<String> intereses = new ArrayList<>();

        if(musica.isChecked()){
            intereses.add("Musica");
        }
        if(tecno.isChecked()){
            intereses.add("Tecnologia");
        }
        if(deporte.isChecked()){
            intereses.add("Deporte");
        }
        if(arte.isChecked()){
            intereses.add("Arte");
        }

        nuevo.setIntereses(intereses);

        if(swtInfo.isChecked()){
            nuevo.setInfo(true);
        }else{
            nuevo.setInfo(false);
        }

        Toast.makeText(this, nuevo.toString(), Toast.LENGTH_LONG).show();

        /*
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
        */
        android.content.Intent intent = new android.content.Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public boolean checkFormValidity(){
        if(primarioInc.isChecked() || primarioCom.isChecked() || secundarioInc.isChecked() || secundarioCom.isChecked() || otros.isChecked()){
            Toast.makeText(this, "No se puede dejar campos vacios", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }
}
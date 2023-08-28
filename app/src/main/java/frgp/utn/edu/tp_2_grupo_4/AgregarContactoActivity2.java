package frgp.utn.edu.tp_2_grupo_4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private String archivo = "contactos.obj";
    private List<Contacto> listado;
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

        if(escribirRegistro(nuevo)){
            android.content.Intent intent = new android.content.Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private boolean escribirRegistro(Contacto nuevo){

        listado = cargarLista();
        if(listado==null){
            listado = new ArrayList<>();
        }
        listado.add(nuevo);

        try{
            ObjectOutputStream objOutput = new ObjectOutputStream(openFileOutput(archivo, MODE_PRIVATE));
            objOutput.writeObject(listado);
            objOutput.close();
            Toast.makeText(this, "Contacto guardado" + '\n' + nuevo.toString(), Toast.LENGTH_LONG).show();
            return true;
        }catch (IOException e){
            Toast.makeText(this, "Error al guardar el archivo", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public List<Contacto> cargarLista() {
        try{
            ObjectInputStream objInput = new ObjectInputStream(openFileInput(archivo));
            List<Contacto> contactos = (List<Contacto>) objInput.readObject();
            objInput.close();
            return contactos;
        }catch (IOException e){
            return null;
        }catch (ClassNotFoundException e){
            Log.e("Listado", "Error clase no encontrada");
            return null;
        }
    }

    public boolean checkFormValidity(){
        if(!primarioInc.isChecked() && !primarioCom.isChecked() && !secundarioInc.isChecked() && !secundarioCom.isChecked() && !otros.isChecked()){
            Toast.makeText(this, "No se puede dejar campos vacios", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu_nav){
        MenuInflater infilter = getMenuInflater();
        infilter.inflate(R.menu.menu_es_activity, menu_nav);
        return super.onCreateOptionsMenu(menu_nav);
    }

    @Override public boolean onOptionsItemSelected(MenuItem opcion_menu){
        int id = opcion_menu.getItemId();

        if(id==R.id.agregarContacto){
            navegar_agregar_contacto(null);
            return true;
        }
        if(id==R.id.listarContacto){
            navegar_listado_contactos(null);
            return true;
        }
        if(id==R.id.listarHome){
            regresar(null);
            return true;
        }
        return super.onOptionsItemSelected(opcion_menu);
    }

    public void navegar_agregar_contacto(android.view.View view) {
        android.content.Intent intent = new android.content.Intent(this, AgregarContactoActivity.class);
        startActivity(intent);
    }

    public void navegar_listado_contactos(android.view.View view) {
        android.content.Intent intent = new android.content.Intent(this, ListadoContactosActivity.class);
        startActivity(intent);
    }

    public void regresar(android.view.View view) {
        android.content.Intent intent = new android.content.Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
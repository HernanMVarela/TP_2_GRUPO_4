package frgp.utn.edu.tp_2_grupo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.sql.Date;

import frgp.utn.edu.tp_2_grupo_4.entidades.Contacto;
import frgp.utn.edu.tp_2_grupo_4.entidades.Email;
import frgp.utn.edu.tp_2_grupo_4.entidades.Telefono;

public class MainActivity extends AppCompatActivity {

    private EditText prueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prueba = (EditText) findViewById(R.id.etmPrueba);

        guardarContacto();
        prueba.setText(LeerContacto().toString());
    }

    private void guardarContacto(){
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        Contacto myObject = new Contacto();
        myObject.setNombre("Hernan");
        myObject.setApellido("Varela");
        myObject.setDireccion("Calle falsa 123");
        myObject.setNacimiento("1990-04-02");
        myObject.setTelefono(new Telefono());
        myObject.setEmail(new Email());
        myObject.getTelefono().setNumero("1122334455");
        myObject.getTelefono().setTipo("Casa");
        myObject.getEmail().setCorreo("hernan.varela@mail.com");
        myObject.getEmail().setTipo("Casa");

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myObject);
        prefsEditor.putString("MyObject", json);
        prefsEditor.commit();
    }

    private Contacto LeerContacto(){
        SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        Contacto obj = gson.fromJson(json, Contacto.class);

        Log.i("contacto", obj.toString());
        return obj;
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

}
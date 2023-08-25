package frgp.utn.edu.tp_2_grupo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AgregarContactoActivity extends AppCompatActivity {

    private Spinner spinnerTelefono;
    private Spinner spinnerMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);
        spinnerTelefono = (Spinner)findViewById(R.id.spinFormTelefono);
        spinnerMail = (Spinner)findViewById(R.id.spinFormEmail);

        String[] opciones = {"Casa", "Trabajo", "Móvil"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,opciones);
        spinnerTelefono.setAdapter(adapter);
        spinnerMail.setAdapter(adapter);
    }

    public void navegar_contactos_dos(android.view.View view) {
        android.content.Intent intent = new android.content.Intent(this, AgregarContactoView2.class);
        startActivity(intent);
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
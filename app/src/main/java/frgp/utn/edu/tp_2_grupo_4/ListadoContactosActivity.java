package frgp.utn.edu.tp_2_grupo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import frgp.utn.edu.tp_2_grupo_4.entidades.Contacto;

public class ListadoContactosActivity extends AppCompatActivity {

    private String archivo = "contactos.obj";
    private ListView lvcontactos;
    private List<Contacto> listado;
    private List<String> nombres = new ArrayList<>();
    private List<String> datosCompletos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_contactos);

        listado = cargarLista();
        if(listado == null){
            nombres.add("No tienes contactos guardados.");
        }else{
            for (Contacto item: listado) {
                nombres.add(item.getNombre() + " " + item.getApellido() + " - " + item.getEmail().getCorreo());
                datosCompletos.add(item.toString());
            }
        }


        lvcontactos = (ListView)findViewById(R.id.lvContactos);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombres);
        lvcontactos.setAdapter(adapter);

        if(listado != null){
            lvcontactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    AlertDialog.Builder contact = new AlertDialog.Builder(ListadoContactosActivity.this);
                    contact.setTitle("Contacto");
                    contact.setMessage(datosCompletos.get(position));
                    contact.setCancelable(false)
                            .setPositiveButton("OK", null);
                    contact.show();

                }
            });
        }

    }

    public List<Contacto> cargarLista() {
        try {
            ObjectInputStream objInput = new ObjectInputStream(openFileInput(archivo));
            List<Contacto> contactos = (List<Contacto>) objInput.readObject();
            objInput.close();
            return contactos;
        }catch (IOException e){
            Toast toast2 = Toast.makeText(getApplicationContext(), "Error al cargar el archivo", Toast.LENGTH_LONG);
            toast2.show();
            return null;
        }catch (ClassNotFoundException e){
            Log.e("Listado", "Error clase no encontrada");
            return null;
        }
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
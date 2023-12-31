package frgp.utn.edu.tp_2_grupo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import frgp.utn.edu.tp_2_grupo_4.entidades.Contacto;
import frgp.utn.edu.tp_2_grupo_4.entidades.Email;
import frgp.utn.edu.tp_2_grupo_4.entidades.Telefono;

public class AgregarContactoActivity extends AppCompatActivity  implements View.OnFocusChangeListener, DatePickerDialog.OnDateSetListener, View.OnClickListener {

    private Spinner spinnerTelefono;
    private Spinner spinnerMail;
    private EditText contactTel;
    private EditText contactNom;
    private EditText contactApe;
    private EditText contactEma;
    private EditText contactDir;
    private EditText contactDat;
    private Calendar mCalendar;
    private SimpleDateFormat mFormat;
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

        contactTel = (EditText)findViewById(R.id.etvFormTelefono);
        contactNom = (EditText)findViewById(R.id.etvFormNombre);
        contactApe = (EditText)findViewById(R.id.etvFormApellido);
        contactEma = (EditText)findViewById(R.id.etvFormEmail);
        contactDir = (EditText)findViewById(R.id.etvFormDireccion);
        contactDat = (EditText)findViewById(R.id.etvFormDate);
        contactDat.setOnFocusChangeListener(this);
        contactDat.setOnClickListener(this);
        mFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            showPicker(view);
        }
    }

    @Override
    public void onClick(View view) {
        showPicker(view);
    }

    private void showPicker(View view) {
        if (mCalendar == null)
            mCalendar = Calendar.getInstance();

        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        int month = mCalendar.get(Calendar.MONTH);
        int year = mCalendar.get(Calendar.YEAR);

        new DatePickerDialog(view.getContext(), this, year, month, day).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        this.contactDat.setText(mFormat.format(mCalendar.getTime()));
    }

    public void navegar_contactos_dos(android.view.View view) {
        android.content.Intent intent = new android.content.Intent(this, AgregarContactoActivity2.class);

        intent.putExtra("InfoNom", contactNom.getText().toString());
        Contacto nuevo = new Contacto();
        nuevo.setEmail(new Email());
        nuevo.setTelefono(new Telefono());

        if(checkFormValidity()){
            return;
        }

        nuevo.setNombre(contactNom.getText().toString());
        nuevo.setApellido(contactApe.getText().toString());
        nuevo.getTelefono().setNumero(contactTel.getText().toString());
        nuevo.getTelefono().setTipo(spinnerTelefono.getSelectedItem().toString());
        nuevo.getEmail().setCorreo(contactEma.getText().toString());
        nuevo.getEmail().setTipo(spinnerMail.getSelectedItem().toString());
        nuevo.setDireccion(contactDir.getText().toString());
        nuevo.setNacimiento(contactDat.getText().toString());

        intent.putExtra("contacto", nuevo);
        startActivity(intent);

        /**
        intent.putExtra("InfoNom", contactNom.getText().toString());
        ArrayList<String> Info = new ArrayList<>();

        Info.add(contactNom.getText().toString());
        Info.add(contactApe.getText().toString());
        Info.add(contactTel.getText().toString());
        Info.add(contactEma.getText().toString());
        Info.add(contactDir.getText().toString());
        Info.add(contactDat.getText().toString());
        intent.putExtra("ArrayInfo", Info);
        startActivity(intent);**/
    }

    public boolean checkFormValidity(){
        if(contactNom.getText().toString().isEmpty()
        || contactApe.getText().toString().isEmpty()
        || contactTel.getText().toString().isEmpty()
        || contactEma.getText().toString().isEmpty()
        || contactDir.getText().toString().isEmpty()
        || contactDat.getText().toString().isEmpty()){
            Toast.makeText(this, "No se puede dejar campos vacios", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(!contactEma.getText().toString().contains("@")){
            Toast.makeText(this, "Ingrese una direccion de mail valida", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(mCalendar.getTime().after(Calendar.getInstance().getTime())){
            Toast.makeText(this, "Ingrese una fecha igual o anterior a la actual", Toast.LENGTH_SHORT).show();
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
package frgp.utn.edu.tp_2_grupo_4.entidades;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Contacto implements Serializable {
    private String nombre;
    private String apellido;
    private Telefono telefono;
    private Email email;
    private String direccion;
    private Date nacimiento;
    private String estudios;
    private List<String> intereses;
    private boolean info;

    public Contacto() {
    }

    public Contacto(String nombre, String apellido, Telefono telefono, Email email, String direccion, Date nacimiento, String estudios, List<String> intereses, boolean info) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.nacimiento = nacimiento;
        this.estudios = estudios;
        this.intereses = intereses;
        this.info = info;
    }

    @Override
    public String toString() {
        return  "Nombre: " + nombre + " " + apellido + '\n'
                + telefono + '\n' + email + '\n' +
                "Direccion: " + direccion + '\n' +
                "Nacimiento: " + nacimiento + '\n' +
                "Estudios: " + estudios + '\n' +
                "Intereses: " + intereses + '\n' +
                "Recibir información: " + (info ? "Si" : "No");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public List<String> getIntereses() {
        return intereses;
    }

    public void setIntereses(List<String> intereses) {
        this.intereses = intereses;
    }
}

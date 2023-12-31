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
    private String nacimiento;
    private String estudios;
    private List<String> intereses;
    private boolean info;

    public Contacto() {
    }

    public Contacto(String nombre, String apellido, Telefono telefono, Email email, String direccion, String nacimiento, String estudios, List<String> intereses, boolean info) {
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

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
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

    public boolean getInfo() {
        return info;
    }

    public void setInfo(boolean info) {
        this.info = info;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: " + nombre + " " + apellido + '\n');
        sb.append(telefono + "\n");
        sb.append(email + "\n");
        sb.append("Direccion: " + direccion + "\n");
        sb.append("Nacimiento: " + nacimiento + "\n");
        sb.append("Estudios: " + estudios + "\n");
        sb.append("Intereses: " + intereses + "\n");
        sb.append("Recibir información: " + (info ? "Si" : "No"));
        return sb.toString();
    }

}

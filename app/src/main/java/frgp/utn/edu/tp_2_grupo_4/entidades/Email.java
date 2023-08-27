package frgp.utn.edu.tp_2_grupo_4.entidades;

import java.io.Serializable;

public class Email implements Serializable {
    private String correo;
    private String tipo;

    public Email() {
    }

    public Email(String correo, String tipo) {
        this.correo = correo;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return  "Correo: " + correo + " | Tipo: " + tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

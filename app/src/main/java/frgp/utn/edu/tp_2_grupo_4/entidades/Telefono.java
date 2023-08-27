package frgp.utn.edu.tp_2_grupo_4.entidades;

import java.io.Serializable;

public class Telefono implements Serializable {
    private String numero;
    private String tipo;

    public Telefono() {
    }

    public Telefono(String numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return  "Telefono: " + numero + " | Tipo: " + tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

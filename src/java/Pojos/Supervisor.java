package Pojos;

import java.io.Serializable;

/**
 *
 * @author Armando-Zar
 */
public class Supervisor implements Serializable{
    private int id_supervisor;
    private String nombre;
    private String dpi;
    private String email;
    private String telefono;

    public Supervisor() {
    }

    //Métodos gets y sets     

    public int getId_supervisor() {
        return id_supervisor;
    }

    public void setId_supervisor(int id_supervisor) {
        this.id_supervisor = id_supervisor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    } 
}

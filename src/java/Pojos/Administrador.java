package Pojos;

import java.io.Serializable;

/**
 * 
 * @author Armando-Zar
 */
public class Administrador implements Serializable {
    private int id_admin;
    private String nombre;
    private String dpi;
    private String email;
    private String telefono;
    
    
    public Administrador() {
    }

    //Métodos gets y sets

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
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

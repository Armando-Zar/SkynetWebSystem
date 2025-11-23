package Pojos;

import java.io.Serializable;

/**
 *
 * @author Armando-Zar
 */
public class Rol implements Serializable{
    private int id_rol;
    private String nombre;

    public Rol() {
    }
    
    //Métodos gets y sets    

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

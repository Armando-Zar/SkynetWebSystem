package Controlador;

import Modelo.RegistroClienteModelo;
import Pojos.Cliente;
import Utilidades.Catalogos;
import Utilidades.Mensajes;
import java.io.Serializable;
import javax.faces.model.SelectItem;

/**
 *
 * @author Armando-Zar
 */
public class RegistroClienteControlador implements Serializable {

    private String nombre;
    private String direccion;
    private String telefono;
    private String email;    
    private String lat = "1";
    private String lng = "1";

    /**
     * Metodo que registra un nuevo cliente
     */
    public void registrarCliente() {
        try {
            String validar = validacion();
            if (validar.equals("")) {
                Cliente client = new Cliente();
                client.setNombre(getNombre());
                client.setDireccion(getDireccion());
                client.setTelefono(getTelefono());
                client.setEmail(getEmail());
                client.setLat(getLat());
                client.setLng(getLng());

                RegistroClienteModelo modelo = new RegistroClienteModelo();
                String resultadoGuardar = modelo.guardarCliente(client);

                if (resultadoGuardar.equals("exito")) {
                    Mensajes.mensaje("Éxito", "Cliente registrado correctamente", 1);
                    setNombre("");
                    setDireccion("");
                    setTelefono("");
                    setEmail("");
                    setLat("");
                    setLng("");
                } else {
                    Mensajes.mensaje("Error", "Datos del cliente no registrados", 2);
                }
            } else {
                Mensajes.mensaje("Advertencia", validar, 3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que valida los datos del módulo registrar cliente
     *
     * @return
     */
    public String validacion() {
        String resultado = "";
        try {
            if (getNombre().equals("")) {
                resultado = "Debe ingresar Nombre del Cliente.";
            } else if (getDireccion().equals("")) {
                resultado = "Debe ingresar la Dirección del Cliente.";
            } else if (getTelefono().equals("")) {
                resultado = "Debe ingresar Teléfono del Cliente.";
            } else if (getEmail().equals("")) {
                resultado = "Debe ingresar Email del Cliente.";
            }

        } catch (Exception ex) {
            resultado = "Error en la validación de los registros";
            ex.printStackTrace();
        }

        return resultado;
    }

    /**
     * metodo que comprubea la LATITUD mínima y máxima
     */
    public void comprobarLatitud() {
        try {
            if (getLat().length() >= 3 && getLat().length() <= 11) {
                Mensajes.mensaje("Mensaje", "Latitud Valida", 1);
            } else {
                Mensajes.mensaje("Mensaje", "Verificar la Latitud ingresada", 3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * metodo que comprubea la LONGITUD mínima y máxima
     */    
    public void comprobarLongitud() {
        try {
            if (getLng().length() >= 3 && getLng().length() <= 11) {
                Mensajes.mensaje("Mensaje", "Longitud Valida", 1);
            } else {
                Mensajes.mensaje("Mensaje", "Verificar la Longitud ingresada", 3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    } 
    
}

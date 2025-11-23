package Controlador;

import Modelo.RegistroAdministradorModelo;
import Pojos.Administrador;
import Utilidades.Mensajes;
import java.io.Serializable;

public class RegistroAdministradorControlador implements Serializable {

    private String nombre;
    private String dpi;
    private String telefono;
    private String email;

    /**
     * Metodo que registra un nuevo administrador
     */
    public void registrarAdministrador() {
        try {
            String validar = validacion();
            if (validar.equals("")) {
                Administrador admin = new Administrador();
                admin.setNombre(getNombre());
                admin.setDpi(getDpi());
                admin.setTelefono(getTelefono());
                admin.setEmail(getEmail());

                RegistroAdministradorModelo modelo = new RegistroAdministradorModelo();
                String resultadoGuardar = modelo.guardarAdministrador(admin);

                if (resultadoGuardar.equals("exito")) {
                    Mensajes.mensaje("Éxito", "Administrador registrado correctamente", 1);
                    setNombre("");
                    setDpi("");
                    setTelefono("");
                    setEmail("");
                } else {
                    Mensajes.mensaje("Error", "Datos del administrador no registrados", 2);
                }
            } else {
                Mensajes.mensaje("Advertencia", validar, 3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que valida los datos del administrador
     */
    public String validacion() {
        String resultado = "";
        try {
            if (getNombre().equals("")) {
                resultado = "Debe ingresar Nombre del Administrador.";
            } else if (getDpi().equals("")) {
                resultado = "Debe ingresar DPI del Administrador.";
            } else if (getTelefono().equals("")) {
                resultado = "Debe ingresar Teléfono del Administrador.";
            } else if (getEmail().equals("")) {
                resultado = "Debe ingresar Email del Administrador.";
            }

        } catch (Exception ex) {
            resultado = "Error en la validación de los registros";
            ex.printStackTrace();
        }

        return resultado;
    }

    // ==== GETS y SETS ====

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
}
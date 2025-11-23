package Controlador;

import Modelo.RegistroSupervisorModelo;
import Pojos.Supervisor;
import Utilidades.Mensajes;
import java.io.Serializable;

/**
 *
 * @author Armando
 */
public class RegistroSupervisorControlador implements Serializable {

    private String nombre;
    private String dpi;
    private String telefono;
    private String email;

    public RegistroSupervisorControlador() {
    }

    /**
     * Metodo que registra un nuevo supervisor
     */
    public void registrarSupervisor() {
        try {
            String validar = validacion();
            if (validar.equals("")) {

                Supervisor supervisor = new Supervisor();
                supervisor.setNombre(getNombre());
                supervisor.setDpi(getDpi());
                supervisor.setTelefono(getTelefono());
                supervisor.setEmail(getEmail());

                RegistroSupervisorModelo modelo = new RegistroSupervisorModelo();
                String resultadoGuardar = modelo.guardarSupervisor(supervisor);

                if (resultadoGuardar.equals("exito")) {
                    Mensajes.mensaje("Éxito", "Supervisor registrado correctamente", 1);

                    setNombre("");
                    setDpi("");
                    setTelefono("");
                    setEmail("");

                } else {
                    Mensajes.mensaje("Error", "Datos del supervisor no registrados", 2);
                }
            } else {
                Mensajes.mensaje("Advertencia", validar, 3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que valida los datos del módulo registrar supervisor
     */
    public String validacion() {
        String resultado = "";
        try {
            if (getNombre().equals("")) {
                resultado = "Debe ingresar Nombre del Supervisor.";
            } else if (getDpi().equals("")) {
                resultado = "Debe ingresar el DPI.";
            } else if (getTelefono().equals("")) {
                resultado = "Debe ingresar Teléfono.";
            } else if (getEmail().equals("")) {
                resultado = "Debe ingresar Email.";
            }

        } catch (Exception ex) {
            resultado = "Error en la validación de los registros";
            ex.printStackTrace();
        }

        return resultado;
    }

    // ---- GETTERS Y SETTERS ----

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

package Controlador;

import Modelo.RegistroTecnicoModelo;
import Pojos.Tecnico;
import Utilidades.Catalogos;
import Utilidades.Mensajes;
import java.io.Serializable;
import javax.faces.model.SelectItem;

/**
 *
 * @author 
 */
public class RegistroTecnicoControlador implements Serializable {

    private String nombre;
    private String dpi;
    private String telefono;
    private String email;
    private int id_supervisor;

    private SelectItem[] listaSupervisor;

    public RegistroTecnicoControlador() {
        cargarSupervisores();
    }

    /**
     * Carga listado de supervisores
     */
    public void cargarSupervisores() {
        Catalogos catalogo = new Catalogos();
        listaSupervisor = catalogo.cargarSupervisor();
    }

    /**
     * Metodo que registra un nuevo técnico
     */
    public void registrarTecnico() {
        try {
            String validar = validacion();
            if (validar.equals("")) {

                Tecnico tecnico = new Tecnico();
                tecnico.setNombre(getNombre());
                tecnico.setDpi(getDpi());
                tecnico.setTelefono(getTelefono());
                tecnico.setEmail(getEmail());
                tecnico.setId_supervisor(getId_supervisor());

                RegistroTecnicoModelo modelo = new RegistroTecnicoModelo();
                String resultadoGuardar = modelo.guardarTecnico(tecnico);

                if (resultadoGuardar.equals("exito")) {
                    Mensajes.mensaje("Éxito", "Técnico registrado correctamente", 1);

                    setNombre("");
                    setDpi("");
                    setTelefono("");
                    setEmail("");
                    setId_supervisor(-1);

                } else {
                    Mensajes.mensaje("Error", "Datos del técnico no registrados", 2);
                }
            } else {
                Mensajes.mensaje("Advertencia", validar, 3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo de validación
     */
    public String validacion() {
        String resultado = "";
        try {
            if (getNombre().equals("")) {
                resultado = "Debe ingresar Nombre del Técnico.";
            } else if (getDpi().equals("")) {
                resultado = "Debe ingresar el DPI.";
            } else if (getTelefono().equals("")) {
                resultado = "Debe ingresar Teléfono.";
            } else if (getEmail().equals("")) {
                resultado = "Debe ingresar Email.";
            } else if (getId_supervisor() == -1) {
                resultado = "Debe seleccionar un Supervisor.";
            }

        } catch (Exception ex) {
            resultado = "Error en la validación de los registros";
            ex.printStackTrace();
        }
        return resultado;
    }

    // GETTERS & SETTERS

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDpi() { return dpi; }
    public void setDpi(String dpi) { this.dpi = dpi; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getId_supervisor() { return id_supervisor; }
    public void setId_supervisor(int id_supervisor) { this.id_supervisor = id_supervisor; }

    public SelectItem[] getListaSupervisor() { return listaSupervisor; }
    public void setListaSupervisor(SelectItem[] listaSupervisor) { this.listaSupervisor = listaSupervisor; }
}

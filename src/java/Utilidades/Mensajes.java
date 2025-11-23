package Utilidades;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * 
 * @author Armando-Zar
 */
public class Mensajes implements Serializable {

    public Mensajes() {
    }

    /**
     * Mensajes de usuarios
     * @param titulo
     * @param descripcion
     * @param tipoMensaje 
     */

    public static void mensaje(String titulo, String descripcion, int tipoMensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        switch (tipoMensaje) {
            case 1:
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, descripcion));
                break;
            case 2:
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, descripcion));
                break;
            case 3:
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, descripcion));
                break;

            case 4:
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, descripcion));
                break;
        }
    }
}

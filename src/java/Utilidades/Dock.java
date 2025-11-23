package Utilidades;

import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 * 
 * @author Armando-Zar
 */
public class Dock implements Serializable{

    public Dock() {
    }
    
  // Serán las reglas de navegación por cada Menú de acuerdo al Rol registrado
  // Se verán reflejados en faces-config
    public String navegacionDock(){
        String resultado = "";
        try{
            int rol = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SESSION_ROL").toString());
            switch(rol){
                case 1:
                    resultado = "menuAdministrador";
                    break;
                case 2:
                    resultado = "menuSupervisor";
                    break;
                case 3:
                    resultado = "menuTecnico";
                    break;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return resultado;
    }
}

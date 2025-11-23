package Controlador;

import java.io.Serializable;

/**
 * 
 * @author Armando-Zar
 */
public class MenuSupervisorControlador implements Serializable{

    public MenuSupervisorControlador() {
    }
    
    /**
     * Metodo que gestiona la navegacion del menu Supervisor
     * @param opcion
     * @return 
     * Esta navegacionMenu hace match con faces-config.xml
     * por ende el nombre que se coloca en faces-config en la NAVEGACIÓN PARA ROL SUPERVISOR
     * debe de ser la misma que se coloque en este switch case con sus mayúsculas o minúsculas.
     */
    public String navegacionMenu(int opcion){
        String resultado = "";    
        try{
            switch(opcion){
                case 1:
                    resultado = "salir";
                    break;
                case 2:
                    resultado = "nuevaVisita";
                    break;
                case 3:
                    resultado = "consultaVisita";
                    break;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }        
        return resultado;
    }
}

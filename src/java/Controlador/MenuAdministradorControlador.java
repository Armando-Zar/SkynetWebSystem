package Controlador;

import java.io.Serializable;

/**
 * 
 * @author Armando-Zar
 */
public class MenuAdministradorControlador implements Serializable{

    public MenuAdministradorControlador() {
    }
    
    /**
     * Metodo que gestiona la navegacion del menu Administrador
     * @param opcion
     * @return
     * Esta navegacionMenu hace match con faces-config.xml
     * por ende el nombre que se coloca en faces-config en la NAVEGACIÓN PARA ROL ADMINISTRADOR
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
                    resultado = "nuevoCliente";
                    break;
                case 3:
                    resultado = "consultaCliente";
                    break;
                case 4:
                    resultado = "nuevaVisita";
                    break;
                case 5:
                    resultado = "consultaVisita";
                    break;
                case 6:
                    resultado = "nuevoAdministrador";
                    break;
                case 7:
                    resultado = "consultaAdministrador";
                    break;
                case 8:
                    resultado = "nuevoSupervisor";
                    break;
                case 9:
                    resultado = "consultaSupervisor";
                    break;
                case 10:
                    resultado = "nuevoTecnico";
                    break;
                case 11:
                    resultado = "consultaTecnico";
                    break;
                case 12:
                    resultado = "nuevoRol";
                    break;
                case 13:
                    resultado = "consultaRol";
                    break;
                case 14:
                    resultado = "nuevoUsuario";
                    break;
                case 15:
                    resultado = "consultaUsuario";
                    break;                                      
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }        
        return resultado;
    }
}

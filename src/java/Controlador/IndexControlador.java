package Controlador;

import Modelo.IndexModelo;
import Utilidades.Mensajes;
import java.io.Serializable;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @author Armando-Zar
 */
public class IndexControlador implements Serializable {

    private String usuario;
    private String password;
    private int id_supervisor;

    public IndexControlador() {
    }

    //metodo que valida los datos de inicio de sesión
    
    public String validarDatos() {
        String resultado = "";
        if (getUsuario().equals("") || getPassword().equals("")) {
            Mensajes.mensaje("Error", "Debe llenar los dos campos", 3);
        }
        else{
            String passwordEncriptado = DigestUtils.md5Hex(getPassword().toUpperCase());
            System.out.print(passwordEncriptado);
            IndexModelo modelo = new IndexModelo();
            resultado = modelo.validarUsuario(getUsuario(), getPassword());
            System.out.println("Usuario logeado: " + usuario.toUpperCase());
            Mensajes.mensaje("Usuario Logeado: "+usuario.toUpperCase(),"", 1);            
            
            if(resultado.equals("usuarioInactivo")){
               Mensajes.mensaje("Error", "Usuario inactivo", 2);
            }else if(resultado.equals("usuarioIncorrecto")){
               Mensajes.mensaje("Error", "Credenciales invalidas", 2);
            }
        }
        return resultado;
    }  

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_supervisor() {
        return id_supervisor;
    }

    public void setId_supervisor(int id_supervisor) {
        this.id_supervisor = id_supervisor;
    }

}

package Modelo;

import Utilidades.Conexion;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 * 
 * @author Armando-Zar
 */
public class IndexModelo implements Serializable{

    public IndexModelo() {
    }
    
    /**
     * Método que valida el login del usuario por control de acceso por Rol
     * @param user
     * @param pass
     * @return 
     */
    public String validarUsuario(String user, String pass){
        String resultado = "";
        try{
            Conexion conexion = new Conexion();
            Connection conn = conexion.connect();
            
            String sql = "select login_name, id_rol, id_supervisor, id_tecnico, estado from skynet.usuario where login_name = '"+user+"' and password = '"+pass+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()){
                rs.beforeFirst();
                while(rs.next()){
                    if(rs.getInt("estado") == 0){
                        resultado = "usuarioInactivo";
                    } else {
                    // Guardamos el usuario y rol en sesión    
                         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SESSION_USER", user);
                         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SESSION_ROL", rs.getInt("id_rol"));
                         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SESSION_SUPERVISOR", rs.getInt("id_supervisor"));
                         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SESSION_TECNICO", rs.getInt("id_tecnico"));
                                                
                     if(rs.getInt("id_rol") == 1){
                        resultado = "administrador";
                    } else if(rs.getInt("id_rol") == 2){
                        resultado = "supervisor";
                    } else if(rs.getInt("id_rol") == 3){
                        resultado = "tecnico";
                    }
                  }
                }
            }else{
                resultado = "usuarioIncorrecto";
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return resultado;
    }     
    
}

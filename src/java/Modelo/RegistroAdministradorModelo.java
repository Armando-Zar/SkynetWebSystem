package Modelo;

import Pojos.Administrador;
import Utilidades.Conexion;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;

public class RegistroAdministradorModelo implements Serializable {

    public RegistroAdministradorModelo() {
    }
    
    /**
     * Metodo que guarda un nuevo administrador en la base de datos
     */
    public String guardarAdministrador(Administrador admin) {
        String resultado = "";
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.connect();
            Statement st = conn.createStatement();
            String sql = "insert into skynet.administrador(nombre, dpi, telefono, email) "
                    + "values('" + admin.getNombre() + "', '" + admin.getDpi() + "', '" + admin.getTelefono() + "', "
                    + "'" + admin.getEmail() + "')";
            
            System.out.println(sql);
            int resultadoGuardar = st.executeUpdate(sql);
            if (resultadoGuardar == 1) {
                resultado = "exito";
            } else {
                resultado = "error";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }
}
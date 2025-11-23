package Modelo;

import Pojos.Supervisor;
import Utilidades.Conexion;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;

/**
 * 
 * @author 
 */
public class RegistroSupervisorModelo implements Serializable {

    public RegistroSupervisorModelo() {
    }

    /**
     * Metodo que guarda un nuevo supervisor en la base de datos
     */
    public String guardarSupervisor(Supervisor supervisor) {
        String resultado = "";
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.connect();
            Statement st = conn.createStatement();

            String sql = "insert into skynet.supervisor(nombre,dpi,telefono,email) "
                    + "values('" + supervisor.getNombre() + "', '" + supervisor.getDpi() + "', '" 
                    + supervisor.getTelefono() + "', '" + supervisor.getEmail() + "')";

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

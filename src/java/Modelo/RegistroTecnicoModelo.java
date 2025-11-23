package Modelo;

import Pojos.Tecnico;
import Utilidades.Conexion;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author 
 */
public class RegistroTecnicoModelo implements Serializable {

    public RegistroTecnicoModelo() {
    }

    /**
     * Metodo que guarda un nuevo técnico
     */
    public String guardarTecnico(Tecnico tecnico) {
        String resultado = "";
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.connect();
            Statement st = conn.createStatement();

            String sql = "insert into skynet.tecnico(nombre, dpi, telefono, email, id_supervisor) "
                    + "values('" + tecnico.getNombre() + "', '" + tecnico.getDpi() + "', '" 
                    + tecnico.getTelefono() + "', '" + tecnico.getEmail() + "', "
                    + tecnico.getId_supervisor()+ ")";

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

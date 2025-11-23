package Modelo;

import Utilidades.Conexion;
import Pojos.Usuario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;

public class RegistroUsuarioModelo implements Serializable {

    public RegistroUsuarioModelo() {}

    /**
     * Metodo que guarda un nuevo usuario
     */
    public String guardarUsuario(Usuario usuario) {
        String resultado = "";
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.connect();
            Statement st = conn.createStatement();

            String sql = "insert into skynet.usuario(login_name, password, id_rol, id_supervisor, id_tecnico, estado) "
                    + "values('" + usuario.getLogin_name() + "', '" + usuario.getPassword() + "', "
                    + usuario.getId_rol() + ", " + usuario.getId_supervisor() + ", "
                    + usuario.getId_tecnico() + ", '" + usuario.getEstado() + "')";

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

package Modelo;

import Pojos.Cliente;
import Utilidades.Conexion;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;

/**
 * 
 * @author Armando-Zar
 */
public class RegistroClienteModelo implements Serializable{

    public RegistroClienteModelo() {
    }
    
    /**
     * Metodo que guarda un nuevo cliente en la base de datos
     * @param cliente
     * @return 
     */
    public String guardarCliente(Cliente cliente){
        String resultado = "";
        try{
            Conexion conexion = new Conexion();
            Connection conn = conexion.connect();
            Statement st = conn.createStatement();
            String sql = "insert into skynet.cliente(nombre,direccion,telefono,email,lat,lng)"
                    + "values('"+cliente.getNombre()+"', '"+cliente.getDireccion()+"', '"+cliente.getTelefono()+"', "
                    + "'"+cliente.getEmail()+"', '"+cliente.getLat()+"','"+cliente.getLng()+"')";
            System.out.println(sql);
            int resultadoGuardar = st.executeUpdate(sql);
            if(resultadoGuardar == 1){
                resultado = "exito";
            }else{
                resultado = "error";
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return resultado;
    }
    
}

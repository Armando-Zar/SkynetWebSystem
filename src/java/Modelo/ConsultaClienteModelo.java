package Modelo;

import Pojos.Cliente;
import Utilidades.Conexion;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author Armando-Zar
 */
public class ConsultaClienteModelo implements Serializable {

    public ConsultaClienteModelo() {
    }

    /**
     * Metodo que consulta el listado general de los clientes en la DB
     *
     * @param listado
     * @return
     */
    
    // Consulta cliente por Id_Cliente
    public ArrayList<Cliente> consultaClienteId_Cliente(String id_cliente) {
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.connect();
            String sql = "select id_cliente,nombre,direccion,telefono,email,lat,lng,fecha_registro from skynet.cliente where id_cliente = '" + id_cliente + "' ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    Cliente cl = new Cliente();
                    cl.setId_cliente(rs.getInt("id_cliente"));
                    cl.setNombre(rs.getString("nombre"));
                    cl.setDireccion(rs.getString("direccion"));
                    cl.setTelefono(rs.getString("telefono"));
                    cl.setEmail(rs.getString("email"));
                    cl.setLat(rs.getString("lat"));
                    cl.setLng(rs.getString("lng"));
                    cl.setFecha_registro(rs.getString("fecha_registro"));
                    lista.add(cl);
                }
            }
        } catch (Exception ex) {
            lista = null;
            ex.printStackTrace();
        }
        return lista;
    }
    
    // Consulta de clientes en general
    public ArrayList<Cliente> consultaClienteGeneral() {
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.connect();
            String sql = "select id_cliente,nombre,direccion,telefono,email,lat,lng,fecha_registro from skynet.cliente where id_cliente >= '" + 1 + "' ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    Cliente cl = new Cliente();
                    cl.setId_cliente(rs.getInt("id_cliente"));
                    cl.setNombre(rs.getString("nombre"));
                    cl.setDireccion(rs.getString("direccion"));
                    cl.setTelefono(rs.getString("telefono"));
                    cl.setEmail(rs.getString("email"));
                    cl.setLat(rs.getString("lat"));
                    cl.setLng(rs.getString("lng"));
                    cl.setFecha_registro(rs.getString("fecha_registro"));
                    lista.add(cl);
                }
            }
        } catch (Exception ex) {
            lista = null;
            ex.printStackTrace();
        }
        return lista;
    }    

    /**
     * Metodo que actualiza el cliente en la DB
     *
     * @param cl
     * @return
     */
    public String actualizarCliente(Cliente cl) {
        String resultado = "";
        try {
            Conexion conexion = new Conexion();
            Connection cnn = conexion.connect();
            String sql = "update skynet.cliente set id_cliente = '"+cl.getId_cliente()+"', nombre = '"+cl.getNombre()+"', direccion = '"+cl.getDireccion()+"', "
                    + " telefono = '"+cl.getTelefono()+"', email = '"+cl.getEmail()+"', lat = '"+cl.getLat()+"', "
                    + " lng = '"+cl.getLng()+"', fecha_registro = '"+cl.getFecha_registro()+"' where id_cliente = '"+cl.getId_cliente()+"'";
            System.out.print(sql);
            Statement st = cnn.createStatement();
            int rs = st.executeUpdate(sql);
            if (rs == 1) {
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

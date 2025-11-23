package Utilidades;

import java.io.Serializable;
import java.net.ConnectException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.model.SelectItem;

/**
 * 
 * @author Armando-Zar
 */
public class Catalogos implements Serializable {

    public Catalogos() {
    }

     /**
     * Metodo que carga listado de Clientes
     */
    public SelectItem[] cargarCliente() {
        SelectItem[] opcions = new SelectItem[]{new SelectItem("-1", "-")};
        try {

            Conexion conexion = new Conexion();
            Connection connect = conexion.connect();
            CallableStatement cs = connect.prepareCall("{call skynet.cargarCliente()}");
            cs.executeQuery();
            ResultSet rs = cs.getResultSet();
            if (rs.last()) {
                int size = rs.getRow();
                rs.beforeFirst();
                opcions = new SelectItem[size];
            }
            int i = 0;
            while (rs.next()) {
                String id = rs.getString("id_cliente");
                String descr = rs.getString("nombre");
                SelectItem d = new SelectItem();
                d.setValue(id);
                d.setLabel(descr);
                opcions[i] = d;
                i++;
            }
            connect.close();
        } catch (ConnectException | SQLException ex) {
            System.out.println("Error al Cargar la Lista");
        }
        return opcions;
    }

    /**
     * Metodo que carga la Dirección del Cliente
     */    
    public SelectItem[] cargarDireccion(int idCliente) {
        SelectItem[] opcions = new SelectItem[]{new SelectItem("-1", "-")};
        try {
            Conexion conexion = new Conexion();
            Connection connect = conexion.connect();
            CallableStatement cs = connect.prepareCall("{call skynet.cargarDireccion(" + idCliente + ")}");
            cs.executeQuery();
            ResultSet rs = cs.getResultSet();
            if (rs.last()) {
                int size = rs.getRow();
                rs.beforeFirst();
                opcions = new SelectItem[size];
            }
            int i = 0;
            while (rs.next()) {
                String id = rs.getString("id_cliente");
                String descr = rs.getString("direccion");
                SelectItem d = new SelectItem();
                d.setValue(id);
                d.setLabel(descr);
                opcions[i] = d;
                i++;
            }
            connect.close();
        } catch (ConnectException | SQLException ex) {
            System.out.println("Error al cargar la Lista");

        }
        return opcions;
    }  
    
    /**
     * Metodo que carga el Email del Cliente
     */    
    public SelectItem[] cargarEmail(int idCliente) {
        SelectItem[] opcions = new SelectItem[]{new SelectItem("-1", "-")};
        try {
            Conexion conexion = new Conexion();
            Connection connect = conexion.connect();
            CallableStatement cs = connect.prepareCall("{call skynet.cargarEmail(" + idCliente + ")}");
            cs.executeQuery();
            ResultSet rs = cs.getResultSet();
            if (rs.last()) {
                int size = rs.getRow();
                rs.beforeFirst();
                opcions = new SelectItem[size];
            }
            int i = 0;
            while (rs.next()) {
                String id = rs.getString("id_cliente");
                String descr = rs.getString("email");
                SelectItem d = new SelectItem();
                d.setValue(id);
                d.setLabel(descr);
                opcions[i] = d;
                i++;
            }
            connect.close();
        } catch (ConnectException | SQLException ex) {
            System.out.println("Error al cargar la Lista");

        }
        return opcions;
    }       

    /**
     * Metodo que carga la Latitud del Cliente
     */    
    public SelectItem[] cargarLatitud(int idCliente) {
        SelectItem[] opcions = new SelectItem[]{new SelectItem("-1", "-")};
        try {
            Conexion conexion = new Conexion();
            Connection connect = conexion.connect();
            CallableStatement cs = connect.prepareCall("{call skynet.cargarLatitud(" + idCliente + ")}");
            cs.executeQuery();
            ResultSet rs = cs.getResultSet();
            if (rs.last()) {
                int size = rs.getRow();
                rs.beforeFirst();
                opcions = new SelectItem[size];
            }
            int i = 0;
            while (rs.next()) {
                String id = rs.getString("id_cliente");
                String descr = rs.getString("lat");
                SelectItem d = new SelectItem();
                d.setValue(id);
                d.setLabel(descr);
                opcions[i] = d;
                i++;
            }
            connect.close();
        } catch (ConnectException | SQLException ex) {
            System.out.println("Error al cargar la Lista");

        }
        return opcions;
    }    
    
    /**
     * Metodo que carga la Longitud del Cliente
     */    
    public SelectItem[] cargarLongitud(int idCliente) {
        SelectItem[] opcions = new SelectItem[]{new SelectItem("-1", "-")};
        try {
            Conexion conexion = new Conexion();
            Connection connect = conexion.connect();
            CallableStatement cs = connect.prepareCall("{call skynet.cargarLongitud(" + idCliente + ")}");
            cs.executeQuery();
            ResultSet rs = cs.getResultSet();
            if (rs.last()) {
                int size = rs.getRow();
                rs.beforeFirst();
                opcions = new SelectItem[size];
            }
            int i = 0;
            while (rs.next()) {
                String id = rs.getString("id_cliente");
                String descr = rs.getString("lng");
                SelectItem d = new SelectItem();
                d.setValue(id);
                d.setLabel(descr);
                opcions[i] = d;
                i++;
            }
            connect.close();
        } catch (ConnectException | SQLException ex) {
            System.out.println("Error al cargar la Lista");

        }
        return opcions;
    }    
    
    /**
     * Metodo que carga listado de Supervisores
     */
    public SelectItem[] cargarSupervisor() {
        SelectItem[] opcions = new SelectItem[]{new SelectItem("-1", "-")};
        try {
            Conexion conexion = new Conexion();
            Connection connect = conexion.connect();
            CallableStatement cs = connect.prepareCall("{call skynet.cargarSupervisor()}");
            cs.executeQuery();
            ResultSet rs = cs.getResultSet();
            if (rs.last()) {
                int size = rs.getRow();
                rs.beforeFirst();
                opcions = new SelectItem[size];
            }
            int i = 0;
            while (rs.next()) {
                String id = rs.getString("id_supervisor");
                String descr = rs.getString("nombre");
                SelectItem d = new SelectItem();
                d.setValue(id);
                d.setLabel(descr);
                opcions[i] = d;
                i++;
            }
            connect.close();
        } catch (ConnectException | SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al cargar la Lista");
        }
        return opcions;
    }    
    
    /**
     * Metodo que carga listo de Tecnicos
     */
    public SelectItem[] cargarTecnico(int idSupervisor) {
        SelectItem[] opcions = new SelectItem[]{new SelectItem("-1", "-")};
        try {
            Conexion conexion = new Conexion();
            Connection connect = conexion.connect();
            CallableStatement cs = connect.prepareCall("{call skynet.cargarTecnico(" + idSupervisor + ")}");
            cs.executeQuery();
            ResultSet rs = cs.getResultSet();
            if (rs.last()) {
                int size = rs.getRow();
                rs.beforeFirst();
                opcions = new SelectItem[size];
            }
            int i = 0;
            while (rs.next()) {
                String id = rs.getString("id_tecnico");
                String descr = rs.getString("nombre");
                SelectItem d = new SelectItem();
                d.setValue(id);
                d.setLabel(descr);
                opcions[i] = d;
                i++;
            }
            connect.close();
        } catch (ConnectException | SQLException ex) {
            System.out.println("Error al cargar la Lista");

        }
        return opcions;
    }
    
     /**
     * Metodo que carga listado de Supervisores
     */
    public SelectItem[] cargarSoloTecnico() {
        SelectItem[] opcions = new SelectItem[]{new SelectItem("-1", "-")};
        try {
            Conexion conexion = new Conexion();
            Connection connect = conexion.connect();
            CallableStatement cs = connect.prepareCall("{call skynet.cargarSoloTecnico()}");
            cs.executeQuery();
            ResultSet rs = cs.getResultSet();
            if (rs.last()) {
                int size = rs.getRow();
                rs.beforeFirst();
                opcions = new SelectItem[size];
            }
            int i = 0;
            while (rs.next()) {
                String id = rs.getString("id_tecnico");
                String descr = rs.getString("nombre");
                SelectItem d = new SelectItem();
                d.setValue(id);
                d.setLabel(descr);
                opcions[i] = d;
                i++;
            }
            connect.close();
        } catch (ConnectException | SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al cargar la Lista");
        }
        return opcions;
    }    

    /**
     * Metodo que carga el Estado de la visita
     */
    public SelectItem[] cargarEstado() {
        SelectItem[] opcions = new SelectItem[]{new SelectItem("-1", "-")};
        try {

            Conexion conexion = new Conexion();
            Connection connect = conexion.connect();
            CallableStatement cs = connect.prepareCall("{call skynet.cargarEstado()}");
            cs.executeQuery();
            ResultSet rs = cs.getResultSet();
            if (rs.last()) {
                int size = rs.getRow();
                rs.beforeFirst();
                opcions = new SelectItem[size];
            }
            int i = 0;
            while (rs.next()) {
                String id = rs.getString("id_estado");
                String descr = rs.getString("descripcion");
                SelectItem d = new SelectItem();
                d.setValue(id);
                d.setLabel(descr);
                opcions[i] = d;
                i++;
            }
            connect.close();
        } catch (ConnectException | SQLException ex) {
            System.out.println("Error al cargar la Lista");
        }
        return opcions;
    }

    public SelectItem[] cargarRol () {
        SelectItem[] opcions = new SelectItem[]{new SelectItem("-1", "-")};
        try {

            Conexion conexion = new Conexion();
            Connection connect = conexion.connect();
            CallableStatement cs = connect.prepareCall("{call skynet.cargarRol()}");
            cs.executeQuery();
            ResultSet rs = cs.getResultSet();
            if (rs.last()) {
                int size = rs.getRow();
                rs.beforeFirst();
                opcions = new SelectItem[size];
            }
            int i = 0;
            while (rs.next()) {
                String id = rs.getString("id_rol");
                String descr = rs.getString("nombre");
                SelectItem d = new SelectItem();
                d.setValue(id);
                d.setLabel(descr);
                opcions[i] = d;
                i++;
            }
            connect.close();
        } catch (ConnectException | SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error al cargar la Lista");
        }
        return opcions;
    }
}

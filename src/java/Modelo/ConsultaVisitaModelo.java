package Modelo;

import Pojos.Visita;
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
public class ConsultaVisitaModelo implements Serializable {

    public ConsultaVisitaModelo() {
    }

    /**
     * Metodo que consulta el listado general de las Visitas en la DB
     *
     * @param listado
     * @return
     */
    
    // Consulta Visita por Id_visita
    public ArrayList<Visita> consultaVisitaId_Visita(String id_visita) {
        ArrayList<Visita> lista = new ArrayList<>();
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.connect();
            String sql = "select v.id_visita, v.id_cliente, (select c.nombre from skynet.cliente c where c.id_cliente = v.id_cliente) as nombre_cliente, v.falla_reportada, v.id_tecnico, (select t.nombre from skynet.tecnico t where t.id_tecnico = v.id_tecnico) as nombre_tecnico, v.id_supervisor, (select s.nombre from skynet.supervisor s where s.id_supervisor = v.id_supervisor) as nombre_supervisor, v.fecha_programada, v.estado, (select e.descripcion from skynet.estado e where e.id_estado = v.estado) as descripcion_estado, v.ingreso, v.egreso, v.punto_lat, (select c.lat from skynet.cliente c where c.id_cliente = v.punto_lat) as latitud_cliente, v.punto_lng, (select c.lng from skynet.cliente c where c.id_cliente = v.punto_lng) as longitud_cliente, v.nota from railway.visita v where v.id_visita = '" + id_visita + "' ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    Visita vi = new Visita();
                    vi.setId_visita(rs.getInt("id_visita"));
                    vi.setId_cliente(rs.getInt("id_cliente"));
                    vi.setNombre_cliente(rs.getString("nombre_cliente"));
                    vi.setFalla_reportada(rs.getString("falla_reportada"));
                    vi.setId_tecnico(rs.getInt("id_tecnico"));
                    vi.setNombre_tecnico(rs.getString("nombre_tecnico"));
                    vi.setId_supervisor(rs.getInt("id_supervisor"));
                    vi.setNombre_supervisor(rs.getString("nombre_supervisor"));
                    vi.setFecha_programada(rs.getString("fecha_programada"));
                    vi.setEstado(rs.getString("estado"));
                    vi.setDescripcion_estado(rs.getString("descripcion_estado"));
                    vi.setIngreso(rs.getString("ingreso"));
                    vi.setEgreso(rs.getString("egreso"));
                    vi.setPunto_lat(rs.getString("punto_lat"));
                    vi.setLatitud_cliente(rs.getString("latitud_cliente"));
                    vi.setPunto_lng(rs.getString("punto_lng"));
                    vi.setLongitud_cliente(rs.getString("longitud_cliente"));
                    vi.setNota(rs.getString("nota"));
                    lista.add(vi);
                }
            } 
        } catch (Exception ex) {
            lista = null;
            ex.printStackTrace();
        }
        return lista;
    }
    
    // Consulta de visitas en general
    public ArrayList<Visita> consultaVisitaGeneral() {
        ArrayList<Visita> lista = new ArrayList<>();
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.connect();
            String sql = "select v.id_visita, v.id_cliente, (select c.nombre from skynet.cliente c where c.id_cliente = v.id_cliente) as nombre_cliente, v.falla_reportada, v.id_tecnico, (select t.nombre from skynet.tecnico t where t.id_tecnico = v.id_tecnico) as nombre_tecnico, v.id_supervisor, (select s.nombre from skynet.supervisor s where s.id_supervisor = v.id_supervisor) as nombre_supervisor, v.fecha_programada, v.estado, (select e.descripcion from skynet.estado e where e.id_estado = v.estado) as descripcion_estado, v.ingreso, v.egreso, v.punto_lat, (select c.lat from skynet.cliente c where c.id_cliente = v.punto_lat) as latitud_cliente, v.punto_lng, (select c.lng from skynet.cliente c where c.id_cliente = v.punto_lng) as longitud_cliente, v.nota from railway.visita v where v.id_visita >= '1'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    Visita vi = new Visita();
                    vi.setId_visita(rs.getInt("id_visita"));
                    vi.setId_cliente(rs.getInt("id_cliente"));
                    vi.setNombre_cliente(rs.getString("nombre_cliente"));
                    vi.setFalla_reportada(rs.getString("falla_reportada"));
                    vi.setId_tecnico(rs.getInt("id_tecnico"));
                    vi.setNombre_tecnico(rs.getString("nombre_tecnico"));
                    vi.setId_supervisor(rs.getInt("id_supervisor"));
                    vi.setNombre_supervisor(rs.getString("nombre_supervisor"));
                    vi.setFecha_programada(rs.getString("fecha_programada"));
                    vi.setEstado(rs.getString("estado")); 
                    vi.setDescripcion_estado(rs.getString("descripcion_estado"));
                    vi.setIngreso(rs.getString("ingreso"));
                    vi.setEgreso(rs.getString("egreso"));
                    vi.setPunto_lat(rs.getString("punto_lat"));
                    vi.setLatitud_cliente(rs.getString("latitud_cliente"));
                    vi.setPunto_lng(rs.getString("punto_lng"));
                    vi.setLongitud_cliente(rs.getString("longitud_cliente"));
                    vi.setNota(rs.getString("nota")); 
                    lista.add(vi);
                }
            }
        } catch (Exception ex) {
            lista = null;
            ex.printStackTrace();
        }
        return lista;
    }

    // Consulta las Visitas de HOY de la TABLA
public ArrayList<Visita> consultaVisitasHoy(int idSupervisor, int idTecnico, int rol)
{
    ArrayList<Visita> lista = new ArrayList<>();
    try {
        Conexion conexion = new Conexion();
        Connection conn = conexion.connect();

        String sql = "select v.id_visita, v.id_cliente, "
                + "(select c.nombre from skynet.cliente c where c.id_cliente = v.id_cliente) as nombre_cliente, "
                + "v.falla_reportada, v.id_tecnico, "
                + "(select t.nombre from skynet.tecnico t where t.id_tecnico = v.id_tecnico) as nombre_tecnico, "
                + "v.id_supervisor, "
                + "(select s.nombre from skynet.supervisor s where s.id_supervisor = v.id_supervisor) as nombre_supervisor, "
                + "v.fecha_programada, v.estado, "
                + "(select e.descripcion from skynet.estado e where e.id_estado = v.estado) as descripcion_estado, "
                + "v.ingreso, v.egreso, v.punto_lat, "
                + "(select c.lat from skynet.cliente c where c.id_cliente = v.punto_lat) as latitud_cliente, "
                + "v.punto_lng, "
                + "(select c.lng from skynet.cliente c where c.id_cliente = v.punto_lng) as longitud_cliente, "
                + "v.nota "
                + "from railway.visita v "
                + "where DATE(v.fecha_programada) = CURDATE() ";

        // FILTRO SEGÚN ROL
        if (rol == 2) { // SUPERVISOR
            sql += "AND v.id_supervisor = " + idSupervisor + " ";
        }
        if (rol == 3) { // TECNICO
            sql += "AND v.id_tecnico = " + idTecnico + " ";
        }

        sql += "order by v.id_visita asc";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            Visita vi = new Visita();
            vi.setId_visita(rs.getInt("id_visita"));
            vi.setId_cliente(rs.getInt("id_cliente"));
            vi.setNombre_cliente(rs.getString("nombre_cliente"));
            vi.setFalla_reportada(rs.getString("falla_reportada"));
            vi.setId_tecnico(rs.getInt("id_tecnico"));
            vi.setNombre_tecnico(rs.getString("nombre_tecnico"));
            vi.setId_supervisor(rs.getInt("id_supervisor"));
            vi.setNombre_supervisor(rs.getString("nombre_supervisor"));
            vi.setFecha_programada(rs.getString("fecha_programada"));
            vi.setEstado(rs.getString("estado"));
            vi.setDescripcion_estado(rs.getString("descripcion_estado"));
            vi.setIngreso(rs.getString("ingreso"));
            vi.setEgreso(rs.getString("egreso"));
            vi.setPunto_lat(rs.getString("punto_lat"));
            vi.setLatitud_cliente(rs.getString("latitud_cliente"));
            vi.setPunto_lng(rs.getString("punto_lng"));
            vi.setLongitud_cliente(rs.getString("longitud_cliente"));
            vi.setNota(rs.getString("nota"));
            lista.add(vi);
        }

        rs.close();
        st.close();
        conn.close();
    } catch (Exception ex) {
        lista = null;
        ex.printStackTrace();
    }
    return lista;
}


 // Consulta las Visitas por SUPERVISOR   
public ArrayList<Visita> consultaVisitaPorSupervisor(int idSupervisor) {
    ArrayList<Visita> lista = new ArrayList<>();
    try {
        Conexion conexion = new Conexion();
        Connection conn = conexion.connect();

        String sql = "select v.id_visita, v.id_cliente, "
                + "(select c.nombre from skynet.cliente c where c.id_cliente = v.id_cliente) as nombre_cliente, "
                + "v.falla_reportada, v.id_tecnico, "
                + "(select t.nombre from skynet.tecnico t where t.id_tecnico = v.id_tecnico) as nombre_tecnico, "
                + "v.id_supervisor, "
                + "(select s.nombre from skynet.supervisor s where s.id_supervisor = v.id_supervisor) as nombre_supervisor, "
                + "v.punto_lat, "
                + "(select c.lat from skynet.cliente c where c.id_cliente = v.punto_lat) as latitud_cliente, " 
                + "v.punto_lng, "
                + "(select c.lng from skynet.cliente c where c.id_cliente = v.punto_lng) as longitud_cliente, "
                + "v.fecha_programada, v.estado, "
                + "(select e.descripcion from skynet.estado e where e.id_estado = v.estado) as descripcion_estado, "
                + "v.ingreso, v.egreso, v.punto_lat, v.punto_lng, v.nota "
                + "from railway.visita v "
                + "where v.id_supervisor = '" + idSupervisor + "' "
                + "order by v.id_visita asc";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            Visita vi = new Visita();
            vi.setId_visita(rs.getInt("id_visita"));
            vi.setId_cliente(rs.getInt("id_cliente"));
            vi.setNombre_cliente(rs.getString("nombre_cliente"));
            vi.setFalla_reportada(rs.getString("falla_reportada"));
            vi.setId_tecnico(rs.getInt("id_tecnico"));
            vi.setNombre_tecnico(rs.getString("nombre_tecnico"));
            vi.setId_supervisor(rs.getInt("id_supervisor"));
            vi.setNombre_supervisor(rs.getString("nombre_supervisor"));
            vi.setFecha_programada(rs.getString("fecha_programada"));
            vi.setEstado(rs.getString("estado"));
            vi.setDescripcion_estado(rs.getString("descripcion_estado"));
            vi.setIngreso(rs.getString("ingreso"));
            vi.setEgreso(rs.getString("egreso"));
            vi.setPunto_lat(rs.getString("punto_lat"));
            vi.setLatitud_cliente(rs.getString("latitud_cliente"));
            vi.setPunto_lng(rs.getString("punto_lng"));
            vi.setLongitud_cliente(rs.getString("longitud_cliente"));
            vi.setNota(rs.getString("nota"));
            lista.add(vi);
        }

        rs.close();
        st.close();
        conn.close();
    } catch (Exception ex) {
        lista = null;
        ex.printStackTrace();
    }
    return lista;
}

// Consulta las Visitas por TECNICO
public ArrayList<Visita> consultaVisitaPorTecnico(int idTecnico) {
    ArrayList<Visita> lista = new ArrayList<>();
    try {
        Conexion conexion = new Conexion();
        Connection conn = conexion.connect();

        String sql = "select v.id_visita, v.id_cliente, "
                + "(select c.nombre from skynet.cliente c where c.id_cliente = v.id_cliente) as nombre_cliente, "
                + "v.falla_reportada, v.id_tecnico, "
                + "(select t.nombre from skynet.tecnico t where t.id_tecnico = v.id_tecnico) as nombre_tecnico, "
                + "v.id_supervisor, "
                + "(select s.nombre from skynet.supervisor s where s.id_supervisor = v.id_supervisor) as nombre_supervisor, "
                + "v.punto_lat, "
                + "(select c.lat from skynet.cliente c where c.id_cliente = v.punto_lat) as latitud_cliente, " 
                + "v.punto_lng, "
                + "(select c.lng from skynet.cliente c where c.id_cliente = v.punto_lng) as longitud_cliente, "
                + "v.fecha_programada, v.estado, "
                + "(select e.descripcion from skynet.estado e where e.id_estado = v.estado) as descripcion_estado, "
                + "v.ingreso, v.egreso, v.punto_lat, v.punto_lng, v.nota "
                + "from railway.visita v "
                + "where v.id_tecnico = '" + idTecnico + "' "
                + "order by v.id_visita asc";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            Visita vi = new Visita();
            vi.setId_visita(rs.getInt("id_visita"));
            vi.setId_cliente(rs.getInt("id_cliente"));
            vi.setNombre_cliente(rs.getString("nombre_cliente"));
            vi.setFalla_reportada(rs.getString("falla_reportada"));
            vi.setId_tecnico(rs.getInt("id_tecnico"));
            vi.setNombre_tecnico(rs.getString("nombre_tecnico"));
            vi.setId_supervisor(rs.getInt("id_supervisor"));
            vi.setNombre_supervisor(rs.getString("nombre_supervisor"));
            vi.setFecha_programada(rs.getString("fecha_programada"));
            vi.setEstado(rs.getString("estado"));
            vi.setDescripcion_estado(rs.getString("descripcion_estado"));
            vi.setIngreso(rs.getString("ingreso"));
            vi.setEgreso(rs.getString("egreso"));
            vi.setPunto_lat(rs.getString("punto_lat"));
            vi.setLatitud_cliente(rs.getString("latitud_cliente"));
            vi.setPunto_lng(rs.getString("punto_lng"));
            vi.setLongitud_cliente(rs.getString("longitud_cliente"));
            vi.setNota(rs.getString("nota"));
            lista.add(vi);
        }

        rs.close();
        st.close();
        conn.close();
    } catch (Exception ex) {
        lista = null;
        ex.printStackTrace();
    }
    return lista;
}

// Obtiene el ID del Supervisor por Tecnico
public int obtenerIdSupervisorPorUsuario(String usuario) {
    int id = -1;
    try {
        Conexion conexion = new Conexion();
        Connection conn = conexion.connect();
        String sql = "select id_supervisor from railway.supervisor where usuario = '" + usuario + "'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            id = rs.getInt("id_supervisor");
        }
        rs.close();
        st.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return id;
}

// Obtiene el ID del Tecnico por el Supervisor
public int obtenerIdTecnicoPorUsuario(String usuario) {
    int id = -1;
    try {
        Conexion conexion = new Conexion();
        Connection conn = conexion.connect();
        String sql = "select id_tecnico from railway.tecnico where usuario = '" + usuario + "'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            id = rs.getInt("id_tecnico");
        }
        rs.close();
        st.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return id;
}

    /**
     * Metodo que actualiza las visitas en la DB
     *
     * @param cl
     * @return
     */
    public String actualizarVisita(Visita vi) {
        String resultado = "";
        try {
            Conexion conexion = new Conexion();
            Connection cnn = conexion.connect();
            String sql = "update railway.visita set id_visita = '"+vi.getId_visita()+"', id_cliente = '"+vi.getId_cliente()+"', falla_reportada = '"+vi.getFalla_reportada()+"', "
                    + " id_tecnico = '"+vi.getId_tecnico()+"', id_supervisor = '"+vi.getId_supervisor()+"', fecha_programada = '"+vi.getFecha_programada()+"', "
                    + " estado = '"+vi.getEstado()+"', ingreso = '"+vi.getIngreso()+"', egreso = '"+vi.getEgreso()+"', punto_lat = '"+vi.getPunto_lat()+"', "
                    + " punto_lng = '"+vi.getPunto_lng()+"', nota = '"+vi.getNota()+"' where id_visita = '"+vi.getId_visita()+"'";
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

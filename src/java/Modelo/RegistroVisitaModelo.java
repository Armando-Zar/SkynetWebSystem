package Modelo;

import Pojos.Visita;
import Utilidades.Conexion;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;

/**
 * 
 * @author Armando-Zar
 */
public class RegistroVisitaModelo implements Serializable{

    public RegistroVisitaModelo() {
    }
    
    /**
     * Metodo que agende una nueva Visita en la base de datos
     * @param visita
     * @return 
     */
    public String guardarVisita(Visita visita){
        String resultado = "";
        try{
            Conexion conexion = new Conexion();
            Connection conn = conexion.connect();
            Statement st = conn.createStatement();
            String sql = "insert into railway.visita(id_cliente,direccion,falla_reportada,id_tecnico,id_supervisor,fecha_programada,estado,ingreso,egreso,punto_lat,punto_lng,email,nota)"
                    + "values('"+visita.getId_cliente()+"', '"+visita.getDireccion()+"', '"+visita.getFalla_reportada()+"', '"+visita.getId_tecnico()+"', "
                    + "'"+visita.getId_supervisor()+"', '"+visita.getFecha_programada()+"','"+visita.getEstado()+"', "
                    + "'"+visita.getIngreso()+"', '"+visita.getEgreso()+"','"+visita.getPunto_lat()+"', "
                    + "'"+visita.getPunto_lng()+"', '"+visita.getEmail()+"', '"+visita.getNota()+"')";
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

package Pojos;

import java.io.Serializable;

/**
 *
 * @author Armando-Zar
 */
public class Visita implements Serializable{
    private int id_visita;
    private int id_cliente;
    private String direccion;
    private String falla_reportada;
    private int id_tecnico;
    private int id_supervisor;
    private String fecha_programada;    
    private String estado;
    private String ingreso;
    private String egreso;
    private String punto_lat;
    private String punto_lng;
    private String email;
    private String nota;
    private String fecha_registro;

/* variables que traen los datos de sus tablas
    Ej: nombre y direccion la trae de la tabla cliente,
    y luego estas variables harán match en el sql del Modelo de la Consulta Visita */    
    private String nombre_cliente;
    private String email_cliente;
    private String latitud_cliente;
    private String longitud_cliente;
    private String descripcion_estado;
    private String nombre_supervisor;
    private String nombre_tecnico;
    
    
    public Visita() {
    }    

    //Métodos gets y sets  

    public int getId_visita() {
        return id_visita;
    }

    public void setId_visita(int id_visita) {
        this.id_visita = id_visita;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getFalla_reportada() {
        return falla_reportada;
    }

    public void setFalla_reportada(String falla_reportada) {
        this.falla_reportada = falla_reportada;
    }

    public int getId_tecnico() {
        return id_tecnico;
    }

    public void setId_tecnico(int id_tecnico) {
        this.id_tecnico = id_tecnico;
    }

    public int getId_supervisor() {
        return id_supervisor;
    }

    public void setId_supervisor(int id_supervisor) {
        this.id_supervisor = id_supervisor;
    }

    public String getFecha_programada() {
        return fecha_programada;
    }

    public void setFecha_programada(String fecha_programada) {
        this.fecha_programada = fecha_programada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getEgreso() {
        return egreso;
    }

    public void setEgreso(String egreso) {
        this.egreso = egreso;
    }

    public String getPunto_lat() {
        return punto_lat;
    }

    public void setPunto_lat(String punto_lat) {
        this.punto_lat = punto_lat;
    }

    public String getPunto_lng() {
        return punto_lng;
    }

    public void setPunto_lng(String punto_lng) {
        this.punto_lng = punto_lng;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getDescripcion_estado() {
        return descripcion_estado;
    }

    public void setDescripcion_estado(String descripcion_estado) {
        this.descripcion_estado = descripcion_estado;
    }

    public String getNombre_supervisor() {
        return nombre_supervisor;
    }

    public void setNombre_supervisor(String nombre_supervisor) {
        this.nombre_supervisor = nombre_supervisor;
    }

    public String getNombre_tecnico() {
        return nombre_tecnico;
    }

    public void setNombre_tecnico(String nombre_tecnico) {
        this.nombre_tecnico = nombre_tecnico;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public String getLatitud_cliente() {
        return latitud_cliente;
    }

    public void setLatitud_cliente(String latitud_cliente) {
        this.latitud_cliente = latitud_cliente;
    }

    public String getLongitud_cliente() {
        return longitud_cliente;
    }

    public void setLongitud_cliente(String longitud_cliente) {
        this.longitud_cliente = longitud_cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
}

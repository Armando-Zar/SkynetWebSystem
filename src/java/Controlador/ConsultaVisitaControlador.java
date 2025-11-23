package Controlador;

import Modelo.ConsultaVisitaModelo;
import Pojos.Visita;
import Utilidades.Conexion;
import Utilidades.Mensajes;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.primefaces.PrimeFaces;

/**
 * 
 * @author Armando-Zar
 */
public class ConsultaVisitaControlador extends Conexion implements Serializable {

    private int id_visita;
    private ArrayList<Visita> listaVisitas = new ArrayList<>();
    private Visita visitaSeleccionada = new Visita();
    
//Atributos para actualizar los datos de la Visita   
    private int clienteActualizar;
    private String falla_reportadaActualizar;
    private int tecnicoActualizar;
    private int supervisorActualizar;
    private String fecha_programadaActualizar;
    private String estadoActualizar;
    private String ingresoActualizar;
    private String egresoActualizar;
    private String latitudActualizar;
    private String longitudActualizar;
    private String notaActualizar;
    
    private int totalVisitasHoy;
    private String urlPdfFile;
    private String nameReporte;

    public ConsultaVisitaControlador() {
    }

    /**
     * Metodo que consulta una visita por el ID
     */
    public void consultarVisitaId_Visita() {
            System.out.println("Botón de consulta por ID PRESIONADO");
        try {
            ConsultaVisitaModelo modelo = new ConsultaVisitaModelo();
            listaVisitas = modelo.consultaVisitaId_Visita(String.valueOf(getId_visita()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
   /**
    * Metodo que consulta el listado general de visitas
    */ 
public void consultarVisitaGeneral() {
        System.out.println("Botón de consulta general PRESIONADO");
    try {
        ConsultaVisitaModelo modelo = new ConsultaVisitaModelo();

        FacesContext context = FacesContext.getCurrentInstance();
        Object rolObj = context.getExternalContext().getSessionMap().get("SESSION_ROL");
        Object userObj = context.getExternalContext().getSessionMap().get("SESSION_USER");

        int rol = (rolObj == null) ? 0 : (int) rolObj;
        String usuario = (userObj == null) ? "" : (String) userObj;

        // Si es administrador -> todas
        if (rol == 1) {
            listaVisitas = modelo.consultaVisitaGeneral();
        }
        // Si es supervisor -> por id_supervisor
        else if (rol == 2) {
            // Intentar obtener id_supervisor desde la sesión (si fue guardado al logear)
            Object idSupObj = context.getExternalContext().getSessionMap().get("SESSION_SUPERVISOR");
            int idSupervisor = (idSupObj != null) ? (int) idSupObj : -1;

            // Si no hay id en sesión, intentar obtenerlo por usuario
            if (idSupervisor == -1) {
                idSupervisor = modelo.obtenerIdSupervisorPorUsuario(usuario);
            }

            if (idSupervisor != -1) {
                listaVisitas = modelo.consultaVisitaPorSupervisor(idSupervisor);
            } else {
                listaVisitas = new ArrayList<>(); // nada para mostrar
            }
        }
        // Si es tecnico -> por id_tecnico
        else if (rol == 3) {
            Object idTecObj = context.getExternalContext().getSessionMap().get("SESSION_TECNICO");
            int idTecnico = (idTecObj != null) ? (int) idTecObj : -1;

            if (idTecnico == -1) {
                idTecnico = modelo.obtenerIdTecnicoPorUsuario(usuario); // nuevo helper (ver modelo)
            }

            if (idTecnico != -1) {
                listaVisitas = modelo.consultaVisitaPorTecnico(idTecnico);
            } else {
                listaVisitas = new ArrayList<>();
            }
        } else {
            listaVisitas = new ArrayList<>();
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
}   

  /**
    * Metodo que consulta las visitas para HOY
    */ 
public void consultarVisitasHoy() {
    try {

        if (listaVisitas == null) {
            listaVisitas = new ArrayList<>();
        } else {
            listaVisitas.clear();
        }

        ConsultaVisitaModelo modelo = new ConsultaVisitaModelo();
        FacesContext context = FacesContext.getCurrentInstance();

        Object rolObj = context.getExternalContext().getSessionMap().get("SESSION_ROL");
        Object userObj = context.getExternalContext().getSessionMap().get("SESSION_USER");

        int rol = (rolObj == null) ? 0 : (int) rolObj;
        String usuario = (userObj == null) ? "" : (String) userObj;

        int idSupervisor = -1;
        int idTecnico = -1;

        // Si es supervisor
        if (rol == 2) {
            Object idSupObj = context.getExternalContext().getSessionMap().get("SESSION_SUPERVISOR");
            idSupervisor = (idSupObj != null) ? (int) idSupObj : modelo.obtenerIdSupervisorPorUsuario(usuario);
        }

        // Si es técnico
        else if (rol == 3) {
            Object idTecObj = context.getExternalContext().getSessionMap().get("SESSION_TECNICO");
            idTecnico = (idTecObj != null) ? (int) idTecObj : modelo.obtenerIdTecnicoPorUsuario(usuario);
        }

        listaVisitas = modelo.consultaVisitasHoy(idSupervisor, idTecnico, rol);

        // Para mostrar el Total de visitas para HOY
        totalVisitasHoy = listaVisitas.size();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    /**
     * Metodo que muestra la visita seleccionado de la tabla
     */
    public void mostrarVisitaSeleccionada() {
        try { 
            if (visitaSeleccionada != null) {
                setId_visita(visitaSeleccionada.getId_visita());
                setClienteActualizar(visitaSeleccionada.getId_cliente());
                setFalla_reportadaActualizar(visitaSeleccionada.getFalla_reportada());
                setTecnicoActualizar(visitaSeleccionada.getId_tecnico());
                setSupervisorActualizar(visitaSeleccionada.getId_supervisor());                
                setFecha_programadaActualizar(visitaSeleccionada.getFecha_programada());
                setEstadoActualizar(visitaSeleccionada.getEstado());
                setIngresoActualizar(visitaSeleccionada.getIngreso());
                setEgresoActualizar(visitaSeleccionada.getEgreso());
                setLatitudActualizar(visitaSeleccionada.getPunto_lat());                
                setLongitudActualizar(visitaSeleccionada.getPunto_lng());
                setNotaActualizar(visitaSeleccionada.getNota());
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('dlgActualizarVisita').show();");
            } else {
                Mensajes.mensaje("Advertencia", "Debe seleccionar primero una Visita", 3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que actualiza la Visita
     */
    public void actualizarVisita() {
        try {
            Visita visita = new Visita();
            visita.setId_visita(getId_visita());
            visita.setId_cliente(getClienteActualizar());            
            visita.setFalla_reportada(getFalla_reportadaActualizar());
            visita.setId_tecnico(getTecnicoActualizar());
            visita.setId_supervisor(getSupervisorActualizar());
            visita.setFecha_programada(getFecha_programadaActualizar());
            visita.setEstado(getEstadoActualizar());
            visita.setIngreso(getIngresoActualizar());
            visita.setEgreso(getEgresoActualizar());
            visita.setPunto_lat(getLatitudActualizar());
            visita.setPunto_lng(getLongitudActualizar());
            visita.setNota(getNotaActualizar());
            
            ConsultaVisitaModelo modelo = new ConsultaVisitaModelo();
            String resultado = modelo.actualizarVisita(visita);
            if (resultado.equals("exito")) {
                Mensajes.mensaje("Exito", "Visita actualizada correctamente", 1);
                consultarVisitaGeneral();
            } else {
                Mensajes.mensaje("Error", "Error al actualizar la Visita", 2);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que genera un reporte de la Visita al Cliente
     */
    public void generarReporteVisita() {
        try {
            //Obtenemos la conexion a la base de datos
            Conexion conn = new Conexion();
            Connection conect = conn.connect();
            
            //Obtener el archivo fuente del reporte
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String jrxmlFileName = ctx.getRealPath("/Reportes/ReporteVisita.jrxml");
            
            //Obtenemos la ubicacion donde vamos a desplegar el archivo del reporte
            ServletContext ctx2 = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String urlPdfFileName = ctx2.getRealPath("/Reportes");
            
            //Obtenemos la ubicacion donde vamos a desplegar la imagen "logo"
            ServletContext ctx3 = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String rutaImagen = ctx3.getRealPath("/Imagenes/logo.png");
            
            //Creamos los argumentos o parametros del reporte
            HashMap hm = null;
            hm = new HashMap();
            hm.put("id_visita", getId_visita());
            hm.put("logo", rutaImagen);
            
            //Compilamos el archivo jrxml y generamos el archivo jasper
            JasperReport reporte = JasperCompileManager.compileReport(jrxmlFileName);
            JasperPrint print = JasperFillManager.fillReport(reporte, hm, conect);
            JasperExportManager.exportReportToPdfFile(print, urlPdfFileName + "/Reporte de Visita.pdf");
            setUrlPdfFile(this.getUrlReportePdf() + "Reporte de Visita.pdf");
            setNameReporte("Reporte Visita a Cliente");
            conect.close();
            
            //Para mostrar el cuadro de dialogo      
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('dlgReporteVisita').show();");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Metodos gets y sets

    public int getId_visita() {
        return id_visita;
    }

    public void setId_visita(int id_visita) {
        this.id_visita = id_visita;
    }

    public ArrayList<Visita> getListaVisitas() {
        return listaVisitas;
    }

    public void setListaVisitas(ArrayList<Visita> listaVisitas) {
        this.listaVisitas = listaVisitas;
    }

    public Visita getVisitaSeleccionada() {
        return visitaSeleccionada;
    }

    public void setVisitaSeleccionada(Visita visitaSeleccionada) {
        this.visitaSeleccionada = visitaSeleccionada;
    }

    public String getFalla_reportadaActualizar() {
        return falla_reportadaActualizar;
    }

    public void setFalla_reportadaActualizar(String falla_reportadaActualizar) {
        this.falla_reportadaActualizar = falla_reportadaActualizar;
    }

    public int getTecnicoActualizar() {
        return tecnicoActualizar;
    }

    public void setTecnicoActualizar(int tecnicoActualizar) {
        this.tecnicoActualizar = tecnicoActualizar;
    }

    public int getSupervisorActualizar() {
        return supervisorActualizar;
    }

    public void setSupervisorActualizar(int supervisorActualizar) {
        this.supervisorActualizar = supervisorActualizar;
    }

    public String getFecha_programadaActualizar() {
        return fecha_programadaActualizar;
    }

    public void setFecha_programadaActualizar(String fecha_programadaActualizar) {
        this.fecha_programadaActualizar = fecha_programadaActualizar;
    }

    public String getEstadoActualizar() {
        return estadoActualizar;
    }

    public void setEstadoActualizar(String estadoActualizar) {
        this.estadoActualizar = estadoActualizar;
    }

    public String getIngresoActualizar() {
        return ingresoActualizar;
    }

    public void setIngresoActualizar(String ingresoActualizar) {
        this.ingresoActualizar = ingresoActualizar;
    }

    public String getEgresoActualizar() {
        return egresoActualizar;
    }

    public void setEgresoActualizar(String egresoActualizar) {
        this.egresoActualizar = egresoActualizar;
    }

    public String getLatitudActualizar() {
        return latitudActualizar;
    }

    public void setLatitudActualizar(String latitudActualizar) {
        this.latitudActualizar = latitudActualizar;
    }

    public String getLongitudActualizar() {
        return longitudActualizar;
    }

    public void setLongitudActualizar(String longitudActualizar) {
        this.longitudActualizar = longitudActualizar;
    }

    public String getNotaActualizar() {
        return notaActualizar;
    }

    public void setNotaActualizar(String notaActualizar) {
        this.notaActualizar = notaActualizar;
    }

    public String getUrlPdfFile() {
        return urlPdfFile;
    }

    public void setUrlPdfFile(String urlPdfFile) {
        this.urlPdfFile = urlPdfFile;
    }

    public String getNameReporte() {
        return nameReporte;
    }

    public void setNameReporte(String nameReporte) {
        this.nameReporte = nameReporte;
    }

    public int getClienteActualizar() {
        return clienteActualizar;
    }

    public void setClienteActualizar(int clienteActualizar) {
        this.clienteActualizar = clienteActualizar;
    }

    public int getTotalVisitasHoy() {
        return totalVisitasHoy;
    }

    public void setTotalVisitasHoy(int totalVisitasHoy) {
        this.totalVisitasHoy = totalVisitasHoy;
    }

    
public String getUsuarioLogueado() {
    Object user = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SESSION_USER");
    return user != null ? user.toString() : "";
}

public String getRolDescripcion() {
    Object rolObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SESSION_ROL");
    if (rolObj == null) return "";

    int rol = (int) rolObj;
    switch (rol) {
        case 1: return "Administrador";
        case 2: return "Supervisor";
        case 3: return "Técnico";
        default: return "Desconocido";
    }
}

    
}

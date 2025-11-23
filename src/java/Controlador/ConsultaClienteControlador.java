package Controlador;

import Modelo.ConsultaClienteModelo;
import Pojos.Cliente;
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
public class ConsultaClienteControlador extends Conexion implements Serializable {

    private int id_cliente;
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private Cliente clienteSeleccionado = new Cliente();
    
//Atributos para actualizar el cliente   
    private String nombreActualizar;
    private String direccionActualizar;
    private String telefonoActualizar;
    private String emailActualizar;
    private String latActualizar;
    private String lngActualizar;
    private String fecha_registroActualizar;
    
    private String urlPdfFile;
    private String nameReporte;

    public ConsultaClienteControlador() {
    }

    /**
     * Metodo que consulta un cliente por el ID
     */
    public void consultarClienteId_Cliente() {
        try {
            ConsultaClienteModelo modelo = new ConsultaClienteModelo();
            listaClientes = modelo.consultaClienteId_Cliente(String.valueOf(getId_cliente()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   /**
    * Metodo que consulta el listado general de clientes
    */ 
    public void consultarClienteGeneral() {
        try {
            ConsultaClienteModelo modelo = new ConsultaClienteModelo();
            listaClientes = modelo.consultaClienteGeneral();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    

    /**
     * Metodo que muestra el cliente seleccionado de la tabla
     */
    public void mostrarClienteSeleccionado() {
        try {
            if (clienteSeleccionado != null) {
                setId_cliente(clienteSeleccionado.getId_cliente());
                setNombreActualizar(clienteSeleccionado.getNombre());
                setDireccionActualizar(clienteSeleccionado.getDireccion());
                setTelefonoActualizar(clienteSeleccionado.getTelefono());
                setEmailActualizar(clienteSeleccionado.getEmail());                
                setLatActualizar(clienteSeleccionado.getLat());
                setLngActualizar(clienteSeleccionado.getLng());
                setFecha_registroActualizar(clienteSeleccionado.getFecha_registro());
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('dlgActualizarCliente').show();");
            } else {
                Mensajes.mensaje("Advertencia", "Debe seleccionar primero un Cliente", 3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que actualiza el cliente
     */
    public void actualizarCliente() {
        try {
            Cliente cliente = new Cliente();
            cliente.setId_cliente(getId_cliente());
            cliente.setNombre(getNombreActualizar());
            cliente.setDireccion(getDireccionActualizar());
            cliente.setTelefono(getTelefonoActualizar());
            cliente.setEmail(getEmailActualizar());
            cliente.setLat(getLatActualizar());
            cliente.setLng(getLngActualizar());
            cliente.setFecha_registro(getFecha_registroActualizar());

            ConsultaClienteModelo modelo = new ConsultaClienteModelo();
            String resultado = modelo.actualizarCliente(cliente);
            if (resultado.equals("exito")) {
                Mensajes.mensaje("Exito", "Cliente actualizado correctamente", 1);
            } else {
                Mensajes.mensaje("Error", "Error al actualizar el Cliente", 2);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que genera un reporte de la Visita al Cliente
     */
    public void generarReporteCliente() {
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
            hm.put("id_cliente", getId_cliente());
            hm.put("logo", rutaImagen);
            
            //Compilamos el archivo jrxml y generamos el archivo jasper
            JasperReport reporte = JasperCompileManager.compileReport(jrxmlFileName);
            JasperPrint print = JasperFillManager.fillReport(reporte, hm, conect);
            JasperExportManager.exportReportToPdfFile(print, urlPdfFileName + "/Reporte de Clientes.pdf");
            setUrlPdfFile(this.getUrlReportePdf() + "Reporte de Clientes.pdf");
            setNameReporte("Reporte Visita a Cliente");
            conect.close();
            
            //Para mostrar el cuadro de dialogo      
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('dlgReporteCliente').show();");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Metodos gets y sets

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public String getNombreActualizar() {
        return nombreActualizar;
    }

    public void setNombreActualizar(String nombreActualizar) {
        this.nombreActualizar = nombreActualizar;
    }

    public String getDireccionActualizar() {
        return direccionActualizar;
    }

    public void setDireccionActualizar(String direccionActualizar) {
        this.direccionActualizar = direccionActualizar;
    }

    public String getTelefonoActualizar() {
        return telefonoActualizar;
    }

    public void setTelefonoActualizar(String telefonoActualizar) {
        this.telefonoActualizar = telefonoActualizar;
    }

    public String getEmailActualizar() {
        return emailActualizar;
    }

    public void setEmailActualizar(String emailActualizar) {
        this.emailActualizar = emailActualizar;
    }

    public String getLatActualizar() {
        return latActualizar;
    }

    public void setLatActualizar(String latActualizar) {
        this.latActualizar = latActualizar;
    }

    public String getLngActualizar() {
        return lngActualizar;
    }

    public void setLngActualizar(String lngActualizar) {
        this.lngActualizar = lngActualizar;
    }


    public String getFecha_registroActualizar() {
        return fecha_registroActualizar;
    }

    public void setFecha_registroActualizar(String fecha_registroActualizar) {
        this.fecha_registroActualizar = fecha_registroActualizar;
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
  
}

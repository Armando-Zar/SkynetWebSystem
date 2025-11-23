package Controlador;

import Modelo.RegistroVisitaModelo;
import Pojos.Visita;
import Utilidades.Catalogos;
import Utilidades.Mensajes;
import java.io.Serializable;
import javax.faces.model.SelectItem;

/**
 *
 * @author Armando-Zar
 */
public class RegistroVisitaControlador implements Serializable {

    private String falla_reportada;
    private String fecha_programada;
    private String estado;
    private SelectItem[] listaEstados;
    private String nota;
    
    private int cliente;
    private SelectItem[] listaClientes;
    private int supervisor;
    private SelectItem[] listaSupervisor;
    private int tecnico;
    private SelectItem[] listaTecnico;
    private String direccion;
    private SelectItem[] listaDireccion;
    private String email;
    private SelectItem[] listaEmail;
    private String latitud;
    private SelectItem[] listaLatitud;
    private String longitud;
    private SelectItem[] listaLongitud;    
    
    public RegistroVisitaControlador() {
        cargarCliente();
        cargarDireccion();
        cargarEmail();
        cargarLatitud();
        cargarLongitud();
        cargarEstado();
        cargarSupervisor();
        cargarTecnico();     
    }
    
    private void cargarCliente() {
        try {
            Catalogos catalogo = new Catalogos();
            listaClientes = catalogo.cargarCliente();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }  

    /* método de tipo público para poder llamarlo desde la vista */    
    public void cargarDireccion() {
        try {
            Catalogos catalogo = new Catalogos();
            listaDireccion = catalogo.cargarDireccion(getCliente());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /* método de tipo público para poder llamarlo desde el Cliente */
    public void cargarEmail() {
        try {
            Catalogos catalogo = new Catalogos();
            listaEmail = catalogo.cargarEmail(getCliente());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }   
    
    /* método de tipo público para poder llamarlo desde el Cliente */
    public void cargarLatitud() {
        try {
            Catalogos catalogo = new Catalogos();
            listaLatitud = catalogo.cargarLatitud(getCliente());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    

    /* método de tipo público para poder llamarlo desde el Cliente */
    public void cargarLongitud() {
        try {
            Catalogos catalogo = new Catalogos();
            listaLongitud = catalogo.cargarLongitud(getCliente());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }     

    private void cargarEstado() {
        try {
            Catalogos catalogo = new Catalogos();
            listaEstados = catalogo.cargarEstado();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cargarSupervisor() {
        try {
            Catalogos catalogo = new Catalogos();
            listaSupervisor = catalogo.cargarSupervisor();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /* método de tipo público para poder llamarlo desde la vista */
    public void cargarTecnico() {
        try {
            Catalogos catalogo = new Catalogos();
            listaTecnico = catalogo.cargarTecnico(getSupervisor());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }  
    

    /**
     * Metodo que registra una nueva visita
     */
    public void registrarVisita() {
        try {
            String validar = validacion();
            if (validar.equals("")) {
                Visita visit = new Visita();
                visit.setId_cliente(getCliente());
                visit.setDireccion(getDireccion());
                visit.setFalla_reportada(getFalla_reportada());
                visit.setId_tecnico(getTecnico());
                visit.setId_supervisor(getSupervisor());
                visit.setFecha_programada(getFecha_programada());
                visit.setEstado(getEstado());
                visit.setPunto_lat(getLatitud());
                visit.setPunto_lng(getLongitud());
                visit.setEmail(getEmail());
                visit.setNota(getNota());

                RegistroVisitaModelo modelo = new RegistroVisitaModelo();
                String resultadoGuardar = modelo.guardarVisita(visit);

                if (resultadoGuardar.equals("exito")) {
                    Mensajes.mensaje("Éxito", "Visita registrada correctamente", 1);
                    setCliente(-1);
                    setDireccion("");
                    setFalla_reportada("");
                    setTecnico(-1);
                    setSupervisor(-1);
                    setFecha_programada("");
                    setEstado("");
                    setLatitud("");
                    setLongitud("");
                    setEmail("");
                    setNota("");
                } else {
                    Mensajes.mensaje("Error", "Datos de la visita no registrados", 2);
                }
            } else {
                Mensajes.mensaje("Advertencia", validar, 3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que valida los datos del módulo registrar visita
     *
     * @return
     */
    public String validacion() {
        String resultado = "";
        try {
            if (String.valueOf(getCliente()).equals("")) {
                resultado = "Debe seleccionar el Cliente.";
            } else if (getDireccion().equals("")) {
                resultado = "Debe seleccionar la Dirección.";
            } else if (String.valueOf(getSupervisor()).equals("")) {
                resultado = "Debe seleccionar el Supervisor.";
            } else if (String.valueOf(getTecnico()).equals("")) {
                resultado = "Debe seleccionar el Tecnico.";
            } else if (getFecha_programada().equals("")) {
                resultado = "Debe ingresar la fecha para la visita.";
            } else if (getEstado().equals("")) {
                resultado = "Debe seleccionar el Estado de esta visita.";
            } else if (getEmail().equals("")) {
                resultado = "Debe seleccionar el Email.";
            } else if (getLatitud().equals("")) {
                resultado = "Debe seleccionar la Latitud.";
            } else if (getLongitud().equals("")) {
                resultado = "Debe seleccionar la Longitud.";
            } else if (getFalla_reportada().equals("")) {
                resultado = "Debe ingresar la Falla reportada.";
            }

        } catch (Exception ex) {
            resultado = "Error en la validación de los registros";
            ex.printStackTrace();
        }

        return resultado;
    }

    /**
     * metodo que comprubea la LATITUD mínima y máxima
     
    public void comprobarLatitud() {
        try {
            if (getPunto_lat().length() >= 3 && getPunto_lat().length() <= 11) {
                Mensajes.mensaje("Mensaje", "Latitud Valida", 1);
            } else {
                Mensajes.mensaje("Mensaje", "Verificar la Latitud ingresada", 3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

 */
    
    /**
     * metodo que comprubea la LONGITUD mínima y máxima
         
    public void comprobarLongitud() {
        try {
            if (getPunto_lng().length() >= 3 && getPunto_lng().length() <= 11) {
                Mensajes.mensaje("Mensaje", "Longitud Valida", 1);
            } else {
                Mensajes.mensaje("Mensaje", "Verificar la Longitud ingresada", 3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
*/
    public String getFalla_reportada() {
        return falla_reportada;
    }

    public void setFalla_reportada(String falla_reportada) {
        this.falla_reportada = falla_reportada;
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

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public SelectItem[] getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(SelectItem[] listaClientes) {
        this.listaClientes = listaClientes;
    }

    public SelectItem[] getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(SelectItem[] listaEstados) {
        this.listaEstados = listaEstados;
    }

    public int getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(int supervisor) {
        this.supervisor = supervisor;
    }

    public SelectItem[] getListaSupervisor() {
        return listaSupervisor;
    }

    public void setListaSupervisor(SelectItem[] listaSupervisor) {
        this.listaSupervisor = listaSupervisor;
    }

    public int getTecnico() {
        return tecnico;
    }

    public void setTecnico(int tecnico) {
        this.tecnico = tecnico;
    }

    public SelectItem[] getListaTecnico() {
        return listaTecnico;
    }

    public void setListaTecnico(SelectItem[] listaTecnico) {
        this.listaTecnico = listaTecnico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public SelectItem[] getListaDireccion() {
        return listaDireccion;
    }

    public void setListaDireccion(SelectItem[] listaDireccion) {
        this.listaDireccion = listaDireccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SelectItem[] getListaEmail() {
        return listaEmail;
    }

    public void setListaEmail(SelectItem[] listaEmail) {
        this.listaEmail = listaEmail;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public SelectItem[] getListaLatitud() {
        return listaLatitud;
    }

    public void setListaLatitud(SelectItem[] listaLatitud) {
        this.listaLatitud = listaLatitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public SelectItem[] getListaLongitud() {
        return listaLongitud;
    }

    public void setListaLongitud(SelectItem[] listaLongitud) {
        this.listaLongitud = listaLongitud;
    }

}

package Controlador;

import Modelo.RegistroUsuarioModelo;
import Pojos.Usuario;
import Utilidades.Catalogos;
import Utilidades.Mensajes;
import java.io.Serializable;
import javax.faces.model.SelectItem;

public class RegistroUsuarioControlador implements Serializable {

    private String login_name;
    private String password;
    private int estado;
    private int id_rol;
    private int id_supervisor;
    private int id_tecnico;
    private RegistroUsuarioModelo modelo = new RegistroUsuarioModelo();
    private Catalogos catalogo = new Catalogos();

    private SelectItem[] listaRol;
    private SelectItem[] listaSupervisor;
    private SelectItem[] listaTecnico;

    public RegistroUsuarioControlador() {
        cargarListas();
        estado = 1; // ← Estado predeterminado: Activo
    }

    /**
     * Cargar listas de rol, supervisor y tecnico independiente
     */
    public void cargarListas() {
        listaRol = catalogo.cargarRol();
        listaSupervisor = catalogo.cargarSupervisor();
        listaTecnico = catalogo.cargarSoloTecnico();
    }

     /**
     * Metodo que registra un nuevo usuario
     */
    public void registrarUsuario() {
    try {
        String validar = validacion();
        if (validar.equals("")) {

            Usuario user = new Usuario();
            user.setLogin_name(getLogin_name());
            user.setPassword(getPassword());
            user.setId_rol(getId_rol());
            user.setId_supervisor(getId_supervisor());
            user.setId_tecnico(getId_tecnico());
            user.setEstado(getEstado());

            RegistroUsuarioModelo modelo = new RegistroUsuarioModelo();
            String resultadoGuardar = modelo.guardarUsuario(user);

                if (resultadoGuardar.equals("exito")) {
                    Mensajes.mensaje("Éxito", "Usuario registrado correctamente", 1);
                    
                    setLogin_name("");
                    setPassword("");
                    setId_rol(-1);
                    setId_supervisor(-1);
                    setId_tecnico(-1);
                    setEstado(-1);
          } else {
                Mensajes.mensaje("Error", "Datos del Usuario no registrados", 2);
            }

        } else {
            Mensajes.mensaje("Advertencia", validar, 3);
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
    
    /**
     * Metodo que valida los datos del usuaruio
     */
    public String validacion() {
    String resultado = "";
    try {
            if (getLogin_name().equals("")) {
                resultado = "Debe ingresar nombre Usuario.";
            } else if (getPassword().equals("")) {
                resultado = "Debe ingresar Contraseña.";
            } else if (String.valueOf(getId_rol()).equals("")) {
                resultado = "Debe ingresar el Rol.";
            } else if (String.valueOf(getId_supervisor()).equals("")) {
                resultado = "Debe ingresar el Supervisor (si aplica).";
            } else if (String.valueOf(getId_tecnico()).equals("")) {
                resultado = "Debe ingresar el Técnico (si aplica).";
            } else if (String.valueOf(getId_supervisor()).equals("")) {
                resultado = "Debe ingresar el Estado (Activo/Inactivo).";
            }

        } catch (Exception ex) {
        resultado = "Error en la validación de los registros.";
        ex.printStackTrace();
    }

    return resultado;
}

    // GETS Y SETS

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public RegistroUsuarioModelo getModelo() {
        return modelo;
    }

    public void setModelo(RegistroUsuarioModelo modelo) {
        this.modelo = modelo;
    }

    public Catalogos getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogos catalogo) {
        this.catalogo = catalogo;
    }

    public SelectItem[] getListaRol() {
        return listaRol;
    }

    public void setListaRol(SelectItem[] listaRol) {
        this.listaRol = listaRol;
    }

    public SelectItem[] getListaSupervisor() {
        return listaSupervisor;
    }

    public void setListaSupervisor(SelectItem[] listaSupervisor) {
        this.listaSupervisor = listaSupervisor;
    }

    public SelectItem[] getListaTecnico() {
        return listaTecnico;
    }

    public void setListaTecnico(SelectItem[] listaTecnico) {
        this.listaTecnico = listaTecnico;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public int getId_supervisor() {
        return id_supervisor;
    }

    public void setId_supervisor(int id_supervisor) {
        this.id_supervisor = id_supervisor;
    }

    public int getId_tecnico() {
        return id_tecnico;
    }

    public void setId_tecnico(int id_tecnico) {
        this.id_tecnico = id_tecnico;
    }

}

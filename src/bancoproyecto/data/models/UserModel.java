package bancoproyecto.data.models;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    public UID GUID;
    private String nombre;
    private String usuario;
    private String contrasena;
    private List<AccountModel> cuentas;


    /**
     * Constructor usado para iniciar sesión.
     * @param usuario Nombre de usuario
     * @param contrasena Contraseña del usuario
     */
    public UserModel(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    /**
     * Constructor usado para crear un nuevo usuario.
     * @param nombre Nombre del usuario
     * @param usuario Nombre de usuario
     * @param contrasena Contraseña del usuario
     */
    public UserModel(String nombre, String usuario, String contrasena) {
        this.GUID = new UID();
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.cuentas = new ArrayList<>();
    }

    public UID getGUID() {
        return GUID;
    }

    public void setGUID(UID GUID) {
        this.GUID = GUID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<AccountModel> getCuentas() {
        return cuentas;
    }
    
    public void setCuentas(List<AccountModel> cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public String toString() {
        return String.format("GUID: %s, Nombre: %s, Usuario: %s, y su contraseña: %s", GUID, nombre, usuario, contrasena); // %s es para string.
    }
}

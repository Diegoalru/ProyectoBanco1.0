package bancoproyecto.data.models;

import java.rmi.server.UID;

public class UsuarioModel {

    public UID GUID;
    private String nombre;
    private String usuario;
    private String contrasena;

    public UsuarioModel(String nombre, String usuario, String contrasena) {
        this.GUID = new UID();
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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

    @Override
    public String toString() {
        return String.format("GUID: %s, Nombre: %s, Usuario: %s, y su contraseña: %s", GUID, nombre, usuario, contrasena); // %s es para string.
    }
}

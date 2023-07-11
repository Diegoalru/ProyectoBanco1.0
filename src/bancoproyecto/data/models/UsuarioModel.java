package bancoproyecto.data.models;

import java.rmi.server.UID;

public class UsuarioModel {

    public UID GUID;
    private String usuario;
    private String contrasena;

    public UsuarioModel(String usuario, String contrasena) {
        this.GUID = new UID();
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public UID getGUID() {
        return GUID;
    }

    public void setGUID(UID GUID) {
        this.GUID = GUID;
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
        return String.format("GUID: %s, Usuario: %s, y su contraseña: %s", GUID, usuario, contrasena); // %s es para string.
    }
}

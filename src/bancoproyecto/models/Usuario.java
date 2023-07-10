package bancoproyecto.models;

public class Usuario {

    private String usuario;
    private String contrasena;

    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public void setContrasena(String nuevaContrasena) {
        this.contrasena = nuevaContrasena;
    }
    
    @Override
    public String toString(){
        return String.format("Usuario: %s, y su contraseña: %s", usuario, contrasena); // %s es para string.
    }
}

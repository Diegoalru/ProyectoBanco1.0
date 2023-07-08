package bancoproyecto;

class Usuario {

    private String usuario;
    private String contraseña;

    public Usuario(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return this.contraseña;
    }

    public void setContraseña(String nuevaContraseña) {
        this.contraseña = nuevaContraseña;
    }
    
    @Override
    public String toString(){
        return "Usuario: " + usuario + ", y su contraseña: " + contraseña;
    }
}

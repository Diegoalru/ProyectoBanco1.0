package bancoproyecto.data;

import bancoproyecto.data.models.UserModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserRepository {

    /**
     * Objeto de tipo Usuario que se verificará en el inicio de sesion.
     */
    private static final List<UserModel> Users = new ArrayList<>();

    /**
     * Obtiene el usuario que se encuentra en UsuarioData
     * @return Usuario que se encuentra en UsuarioData
     */
    public static List<UserModel> GetUsers(){
        return Users;
    }

    /**
     * Guarda uno o varios usuarios en UsuarioData.
     *
     * @param user Usuario que se desea guardar
     */
    public static void NewUser(UserModel... user){
        try{
            Collections.addAll(Users, user);
        } catch(NullPointerException e){
            System.err.println("Error no se pudo guardar el usuario");
        } catch(Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Inicia sesion con el usuario que se encuentra en UsuarioData
     * @param user Usuario que se desea iniciar sesion
     * @return True si el usuario coincide con el usuario en UsuarioData
     */
    public static UserModel Login(UserModel user){
        for (var item : Users) {
            if (item.getUsuario().equals(user.getUsuario()) &&
                    item.getContrasena().equals(user.getContrasena())) {
                return item;
            }
        }
        return null;
    }

    /**
     * Verifica si el usuario que se desea registrar ya existe en UsuarioData
     * @param user Usuario que se desea verificar
     * @return True si el usuario ya existe en UsuarioData
     */
    public static Boolean UserExists(UserModel user) {
        for (var item : Users) {
            if (item.getUsuario().equals(user.getUsuario())) {
                return true;
            }
        }
        return false;
    }
}

package bancoproyecto.data;

import bancoproyecto.data.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    /**
     * Lista de {@link UserModel usuarios} en el sistema.
     */
    private static final List<UserModel> Users = new ArrayList<>();

    /**
     * Obtiene la lista de usuarios que se encuentran en {@code Users}
     * @return Lista de {@link UserModel usuarios}
     */
    public static List<UserModel> GetUsers(){
        return Users;
    }

    /**
     * Guarda uno o varios {@link UserModel usuarios}.
     *
     * @param user {@link UserModel Usuario} que se desea guardar.
     */
    public static void NewUser(UserModel user){
        Users.add(user);
    }

    /**
     * Inicia sesión con credenciales proporcionadas por el usuario.
     *
     * @param user {@link UserModel Usuario} que se desea iniciar sesión
     * @return {@link UserModel Usuario} que inició sesión.
     */
    public static UserModel Login(UserModel user){
        for (var item : Users) {
            if (item.getUsername().equals(user.getUsername()) &&
                    item.getPassword().equals(user.getPassword())) {
                return item;
            }
        }
        return null;
    }

    /**
     * Verifica si el usuario que se desea registrar ya existe en el sistema.
     *
     * @param user {@link UserModel Usuario} que se desea verificar.
     * @return {@link Boolean} que indica si el usuario existe o no.
     */
    public static Boolean UserExists(UserModel user) {
        for (var item : Users) {
            if (item.getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }
}

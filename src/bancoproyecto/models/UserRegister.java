package bancoproyecto.models;

public record UserRegister(String name, String username, String password){
    public UserRegister{
        if(name.isBlank()){
            throw new IllegalArgumentException("Name can't be blank");
        }

        if(username.isBlank()){
            throw new IllegalArgumentException("Username can't be blank");
        }

        if(password.isBlank()){
            throw new IllegalArgumentException("Password can't be blank");
        }
    }

    @Override
    public String toString(){
        return String.format("Name: %s, Username: %s, Password: %s", name, username, password);
    }
}

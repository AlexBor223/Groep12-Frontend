package models;

public class Admin {

    private String Username;
    private String Password;
    private String Email;


    public Admin(String Username, String Password){
        this.Username = Username;
        this.Password = Password;
    }


    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }
}

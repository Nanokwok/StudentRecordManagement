public class Admin {
    private String name;  // name of the admin
    private int password;  // password of the admin

    public Admin(String name, int password) {  // constructor
        setPassword(password);
        this.name = name;
    }

    public String getName() {  // getter for name
        return name;
    }

    public int getPassword() {  // getter for password
        return password;
    }

    public void setPassword(int idNumber) {  // setter for password
        if (idNumber >= 10000000 && idNumber <= 99999999) {
            this.password = idNumber;
        } else {
            this.password = 0;
        }
    }
}
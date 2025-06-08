package Models;

public abstract class User {

    protected String name;
    protected String sex;
    protected String email;
    protected String password;
    protected long phone_number;

    public User(String name, String sex, String email, String password, long phone_number) {
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public static User parseUser(String line) {
        if (line.startsWith("\\ADMIN")) {
            Admin admin = new Admin();
            admin.fromFileString(line);
            return admin;
        } else if (line.startsWith("\\CLIENT")) {
            Client client = new Client();
            client.fromFileString(line);
            return client;
        }
        return null;
    }

    public abstract String toFileString();

    public abstract User fromFileString(String line);
}

package Models;

public class Admin extends User {

    public Admin(String name, String sex, String email, String password, long phone_number) {
        super(name, sex, email, password, phone_number);
    }

    public Admin() {
        super(null, null, null, null, 0);
    }
    
    @Override
    public String toFileString() {
        return String.join(" - ",
                "ADMIN",
                name,
                sex,
                email,
                password,
                String.valueOf(phone_number)
        );
    }

    @Override
    public User fromFileString(String line) {
        String[] parts = line.split(" - ");

        if (parts.length < 5) {
            throw new IllegalArgumentException("Formato incorrecto en línea: " + line);
        }

        Admin admin = new Admin();
        admin.setName(parts[0]);
        admin.setSex(parts[1]);
        admin.setEmail(parts[2]);
        admin.setPassword(parts[3]);
        admin.setPhone_number(Long.parseLong(parts[4]));

        return admin;
    }
}

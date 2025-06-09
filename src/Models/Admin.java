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

        if (parts.length < 6) {
            throw new IllegalArgumentException("Formato incorrecto en línea: " + line);
        }

        Admin admin = new Admin();
        admin.setName(parts[1]);
        admin.setSex(parts[2]);
        admin.setEmail(parts[3]);
        admin.setPassword(parts[4]);
        admin.setPhone_number(Long.parseLong(parts[5]));

        return admin;
    }
}

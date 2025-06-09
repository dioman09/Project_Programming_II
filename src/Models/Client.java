package Models;

public class Client extends User {
    
    public Client(String name, String sex, String email, String password, long phone_number) {
        super(name, sex, email, password, phone_number);
    }
    
    public Client() {
        super(null, null, null, null, 0);
    }
    
    @Override
    public String toFileString() {
        return String.join(" - ",
                "CLIENT",
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
        
        Client client = new Client();
        client.setName(parts[1]);
        client.setSex(parts[2]);
        client.setEmail(parts[3]);
        client.setPassword(parts[4]);
        client.setPhone_number(Long.parseLong(parts[5]));        
        
        return client;
    }
}

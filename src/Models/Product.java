package Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Product {

    private LocalDateTime date_purchase;
    private int id;
    private String name;
    private String brand;
    private String sex;
    private String size;
    private float price;
    private String description;
    private String email_buyer;
    private List<String> urls_images;

    public Product(int id, String name, String brand, String sex, String size, float price, String description, String email_buyer, List<String> urls_images) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.sex = sex;
        this.size = size;
        this.price = price;
        this.description = description;
        this.email_buyer = email_buyer;
        this.urls_images = urls_images;
    }

    public Product() {
    }

    public LocalDateTime getDate_purchase() {
        return date_purchase;
    }

    public void setDate_purchase(LocalDateTime date_purchase) {
        this.date_purchase = date_purchase;
    }

    public String getDate_purchase_formatt(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = formatter.format(localDateTime);

        return date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail_buyer() {
        return email_buyer;
    }

    public void setEmail_buyer(String email_buyer) {
        this.email_buyer = email_buyer;
    }

    public List<String> getUrls_images() {
        return urls_images;
    }

    public void setUrls_images(List<String> urls_images) {
        this.urls_images = urls_images;
    }

    public String toFileString() {

        String date = "NULL";
        
        if (date_purchase != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date = formatter.format(date_purchase);
        }

        return String.join("|",
                date,
                String.valueOf(id),
                name,
                brand,
                sex,
                size,
                String.valueOf(price),
                description,
                email_buyer,
                String.join(";", urls_images)
        );
    }

    public static Product fromFileString(String line) {
        String[] parts = line.split("\\|");

        if (parts.length < 10) {
            throw new IllegalArgumentException("Formato incorrecto en línea: " + line);
        }

        Product product = new Product();

        if (!parts[0].equals("NULL")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(parts[0], formatter);
            product.setDate_purchase(fecha.atStartOfDay());
        }

        product.setId(Integer.parseInt(parts[1]));
        product.setName(parts[2]);
        product.setBrand(parts[3]);
        product.setSex(parts[4]);
        product.setSize(parts[5]);

        try {
            product.setPrice(Float.parseFloat(parts[6]));
        } catch (NumberFormatException e) {
            product.setPrice(0.0f);
        }

        product.setDescription(parts[7]);
        product.setEmail_buyer(parts[8]);

        String[] urls = parts[9].split(";");
        product.setUrls_images(Arrays.asList(urls));

        return product;
    }
}

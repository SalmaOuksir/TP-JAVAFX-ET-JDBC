package ma.enset.JavaFxJdbc.dao.entities;

import java.io.Serializable;

public class Product implements Serializable {
    private long id;
    private String name;
    private String reference;
    private float price;
    private Category category;

    public Product() {
    }

    public Product(Category category ,String name, String reference, float price) {
        this.name = name;
        this.reference = reference;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public float getprice() {
        return price;
    }

    public void setprice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                ", price=" + price +
                '}';
    }
}

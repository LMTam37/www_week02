package vn.edu.iuh.fit.week02.models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "product_price")
public class ProductPrice {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @Column(name = "price_date_time")
    private Date priceDateTime;

    @Column(name = "price")
    private Double price;

    @Column(name = "note")
    private String note;

    public ProductPrice() {
    }

    public ProductPrice(Product product, Date priceDateTime, Double price, String note) {
        this.product = product;
        this.priceDateTime = priceDateTime;
        this.price = price;
        this.note = note;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getPriceDateTime() {
        return priceDateTime;
    }

    public void setPriceDateTime(Date priceDateTime) {
        this.priceDateTime = priceDateTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ProductPrice{" + "product=" + product + ", priceDateTime=" + priceDateTime + ", price=" + price + ", note='" + note + '\'' + '}';
    }
}
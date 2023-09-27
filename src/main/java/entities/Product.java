
package entities;

/**
 *
 * @author pcm23
 * 
 * 
 */

// import java.util.Date;

public class Product {

    private String code;
    private String name;
    private int quantity;
    private String type; // daily or long
    private String manufacturingDate;
    private String expirationDate;

    public Product() {
    }

    public Product(String code, String name, int quantity, String type, String manufacturingDate,
            String expirationDate) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.type = type;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setManufacturingDate(String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public void print() {
       
       
        System.out.format("%-10s%-17s%-15d%-15s%-25s%-25s\n", code, name, quantity, type, manufacturingDate,
                expirationDate);

    }

    @Override
    public String toString(){
        return code + ", " + name + ", " + quantity + ", " + type + ", " + manufacturingDate + ", " + expirationDate; 
    }

}

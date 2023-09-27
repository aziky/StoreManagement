
package entities;

import java.util.List;


public class WareHouse {
    private String code;
    private String time;
    private List<Product> listProduct;

    public WareHouse() {
    }

    public WareHouse(String code, String time, List<Product> listProduct) {
        this.code = code;
        this.time = time;
        this.listProduct = listProduct;
    }

    public String getCode() {
        return code;
    }

    public String getTime() {
        return time;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public boolean getProductCode(String code){
        for (Product product : listProduct){
            if (code.equals(product.getCode())){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String result = code + ", " +  time ;
        
        for (Product product : listProduct){
            String productCode = product.getCode();
            result += (", " + productCode);
        }
        
        return result;
    }

    
    public void print() {
        // String result = code + ", " +  time ;
        String productCode = "";
        
        for (Product product : listProduct){
             productCode = product.getCode();
            // result += (", " + productCode);
        }
        
        System.out.format("%-15s%-20s%-15s\n",code, time, productCode);
    }
}

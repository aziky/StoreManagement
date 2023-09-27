package manage;

// import entities.DailyProduct;
// import entities.LongProduct;
import entities.Product;
import utils.FileManage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductManage implements IProductManage {
    FileManage fileManage = new FileManage();

    public List<Product> listProduct;

    public ProductManage() {
        listProduct = new ArrayList<>();
    }

  

    @Override
    public void addProduct(Product product) {
        listProduct.add(product);
    }

    @Override
    public Product updateProduct(Product oldProduct, Product newProduct) {
        if (newProduct.getCode().isBlank()) {
            newProduct.setCode(oldProduct.getCode());
        }

        if (newProduct.getName().isBlank()) {
            newProduct.setName(oldProduct.getName());
        }

        if (newProduct.getQuantity() == -1) {
            newProduct.setQuantity(oldProduct.getQuantity());
        }

        if (newProduct.getType().isBlank()) {
            newProduct.setType(oldProduct.getType());
        }

        if (newProduct.getManufacturingDate().isBlank()) {
            newProduct.setManufacturingDate(oldProduct.getManufacturingDate());
        }

        if (newProduct.getExpirationDate().isBlank()) {
            newProduct.setExpirationDate(oldProduct.getExpirationDate());
        }

        oldProduct = newProduct;
        return oldProduct;
    }

    @Override
    public void deleteProduct(Product product) {
        listProduct.remove(product);
    }

    @Override

    public void showAllProduct(boolean option) {
        if (option) {
            try {
                showByFile("product.dat");
            } catch (IOException e) {
                System.err.println("Not found file!");
            }
        } else {
            show(listProduct);
        }
    }



    public void show(List<Product> list) {
        System.out.println("\n==========Product List===========");
        System.out.format("%-10s%-17s%-15s%-15s%-25s%-25s\n", "ID", "NAME", "QUANTITY", "TYPE", "MANUFACTURING DATE", "EXPIRATION DATE");
        for (Product product : list) {
            product.print();
        }
    }

    public void showByFile(String pathFile) throws FileNotFoundException, IOException {
        List<Product> data = fileManage.loadData(pathFile);
        show(data);
    }

    public Product getProductByCode(String code) {
        for (Product product : listProduct) {
            if (product.getCode().equals(code)) {
                return product;
            }
        }
        return null;
    }
}

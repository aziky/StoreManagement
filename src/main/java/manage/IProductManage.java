package manage;

import entities.Product;
public interface IProductManage {
     //add 
    void addProduct(Product product);
    //update
    Product updateProduct(Product oldProduct, Product newProduct);
    //delete
    void deleteProduct(Product product);
    //show
    void showAllProduct(boolean option);
}

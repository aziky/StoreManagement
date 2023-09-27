package report;

import java.util.List;

import entities.Product;
// import entities.WareHouse;/
// import manage.ProductManage;
// import manage.WareHouseManage;
import entities.WareHouse;

/**
 *
 * @author pcm23
 */
public interface IReport {
    List<Product> showProductExpired(List<Product> listProduct);
    // void showProductExpired(List<Product> listProduct);

    List<Product> showProductSelling(List<Product> listProduct);
    // void showProductSelling(List<Product> listProduct);

    List<Product> showProductRunningOut(List<Product> listProduct);

    // Product showReceiptProduct(String code, ProductManage productManage,
    // WareHouseManage wareHouseManage);
    // void showReceiptProduct(String code);
    WareHouse showReceiptProduct(String code);

}

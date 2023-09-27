package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import entities.Product;
import entities.WareHouse;
import manage.ProductManage;
import manage.WareHouseManage;
import report.Report;
import utils.FileManage;
import utils.Validation;
import utils.Status;

public class Service implements IService {
    public static int endCode;
    ProductManage productManage = new ProductManage();
    Validation valid = new Validation();
    List<Product> listProduct = productManage.listProduct;
    WareHouseManage wareHouseManage = new WareHouseManage();
    Report report = new Report();
    FileManage fileManage = new FileManage();

    List<Product> list = fileManage.loadData("product.dat");

    public Product inputProduct(Status status) {

        String code = valid.checkProductCodeExist("Enter code product: ", listProduct, status);
        if (status.equals(Status.ADD)) {
            for (Product p : list) {
                if (code.equals(p.getCode())) {
                    System.out.println("This code already have in the file");
                    return null;
                }
            }
        }
        String name = valid.checkString("Enter product name: ", status);
        int quantity = valid.checkInt("Enter quantity product: ", 0, Integer.MAX_VALUE, status);
        String type = valid.checkType("Enter type product (daily or long): ", status);

        String mDate = valid.checkBeforeDate("Enter manufacturing date: ", status);
        String eDate = valid.checkAfterDate("Enter expiration date: ", mDate, status);
        Product product = new Product(code, name, quantity, type, mDate, eDate);

        return product;
    }

    @Override
    public void addProduct() {
        // submenu
        while (true) {
            Product newProduct = inputProduct(Status.ADD);
            // check whether code product exists in the file or not
            if (newProduct == null) {
                return;
            }
            // add the new production to collection
            productManage.addProduct(newProduct);
            // the application asks to continue creating new product or go back to main menu
            if (valid.checkYesOrNo("Do you want to continue to add product in the collection (Y/N): ")) {
                System.out.println();
                continue;
            }

            break;
        }

    }

    @Override
    public String updateProduct() {
        // user requires enter the productCode
        System.out.println("Press enter to keep the old information");
        String code = valid.checkCode("Enter code to update: ", Status.UPDATE);
        Product product = new Product() {
        };

        // get Product by code
        Product olProduct = productManage.getProductByCode(code);
        if (olProduct == null) {
            System.out.println("Product does not exists in the system");
        } else {
            Product newProduct = inputProduct(Status.UPDATE);

            product = productManage.updateProduct(olProduct, newProduct);
        }
        System.out.println("-----------------------");

        System.out.println("New information of old product: ");
        if (olProduct == null) {
            System.out.println("none");
        } else {
            listProduct.remove(olProduct);
            listProduct.add(product);
            System.out.format("%-10s%-17s%-15s%-15s%-25s%-25s\n", "ID", "NAME", "QUANTITY", "TYPE",
                    "MANUFACTURING DATE", "EXPIRATION DATE");
            product.print();

        }
        return code;
    }

    @Override
    public void deleteProduct() {
        String code = valid.checkCode("Enter code to delete: ", Status.DELETE);
        Boolean flag = false;
        // Show the result of the delete: success or fail. (fail (when product already
        // import/export) -> flag = false)

        Product product = productManage.getProductByCode(code);

        if (product == null) {
            System.out.println("Product does not exists in the system");
        }

        /*
         * only remove the product from the store's list when the import
         * export information for this product has not been generated.
         */
        else if (wareHouseManage.getProductIWareHouse(product) != null) {
            System.out.println("Product exists in receipt");
            flag = false;
        } else if (product != null) {
            flag = true;
            productManage.deleteProduct(product);

        }

        if (flag)
            System.out.println("Delete Success");
        else
            System.out.println("Delete Fail");
    }

    @Override
    // option == true -> file
    // option == false -> collection
    public void showAllProduct() {
        boolean option = valid.checkFileOrCollection("Do you want to show by file or collection (F/C): ");

        productManage.showAllProduct(option);
    }

    public WareHouse inputReceipt(boolean option) {
        String code = "";
        if (option) {
            code += "I";
        } else {
            code += "E";
        }

        // check code of Import and Export in the file and then increase 1 to the new
        // code
        endCode = fileManage.loadDataWare("warehouse.dat", code) + 1;

        if (endCode > 9999999) {
            System.out.println("Warehouse information is full!");
        }

        int numberZero = 7 - (endCode + "").length();
        String med = "";
        for (int i = 1; i <= numberZero; i++) {
            med += "0";
        }
        code += (med + endCode);
        // input time from the system
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        // list Product
        List<Product> listP = new ArrayList<>();
        WareHouse importReceipt = null;
        while (true) {
            String productCode = valid.checkCode("Enter code product: ", Status.ADD);
            for (Product p : list) {
                if (code.equals(p.getCode())) {
                    System.out.println("This code already have in the file");
                    return null;
                }
            }
            Product product = productManage.getProductByCode(productCode);

            if (product == null) {
                System.out.println("Fail. Product does not exist in the system");
                break;
            } else if (wareHouseManage.listExport.contains(product)) {
                System.out.println("Fail. Product already exists in receipt");
                break;
            } else {
                listP.add(product);
                if (valid.checkYesOrNo("Do you want to continue add product in receipt (Y/N): ")) {
                    continue;
                } else {
                    importReceipt = new WareHouse(code, time, listP);

                    System.out.println("Success");
                    break;
                }
            }
        }

        return importReceipt;
    }

    // true -> Import, false -> Export
    @Override
    public void createReceipt() {
        boolean type = valid.checkImportOrExport("Do you want to create receipt import or export (I/E): ");
        if (type == true) {
            WareHouse importReceipt = inputReceipt(type); // function inputReceipt
            if (importReceipt == null) {
                System.out.println("fail");
            } else {
                wareHouseManage.createImportReceipt(importReceipt);

            }

        } else {
            WareHouse exportReceipt = inputReceipt(type);
            if (exportReceipt == null) {
                System.out.println("fail");
            } else {
                wareHouseManage.createExportReceipt(exportReceipt);

            }
        }
    }

    @Override
    public void showProductExpired() {
        List<Product> list = report.showProductExpired(listProduct);
        if (list != null) {
            productManage.show(list);
            System.out.println();
        } else {
            System.out.println("Don't have any products expired");
        }
    }

    @Override
    public void showProductSelling() {
        List<Product> list = report.showProductSelling(listProduct);

        if (list != null) {
            productManage.show(list);
            System.out.println();

        } else {
            System.out.println("Don't have any product to sell");

        }
    }

    @Override
    public void showProductRunningOut() {
        List<Product> list = report.showProductRunningOut(listProduct);
        if (list != null) {
            productManage.show(list);
            System.out.println();

        } else {
            System.out.println("Don't have any product running out of stock");

        }
    }

    @Override
    public void showReceiptProduct() {
        String code = valid.checkCode("Enter code product: ", Status.NONE);
        wareHouseManage.show(code);

    }

    public void saveToFile(String fileName, String type) {
        List<WareHouse> listWareHouses = new ArrayList<>(wareHouseManage.listImport);
        listWareHouses.addAll(wareHouseManage.listExport);

        FileManage fileManage = new FileManage();
        // System.out.println(listProduct);
        fileManage.saveToFile(fileName, type, listProduct, listWareHouses);
    }
}

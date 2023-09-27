package manage;

import java.util.ArrayList;
import java.util.List;

import entities.Product;
import entities.WareHouse;

public class WareHouseManage implements IWareHouseManage {

    public List<WareHouse> listImport;
    public List<WareHouse> listExport;

    public WareHouseManage() {
        listImport = new ArrayList<>();
        listExport = new ArrayList<>();

    }

    @Override
    public void createImportReceipt(WareHouse wareHouse) {
        // code import IXXXXXX
        listImport.add(wareHouse);
        System.out.println("\n==========WareHouse List===========");
        System.out.format("%-15s%-20s%-15s\n", "CODE", "INPUT TIME", "PRODUCT CODE");
        wareHouse.print();
    }

    @Override
    public void createExportReceipt(WareHouse wareHouse) {
        listExport.add(wareHouse);
        System.out.println("\n==========WareHouse List===========");
        System.out.format("%-15s%-20s%-15s\n", "CODE", "INPUT TIME", "PRODUCT CODE");
        wareHouse.print();

    }

    public void show(String code) {

        // List<WareHouse> listWareHouses = new ArrayList<>(listAll);
        List<WareHouse> listWareHouses = new ArrayList<>(listImport);

        listWareHouses.addAll(listExport);
        for (WareHouse wareHouse : listWareHouses) {
            if (wareHouse.getProductCode(code)) {
                System.out.println("\n==========WareHouse List===========");
                System.out.format("%-15s%-20s%-15s\n", "CODE", "INPUT TIME", "PRODUCT CODE");
                wareHouse.print();
                return;
            }
        }
        System.out.println("Don't have that product in receipt");
    }

    public Product getProductIWareHouse(Product product) {
        // WareHouse wareHouse = new WareHouse();
        // List<WareHouse> listReceipts = new ArrayList<>(listImport);

        List<WareHouse> listReceipts = new ArrayList<>(listImport);

        listReceipts.addAll(listExport);

        for (WareHouse receipt : listReceipts) {
            List<Product> list = receipt.getListProduct();
            for (Product p : list) {
                if (p.equals(product)) {
                    return product;
                }
            }
        }

        return null;
    }

}

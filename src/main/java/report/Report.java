package report;

import entities.Product;
import entities.WareHouse;
import manage.WareHouseManage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Report implements IReport {
    WareHouseManage wareHouseManage = new WareHouseManage();

    @Override
    public List<Product> showProductExpired(List<Product> listProduct) {
        List<Product> listExProducts = new ArrayList<>();
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean check = false;
        try {
            for (Product product : listProduct) {
                Date endTime = sdf.parse(product.getExpirationDate());

                if (endTime.compareTo(currentDate) < 0) {
                    listExProducts.add(product);
                    check = true;
                }
            }
        } catch (ParseException e) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, e);
        }
        if (check == false)
            return null;
        return listExProducts;
    }

    @Override
    public List<Product> showProductSelling(List<Product> listProduct) {
        List<Product> listSelling = new ArrayList<>();

        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        boolean check = false;

        try {
            // Date nowTime = sdf.parse(time);
            Date currentDate = new Date();

            for (Product product : listProduct) {

                Date endTime = sdf.parse(product.getExpirationDate());

                if (endTime.compareTo(currentDate) >= 0 && product.getQuantity() > 0) {
                    listSelling.add(product);
                    check = true;
                }
            }
        } catch (ParseException e) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, e);
        }
        if (check == false) return null;
        return listSelling;
    }

    @Override
    public List<Product> showProductRunningOut(List<Product> listProduct) {
        List<Product> listSelling = new ArrayList<>();
        boolean check = false;
        for (Product product : listProduct) {

            if (product.getQuantity() < 3) {
                listSelling.add(product);
                check = true;
            }
        }

        Comparator<Product> c = new Comparator<Product>() {
            public int compare(Product o1, Product o2) {
                if (o1.getQuantity() > o2.getQuantity()) {
                    return 1;
                } else if (o1.getQuantity() < o2.getQuantity()) {
                    return -1;
                }

                return 0;
            }
        };
        if (check == false) return null;
        Collections.sort(listSelling, c);
        return listSelling;
    }

    @Override
    public WareHouse showReceiptProduct(String code) {
        List<WareHouse> list = new ArrayList<>(wareHouseManage.listExport);
        list.addAll(wareHouseManage.listImport);

        for (WareHouse wareHouse : list) {
            if (wareHouse.getProductCode(code)) {
                System.out.println(wareHouse);
                return wareHouse;
            }
        }

        return null;
    }
}

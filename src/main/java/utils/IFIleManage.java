package utils;

import java.util.List;

import entities.Product;
import entities.WareHouse;

/**
 *
 * @author pcm23
 */
public interface IFIleManage {
     void saveToFile(String fileName, String type, List<Product> lProduc, List<WareHouse> wareHouses);
     List<Product> loadData(String fileName);
}

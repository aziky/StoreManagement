package utils;

import entities.Product;
import entities.WareHouse;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManage implements IFIleManage {

    @Override
    public void saveToFile(String fileName, String type, List<Product> lProduct, List<WareHouse> lWareHouses) {

        if (type.equals("product")) {
            try {
                File file = new File(fileName);

                if (!file.exists()) {
                    file.createNewFile();
                }

                StringBuilder fileContent;
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                fileContent = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
                bufferedReader.close();
                
           
                for (Product product : lProduct) {
                    fileContent.append(product).append("\n");
                }

                if (fileContent.length() == 0) {
                    System.out.println("Nothing to save!");
                    return;
                }
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(fileContent.toString());
                bufferedWriter.flush();
                bufferedWriter.close();
                System.out.println("Success");

            } catch (IOException e) {
                System.err.println("An error");
            }
        }

        else if (type.equalsIgnoreCase("warehouse")) {
            try {
                File file = new File(fileName);

                if (!file.exists()) {
                    file.createNewFile();
                }

                StringBuilder fileContent;
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                fileContent = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    fileContent.append(line).append("\n");

                }
                bufferedReader.close();

                for (WareHouse wareHouse : lWareHouses) {
                    fileContent.append(wareHouse).append("\n");

                }
                if (fileContent.length() == 0) {
                    System.out.println("Nothing to save!");
                    return;
                }

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(fileContent.toString());
                bufferedWriter.close();
                System.out.println("Success");

            } catch (IOException e) {
                System.err.println("An error");
            }
        }

    }

    // @Override
    public List<Product> loadData(String fileName) {
        List<Product> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {

                    StringTokenizer stk = new StringTokenizer(line, ",");
                    String code = stk.nextToken().trim();
                    String name = stk.nextToken().trim();
                    int quantity = Integer.parseInt(stk.nextToken().trim());
                    String type = stk.nextToken().trim();
                    String mDate = stk.nextToken().trim();
                    String eDate = stk.nextToken().trim();
                    Product newProduct = new Product(code, name, quantity, type, mDate, eDate);

                    result.add(newProduct);
                }
            }
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(FileManage.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    public int loadDataWare(String fileName, String type) {
        int countImport = 0;
        int countExport = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty() && line.startsWith(type)) {
                    countImport++;
                }else  if (!line.isEmpty() && line.startsWith(type)){
                    countExport++;
                }
            }
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(FileManage.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(type.equalsIgnoreCase("I")){
            return countImport;
        }else{
            return countExport;
        }

    }
}

// replace showByFile to loadData from file manager
// add loadData to add, update, delete, showByFile function
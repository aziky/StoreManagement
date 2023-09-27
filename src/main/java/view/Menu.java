package view;

import utils.Status;
import utils.Validation;
import service.Service;


public class Menu {

    // create submenu
    // private static final Scanner sc = new Scanner(System.in);
    public static final String line = "-----------------------";

    Validation validation = new Validation();
    Service service = new Service();

    public Menu() {
    }

    public void execute() {
        int input;
        System.out.println(line);

        do {
            System.out.println("1. Manage products");
            System.out.println("2. Manage warehouse");
            System.out.println("3. Report");
            System.out.println("4. Store data to files");
            System.out.println("5. Exit");
            input = validation.checkInt("Enter your choice: ", 1, 5, Status.NONE);

            switch (input) {
                case 1:
                    menuProduct();
                    break;
                case 2:
                    menuWarehouse();
                    break;
                case 3:
                    menuReport();
                    break;
                case 4:
                    menuStoreData();
                    break;
                case 5:
                    System.out.println("bye bye!");
                    break;

            }

        } while (input != 5);
    }

    public void menuProduct() {
        int userChoice;
        do {
            System.out.println(line);
            System.out.println("1. Add product");
            System.out.println("2. Update product information");
            System.out.println("3. Delete product");
            System.out.println("4. Show all products");
            System.out.println("5. Back to the main menu");
            userChoice = validation.checkInt("Enter your choice: ", 1, 5, Status.NONE);
            System.out.println(line);

            switch (userChoice) {
                case 1:
                    service.addProduct();
                    break;
                case 2:
                    service.updateProduct();
                    break;
                case 3:
                    service.deleteProduct();
                    break;
                case 4:
                    service.showAllProduct();
                    break;
  
            }
        } while (userChoice != 5);
    }

    public void menuWarehouse() {
        int userChoice;

        do {
            System.out.println(line);
            System.out.println("1. Create an import/export receipt");
            System.out.println("2. Back to the main menu");
            userChoice = validation.checkInt("Enter your choice: ", 1, 5, Status.NONE);

            System.out.println(line);

            switch (userChoice) {
                case 1:
                    service.createReceipt();
                    break;


            }
        } while (userChoice != 2);
    }

    public void menuReport() {
        int userChoice;

        do {
            System.out.println(line);
            System.out.println("1. Product that have expired");
            System.out.println("2. Products that the store is selling");
            System.out.println("3. Products that are running out of stock");
            System.out.println("4. Import/Export receipt of a product");
            System.out.println("5. Back to the main menu");
            userChoice = validation.checkInt("Enter your choice: ", 1, 5, Status.NONE);

            System.out.println(line);

            switch (userChoice) {
                case 1:
                    service.showProductExpired();
                    break;
                case 2:
                    service.showProductSelling();
                    break;
                case 3:
                    service.showProductRunningOut();
                    break;
                case 4:
                    service.showReceiptProduct();
                    break;


            }
        } while (userChoice != 5);
    }

    public void menuStoreData() {
        int userChoice;

        do {
            System.out.println(line);
            System.out.println("1. Store product to file");
            System.out.println("2. Store warehouse to file");
            System.out.println("3. Back to the main menu");
            userChoice = validation.checkInt("Enter your choice: ", 1, 5, Status.NONE);

            System.out.println(line);

            switch (userChoice) {
                case 1:
                    service.saveToFile("product.dat", "product");
                    break;
                case 2:
                    service.saveToFile("warehouse.dat", "warehouse");
                    break;


            }
        } while (userChoice != 3);
    }
}

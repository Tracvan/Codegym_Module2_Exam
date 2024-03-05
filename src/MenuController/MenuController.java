package MenuController;

import Model.Product;
import Service.ProductService;
import readWriteFile.ReadWriteFile;
import view.Menu;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MenuController {
    public static void load() throws InterruptedException, IOException {
        List<Product> products = ReadWriteFile.readFile();
        boolean isValid =true;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Hãy nhập lựa chọn ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    ProductService.viewList();
                    Menu.showMenu();
                    isValid = false;
                    break;
                case "2":
                    ProductService.addProduct();
                    Menu.showMenu();
                    isValid = false;
                    break;
                case "3":
                    ProductService.updateProduct();
                    Menu.showMenu();
                    isValid = false;
                    break;
                case "4":
                    ProductService.deleteProduct();
                    Menu.showMenu();
                    isValid = false;
                    break;
                case "5":
                    ProductService.sortProductList();
                    Menu.showMenu();
                    isValid = false;
                    break;
                case "6":
                    ProductService.showMostExpensive();
                    Menu.showMenu();
                    isValid = false;
                    break;
                case "7":
                    ReadWriteFile.readFile();
                    Menu.showMenu();
                    isValid = false;
                    break;
                case "8":
                    ReadWriteFile.writeFile(products);
                    Menu.showMenu();
                    isValid = false;
                    break;
            }
            if(isValid){
                System.err.println("Lựa chọn không hợp lệ vui lòng nhập lại");
            }
        } while (isValid);
    }
}



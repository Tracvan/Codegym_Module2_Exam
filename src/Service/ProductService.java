package Service;

import Model.Product;
import readWriteFile.ReadWriteFile;
import view.Menu;

import java.util.List;
import java.util.Scanner;

public class ProductService {
    public static void showMostExpensive(){
        List<Product> products = ReadWriteFile.readFile();
        Product maxCost = products.get(0);
        for(Product p : products){
            if(p.getPrice() > maxCost.getPrice()){
                maxCost = p;
            }
        }
        System.out.println("Sản phẩm có giá cao nhất");
        System.out.println("Mã sản phẩm : " + maxCost.getId() + " Tên sản phẩm: " + maxCost.getName() + " Số lượng sản phẩm: " + maxCost.getQuantity() + " Giá sản phẩm: " + maxCost.getPrice() + " Mô tả: " + maxCost.getType());
    }

    public static void viewList() {
        List<Product> products = ReadWriteFile.readFile();
        System.out.println("Tên sản phẩm\t\t\tGiá\tSố Lượng\tKiểu");
        for (int i = 0; i<products.size(); i++) {
                Product product = products.get(i);
                String formattedName = String.format("%-20s", product.getName());
                String formattedPrice = String.format("%.2f", product.getPrice());
                String formattedQuantity = String.format("%-10d", product.getQuantity());
                String formattedType = String.format("%-10s", product.getType());
                System.out.println(formattedName + "\t" + formattedPrice + "\t" + formattedQuantity + "\t" + formattedType);
            }
        }

    public static void addProduct() {
        int id;
        boolean isExist = false;
        Scanner scanner = new Scanner(System.in);
        List<Product> products = ReadWriteFile.readFile();
        do {
            System.out.println("Hãy nhập mã sản phẩm");
            id = scanner.nextInt();
            scanner.nextLine();
            for (Product p : products) {
                if (p.getId() == id) {
                    isExist = true;
                    System.err.println("Id đã bị trùng vui lòng nhập id khác");
                }
            }
        }
        while (isExist);
        System.out.println("Hãy nhập tên sản phẩm");
        String name = scanner.nextLine();
        System.out.println("Hãy nhập giá sản phẩm");
        double price = scanner.nextDouble();
        System.out.println("Hãy nhập số lượng");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Hãy nhập mô tả");
        String type = scanner.nextLine();
        Product newProduct = new Product(id, name, price, quantity, type);
        products.add(newProduct);
        ReadWriteFile.writeFile(products);
        System.out.println("Đã thêm sản phẩm thành công");
    }

    public static void updateProduct() {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = ReadWriteFile.readFile();
        boolean isMatch = false;
        do {
            System.out.println("Hãy nhập mã sản phẩm cần sửa");
            int id = scanner.nextInt();
            scanner.nextLine();
            for (Product p : products) {
                if (p.getId() == id) {
                    isMatch = true;
                    System.out.println("Nhập mã sản phẩm mới");
                    int newId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Nhập tên sản phẩm");
                    String newName = scanner.nextLine();
                    System.out.println("Nhập giá");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Nhập số lượng");
                    int newQuantity = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Nhập mô tả");
                    String newType = scanner.nextLine();
                    p.setId(newId);
                    p.setName(newName);
                    p.setPrice(newPrice);
                    p.setQuantity(newQuantity);
                    p.setType(newType);
                    ReadWriteFile.writeFile(products);
                    System.out.println("Đã sửa thành công");
                    break;
                }
            }
            if (!isMatch) {
                System.err.println("Không tìm thấy sản phẩm, vui lòng nhập lại");
            }
        } while (!isMatch);
    }

    public static void deleteProduct() throws InterruptedException {
        boolean isFound = false;
        Scanner scanner = new Scanner(System.in);
        List<Product> products = ReadWriteFile.readFile();
        do {
            System.out.println("Nhập mã sản phẩm");
            int id = scanner.nextInt();
            scanner.nextLine();
            for (Product p : products) {
                if (p.getId() == id) {
                    isFound = true;
                    System.out.println("Nhập Y để xác nhận");
                    String confirm = scanner.nextLine();
                    if(confirm.equals("Y")){
                    products.remove(p);
                    ReadWriteFile.writeFile(products);
                    System.out.println("Đã xóa thành công");}
                    else {
                        Menu.showMenu();
                        break;
                    }
                    break;
                }
            }
            if (!isFound) {
                System.out.println("Không tìm thấy sản phẩm, vui lòng nhập lại");
            }
        } while (!isFound);
    }

    public static void sortPriceLowToHigh() throws InterruptedException {
        List<Product> products = ReadWriteFile.readFile();
        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < products.size(); j++) {
                if (products.get(i).getPrice() < products.get(j).getPrice()) {
                    Product temp = products.get(i);
                    products.set(i, products.get(j));
                    products.set(j, temp);
                }
            }
        }
        System.out.println("__________________________________________________");
        System.out.println("Number  Product's Name    Quantity  Price   Type");
        System.out.println("---------------------------------------------------");
        for (Product p : products) {
            String formattedString = String.format("%-7d %-16s %-9d %.2f   %s", p.getId(), p.getName(), p.getQuantity(), p.getPrice(), p.getType());
            System.out.println(formattedString);
        }
        System.out.println("__________________________________________________");
        Thread.sleep(1000);
    }

    public static void sortPriceHighToLow() throws InterruptedException {
        List<Product> products = ReadWriteFile.readFile();
        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < products.size(); j++) {
                if (products.get(i).getPrice() > products.get(j).getPrice()) {
                    Product temp = products.get(i);
                    products.set(i, products.get(j));
                    products.set(j, temp);
                }
            }
        }
        System.out.println("________________________________________________");
        System.out.println("Number  Product's Name    Quantity  Price   Type");
        System.out.println("---------------------------------------------------");
        for (Product p : products) {
            String formattedString = String.format("%-7d %-16s %-9d %.2f   %s", p.getId(), p.getName(), p.getQuantity(), p.getPrice(), p.getType());
            System.out.println(formattedString);
        }
        System.out.println("________________________________________________");
        Thread.sleep(1000);

    }

    public static void sortProductList() throws InterruptedException {
        boolean isMatch = false;
        Scanner scanner = new Scanner(System.in);
        String choice;
        System.out.println("1.Sắp xếp theo giá tăng dần");
        System.out.println("2.Sắp xếp theo giá giảm dần");
        System.out.println("3.Trở về menu");
        do {
            System.out.println("Hãy nhập lựa chọn");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    isMatch = true;
                    sortPriceLowToHigh();
                    break;
                case "2":
                    isMatch = true;
                    sortPriceHighToLow();
                    break;
                case "3":
                    Menu.showMenu();
                    break;
            }
            if(!isMatch){
                System.err.println("Lựa chọn không hợp lệ");
            }
        } while (!isMatch);
    }

}

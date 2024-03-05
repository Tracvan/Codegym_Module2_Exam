package readWriteFile;
import Model.Product;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteFile {
    private static File file = new File("productList.csv");

    public static List<Product> readFile() {
        List<Product> productList = new ArrayList<>();
        String line;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] productString = line.split(",");
                int id = Integer.parseInt(productString[0]);
                String name = productString[1];
                double price = Double.parseDouble(productString[3]);
                int quanity = Integer.parseInt(productString[2]);
                String type = productString[4];

                Product product = new Product(id, name, price, quanity, type);
                productList.add(product);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public static void writeFile(List<Product> productList) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            StringBuilder productString = new StringBuilder();
            for (Product product : productList) {
                productString.append(product.getId()).append(",").
                        append(product.getName()).append(",").
                        append(product.getQuantity()).append(",").
                        append(product.getPrice()).append(",").
                        append(product.getType()).append("\n");
            }
            bufferedWriter.write(productString.toString());
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}


package util;

import dao.ProdusDAO;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import entity.Produs;
import shop.Shop;

public class Utility {

    private static Utility utility;

    public static Utility getInstance() {

        if (utility == null) {
            utility = new Utility();
        }

        return utility;

    }

    public void refreshTable(Node node) {
        TableView table = (TableView) node;

        if (table != null) {
            table.getItems().clear();
            Shop shop = Shop.getInstance();

            for (Produs produs : ProdusDAO.getInstance().getAllProducts()) {
                table.getItems().add(produs);
            }
        }
//        Shop shop = Shop.getInstance();
//        shop.saveObjects();
    }

    public Node getTable(GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (gridPane.getRowIndex(node) == 8 && gridPane.getColumnIndex(node) == 0) {
                return node;
            }
        }
        return null;
    }




//    public void writeToFile(List<Produs> produsList) {
//        Shop shop = Shop.getInstance();
//        produsList = shop.getProduse();
//        if (produsList != null) {
//            try {
//                FileOutputStream fileOutputStream = new FileOutputStream("fileOut.txt");
//                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//                for (Produs produs : produsList) {
//                    objectOutputStream.writeObject(produs);
//                }
//                fileOutputStream.close();
//                objectOutputStream.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public List<Produs> readFromFile() {
//        Shop shop = Shop.getInstance();
//
//        try {
//            FileInputStream fileInputStream = new FileInputStream("fileOut.txt");
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//            while (fileInputStream.available() > 0) {
//                Produs produs = (Produs) objectInputStream.readObject();
//
//                shop.addProd(produs);
//            }
//        } catch (ClassNotFoundException | IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }
//
//    public <List> Produs readFromCSVFile(String fileName) throws FileNotFoundException {
//
//        Scanner scanner = new Scanner(new File(fileName));
//
//
//        while (scanner.hasNextLine()) {
//            String fullRow = scanner.nextLine();
//
//            try {
//                countCommasInLine(fullRow);
//            } catch (Exception var6) {
//                PrintStream var10000 = System.err;
//                char var10001 = fullRow.charAt(0);
//                var10000.println("Exceptie la linia " + var10001 + ": " + fullRow);
//                continue;
//            }
//
//            Scanner rowScanner = new Scanner(fullRow);
//            rowScanner.useDelimiter(",");
//            String id = rowScanner.next();
//            String price = rowScanner.next();
//            String numberOfSims = rowScanner.next();
//            String hasTouch = rowScanner.next();
//            String voltaj = rowScanner.next();
//            String name = rowScanner.next();
////            Laptop laptop = new Laptop(Long.parseLong(id), Double.parseDouble(price), name, Boolean.parseBoolean(hasTouch), Integer.parseInt(numberOfSims), Integer.parseInt(voltaj));
////            Phone phone = new Phone(Double.parseDouble(price), name, Integer.parseInt(numberOfSims), Boolean.parseBoolean(hasTouch), Integer.parseInt(voltaj));
//
////            Shop shop = Shop.getInstance();
////            shop.addProd(phone);
//        }
//        return null;
//    }
//
//    private static void countCommasInLine(String fullRow) throws Exception {
//        int counter = 0;
//
//        for (int i = 0; i < fullRow.length(); ++i) {
//            if (fullRow.charAt(i) == ',') {
//                ++counter;
//            }
//        }
//
//        if (counter != 5) {
//            throw new Exception("Number of values is incorrect");
//        }
//    }
}

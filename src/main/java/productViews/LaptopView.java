package productViews;

import dao.LaptopDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import entity.Laptop;
import customWidgets.NumericTextField;
import util.Utility;

public class LaptopView extends Stage {

    GridPane mainGridPane;
    public LaptopView(GridPane mainGridPane) {

        this.mainGridPane = mainGridPane;
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(800, 600);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.TOP_CENTER);


        generateAddLaptopView(gridPane);
        Utility.getInstance().refreshTable(Utility.getInstance().getTable(this.mainGridPane));
        Scene scene = new Scene(gridPane);
        this.setTitle("Add product");
        this.setScene(scene);
    }



    private void generateAddLaptopView(GridPane gridPane) {
        Label titleLabel = new Label("Add laptop");
        gridPane.add(titleLabel, 2, 0);

        Label idLabel = new Label("ID");
        NumericTextField idTextField = new NumericTextField();


        Label priceLabel = new Label("Price");
        NumericTextField priceTextField = new NumericTextField();

        Label productNameLabel = new Label("Product name");
        TextField productNameTextField = new TextField();

        CheckBox hasGraphic = new CheckBox("Yes");
        CheckBox doesntHaveGraphic = new CheckBox("No");
        Label graphicCard = new Label("Has graphic card");

        Label numbOfPortsLabel = new Label("Number of ports");
        NumericTextField numbOfPortsTextField = new NumericTextField();

        Label voltajLabel = new Label("Voltage");
        NumericTextField voltajTextField = new NumericTextField();

        HBox hbox = new HBox();
        hbox.getChildren().addAll(hasGraphic, doesntHaveGraphic);

        Button addProductButton = new Button("Add product");

        addProductButton.setOnAction(actionEvent -> {
            String price = priceTextField.getText();
            boolean hasGraphicCard = hasGraphic.isSelected();
            String numerOfPorts = numbOfPortsTextField.getText();
            String voltaj = voltajTextField.getText();
            String productName = productNameTextField.getText();

            LaptopDAO laptopDAO = new LaptopDAO();
            laptopDAO.insertIntoTable(new Laptop(Double.parseDouble(price), productName, hasGraphicCard, Integer.parseInt(numerOfPorts), Integer.parseInt(voltaj)));

//            Laptop laptop = new Laptop(Long.parseLong(id), Double.parseDouble(price), productName, hasGraphicCard, Integer.parseInt(numerOfPorts), Integer.parseInt(voltaj));

//            Shop shop = Shop.getInstance();
//            shop.addProd(laptop);
            Utility.getInstance().refreshTable(Utility.getInstance().getTable(this.mainGridPane));
            this.close();

        });

        setLaptopLabelsAndTextFieldsOnGrid(gridPane, idLabel, idTextField, priceLabel, priceTextField, productNameLabel, productNameTextField, graphicCard, numbOfPortsLabel, numbOfPortsTextField, voltajLabel, voltajTextField, hbox, addProductButton);
    }

    private void setLaptopLabelsAndTextFieldsOnGrid(GridPane gridPane, Label idLabel, NumericTextField idTextField, Label priceLabel, NumericTextField priceTextField, Label productNameLabel, TextField productNameTextField, Label graphicCard, Label numbOfPortsLabel, NumericTextField numbOfPortsTextField, Label voltajLabel, NumericTextField voltajTextField, HBox hbox, Button addProductButton) {
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(actionEvent -> clearTextFields(gridPane));

        Button closeButton = new Button("Close");
        closeButton.setOnAction(actionEvent -> this.close());

        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(addProductButton, clearButton, closeButton);

//        gridPane.add(idLabel, 1, 1);
//        gridPane.add(idTextField, 2, 1);

        gridPane.add(priceLabel, 1, 2);
        gridPane.add(priceTextField, 2, 2);

        gridPane.add(productNameLabel, 1, 3);
        gridPane.add(productNameTextField, 2, 3);

        gridPane.add(graphicCard, 1, 4);
        gridPane.add(hbox, 2, 4);

        gridPane.add(numbOfPortsLabel, 1, 5);
        gridPane.add(numbOfPortsTextField, 2, 5);

        gridPane.add(voltajLabel, 1, 6);
        gridPane.add(voltajTextField, 2, 6);
        gridPane.add(new Label(""), 0, 7);

        gridPane.add(hbox2, 2, 8);
    }


    private void clearTextFields(GridPane gridPane) {

        for (Node node : gridPane.getChildren()) {
            if (node instanceof TextField) {
                TextField text = (TextField) node;
                text.setText("");
            }
        }
    }

}

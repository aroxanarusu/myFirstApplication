package productViews;

import dao.PhoneDAO;
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
import entity.Phone;
import customWidgets.NumericTextField;
import util.Utility;

public class PhoneView extends Stage {


    GridPane mainGridPane;
    public PhoneView(GridPane mainGridPane) {

        this.mainGridPane = mainGridPane;

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(800, 600);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.TOP_CENTER);

        generatePhoneLabelsAndFields(gridPane);
        Utility.getInstance().refreshTable(Utility.getInstance().getTable(this.mainGridPane));
        Scene scene = new Scene(gridPane);
        this.setTitle("Add phone");
        this.setScene(scene);
    }

    private void generatePhoneLabelsAndFields(GridPane gridPane) {
        Label titleLabel = new Label("Add phone");
        gridPane.add(titleLabel, 2, 0);

        Label idLabel = new Label("ID");
        NumericTextField idTextField = new NumericTextField();

        Label priceLabel = new Label("Price");
        NumericTextField priceTextField = new NumericTextField();

        Label productNameLabel = new Label("Product name");
        TextField productNameTextField = new TextField();

        CheckBox hasTouch = new CheckBox("Yes");
        CheckBox doesntHaveTouch = new CheckBox("No");
        Label touchScreen = new Label("TouchScreen: ");

        Label numbOfSims = new Label("Number of sims");
        NumericTextField numbOfSimsTextField = new NumericTextField();

        Label voltajLabel = new Label("Voltage");
        NumericTextField voltajTextField = new NumericTextField();

        HBox hbox = new HBox();
        hbox.getChildren().addAll(hasTouch, doesntHaveTouch);

        Button addProductButton = new Button("Add product");

        addProductButton.setOnAction(actionEvent -> {

            String price = priceTextField.getText();
            boolean phoneTouchScreen = hasTouch.isSelected();
            String numberOfSims = numbOfSimsTextField.getText();
            String voltaj = voltajTextField.getText();
            String productName = productNameTextField.getText();

            PhoneDAO phoneDAO = new PhoneDAO();
            phoneDAO.insertIntoTable(new Phone(Double.parseDouble(price), productName, Integer.parseInt(numberOfSims), phoneTouchScreen, Integer.parseInt(voltaj)));

//            Phone phone = new Phone(Long.parseLong(id), Double.parseDouble(price), productName, Integer.parseInt(numberOfSims), phoneTouchScreen, Integer.parseInt(voltaj));

//            Shop shop = Shop.getInstance();
//            shop.addProd(phone);
            Utility.getInstance().refreshTable(Utility.getInstance().getTable(this.mainGridPane));
            this.close();
        });

        setPhoneLabelsAndTextFieldsOnGrid(gridPane, idLabel, idTextField, priceLabel, priceTextField, productNameLabel, productNameTextField, touchScreen, numbOfSims, numbOfSimsTextField, voltajLabel, voltajTextField, hbox, addProductButton);
    }

    private void clearTextFields(GridPane gridPane) {

        for (Node node : gridPane.getChildren()) {
            if (node instanceof TextField) {
                TextField text = (TextField) node;
                text.setText("");
            }
        }
    }


    private void setPhoneLabelsAndTextFieldsOnGrid(GridPane gridPane,  Label idLabel, NumericTextField idTextField, Label priceLabel, NumericTextField priceTextField, Label productNameLabel, TextField productNameTextField, Label touchScreen, Label numbOfSims, NumericTextField numbOfSimsTextField, Label voltajLabel, NumericTextField voltajTextField, HBox hbox, Button addProductButton) {
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(actionEvent -> clearTextFields(gridPane));

        Button closeButton = new Button("Close");
        closeButton.setOnAction(actionEvent -> this.close());

        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(addProductButton, clearButton, closeButton);



        gridPane.add(priceLabel, 1, 2);
        gridPane.add(priceTextField, 2, 2);

        gridPane.add(productNameLabel, 1, 3);
        gridPane.add(productNameTextField, 2, 3);

        gridPane.add(touchScreen, 1, 4);
        gridPane.add(hbox, 2, 4);

        gridPane.add(numbOfSims, 1, 5);
        gridPane.add(numbOfSimsTextField, 2, 5);

        gridPane.add(voltajLabel, 1, 6);
        gridPane.add(voltajTextField, 2, 6);
        gridPane.add(new Label(""), 0, 7);


        gridPane.add(hbox2, 2, 8);
    }

}

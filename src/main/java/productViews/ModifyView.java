package productViews;

import customWidgets.NumericTextField;
import dao.LaptopDAO;
import dao.PhoneDAO;
import entity.Laptop;
import entity.Phone;
import entity.Produs;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.Utility;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ModifyView extends Stage {


    GridPane mainGridPane;

    public ModifyView(GridPane mainGridPane, Map<String, String> values, Produs produs) {
        this.mainGridPane = mainGridPane;
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(800, 600);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.TOP_CENTER);
        generateModifyView(gridPane, values, produs);
        Utility.getInstance().refreshTable(Utility.getInstance().getTable(this.mainGridPane));
        Scene scene = new Scene(gridPane);
        this.setTitle("Modify");
        this.setScene(scene);

    }

    public void generateModifyView(GridPane gridPane, Map<String, String> values, Produs produs) {
        Set<String> valuesOfMap = new HashSet<>();
        values.keySet().forEach(val -> {
            if (values.get(val) != null) {
                valuesOfMap.add(val);
            }
        });
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        valuesOfMap
                );
        final ComboBox comboBox = new ComboBox(options);

        final Control[] test = {null, null};

        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                System.out.println(t1);
                if (produs instanceof Laptop) {
                    if (t1.equals("Price") || t1.equals("Voltage") || t1.equals("Number Of Ports")) {
                        test[0] = new NumericTextField();
                    } else if (t1.equals("Has Graphic Card")) {
                        ObservableList<String> options =
                                FXCollections.observableArrayList(
                                        "true",
                                        "false"
                                );
                        test[0] = new ComboBox(options);
                    } else {
                        test[0] = new TextField();
                    }
                    gridPane.add(test[0], 0, 4);
                } else if (produs instanceof Phone) {
                    if (t1.equals("Price") || t1.equals("Voltage") || t1.equals("Number Of Sims")) {
                        test[0] = new NumericTextField();
                    } else if (t1.equals("Has Touch")) {
                        ObservableList<String> options =
                                FXCollections.observableArrayList(
                                        "True",
                                        "False"
                                );
                        test[0] = new ComboBox(options);
                    } else {
                        test[0] = new TextField();

                    }
                    gridPane.add(test[0], 0, 4);
                }
            }
        });

        gridPane.add(comboBox, 0, 0);

        Label oldValue = new Label("Old Value:");
        TextField oldTextValue = new TextField();


        Label newValue = new Label("New Value:");
//        TextField newTextValue = new TextField();


//        gridPane.add(oldValue, 0,1);
//        gridPane.add(oldTextValue, 0,2);

        gridPane.add(newValue, 0, 3);

        Button submitButton = new Button("Submit");

        submitButton.setOnAction(actionEvent -> {
            String value = "";
            if (test[0] instanceof NumericTextField || test[0] instanceof TextField) {
                TextField t = (TextField) test[0];
                value = t.getText();
            } else {
                if (test[0] != null) {
                    ComboBox t = (ComboBox) test[0];
                    value = t.getValue().toString();
                }
            }

            submitAction(gridPane, value, values, comboBox, produs);
            Utility.getInstance().refreshTable(Utility.getInstance().getTable(this.mainGridPane));
            this.close();
        });
        gridPane.add(submitButton, 0, 5);
    }

    private void submitAction(GridPane gridPane, String newValue, Map<String, String> values, ComboBox comboBox, Produs produs) {
        String parameter = values.get(comboBox.getValue());
        if (produs instanceof Laptop) {
            Laptop laptop = (Laptop) produs;
            System.out.println(parameter);
            if (parameter.equals("Price")) {
                laptop.setPrice(Double.parseDouble(newValue));
                System.out.println(produs);
            } else if (parameter.equals("Name")) {
                laptop.setName(newValue);
            } else if (parameter.equals("Voltage")) {
                laptop.setVoltage(Integer.parseInt(newValue));
            } else if (parameter.equals("HasGraphicCard")) {
                laptop.setHasGraphicCard(Boolean.parseBoolean(newValue));
            } else if (parameter.equals("NumberOfPorts")) {
                laptop.setNumbOfPorts(Integer.parseInt(newValue));
            }
            System.out.println(laptop);
            LaptopDAO laptopDAO = new LaptopDAO();
            laptopDAO.updateLaptop(laptop.getPrice(), laptop.getName(), laptop.getVoltage(), laptop.isHasGraphicCard(), laptop.getNumbOfPorts(), laptop.getId());
        } else if (produs instanceof Phone) {
            Phone phone = (Phone) produs;
            if (parameter.equals("Price")) {
                phone.setPrice(Double.parseDouble(newValue));
                System.out.println(produs);
            } else if (parameter.equals("Name")) {
                phone.setName(newValue);
            } else if (parameter.equals("Voltage")) {
                phone.setVoltage(Integer.parseInt(newValue));
            } else if (parameter.equals("NumberOfSims")) {
                phone.setNumbOfSims(Integer.parseInt(newValue));
            } else if (parameter.equals("HasTouch")) {
                phone.setHasTouch(Boolean.parseBoolean(newValue));
            }
            PhoneDAO phoneDAO = new PhoneDAO();
            phoneDAO.updatePhone(phone.getPrice(), phone.getName(), phone.getVoltage(), phone.isHasTouch(), phone.getNumbOfSims(), phone.getId());
            //double price, String name, int voltage, boolean hasTouch, int numbOfSims, long id
            System.out.println(phone);
        }
    }

//    public void setLabelsANdTextFieldsOnGrid(GridPane gridPane, )
}

package customWidgets;

import dao.ProdusDAO;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import entity.Produs;

import java.util.ArrayList;
import java.util.List;

public class ActiveSearchBar extends TextField {


    public ActiveSearchBar(TableView table) {
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Produs> produse = getProductsBySearch(newValue);

            if (table != null) {
                table.getItems().clear();
                for (Produs produs : produse) {
                    table.getItems().add(produs);
                }
            }

        });
    }


    public List<Produs> getProductsBySearch(String toSearch) {
        ProdusDAO prod = ProdusDAO.getInstance();
        List<Produs> products = ProdusDAO.getInstance().getAllProducts();
        List<Produs> listToBeReturned = new ArrayList<>();
        toSearch = toSearch.toLowerCase();

        for (Produs produs : products) {
            if (String.valueOf(produs.getId()).toLowerCase().contains(toSearch) || produs.getName().toLowerCase().contains(toSearch)) {
                listToBeReturned.add(produs);
            }
        }

        return listToBeReturned;
    }
}

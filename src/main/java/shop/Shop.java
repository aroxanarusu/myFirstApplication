package shop;


import dao.ProdusDAO;
import entity.Produs;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private static Shop shop;

    public static Shop getInstance() {
        if (shop == null) {
            shop = new Shop();
        }

        return shop;
    }

    private List<Produs> produse;

    public Shop() {
        this.produse = new ArrayList<>();
    }

    public void addProd(Produs produs) {
        if (!isIdInShop(produs.getId())) {
            produse.add(produs);
        }else System.out.println("Try again");
    }

    public void printProducts() {
        for (Produs produs : produse) {
            System.out.println(produs);
        }
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void loadObjects() {

        this.produse = ProdusDAO.getInstance().getAllProducts();
    }

//    public void saveObjects() {
//
//        Utility.getInstance().writeToFile(this.produse);
//    }

    public void removeProduct(long id) {

        if (produse.indexOf(getProductById(id)) != -1) {
            produse.remove(produse.indexOf(getProductById(id)));
        }
    }

    public boolean isIdInShop(long id) {

        List<Produs> produs = getProduse();
        for (int i = 0; i < produs.size(); i++) {
            if (produs.get(i) != null)
                if (produs.get(i).getId() == id) {
                    return true;
                }
        }
        return false;
    }

    public Produs getProductById(long id) {

        for (Produs produs : produse) {
            if (id == produs.getId()) {
                return produs;
            }
        }
        return null;
    }
}

package ma.enset.JavaFxJdbc.presentation.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.JavaFxJdbc.dao.CategoryDaoImpl;
import ma.enset.JavaFxJdbc.dao.ProductDaoImpl;
import ma.enset.JavaFxJdbc.dao.entities.Category;
import ma.enset.JavaFxJdbc.dao.entities.Product;
import ma.enset.JavaFxJdbc.service.CatalogueService;
import ma.enset.JavaFxJdbc.service.CatalogueServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    @FXML private TextField textNom;
    @FXML private TextField textRef;
    @FXML private TextField textPrix;
    @FXML private ComboBox<Category> ComboCategerie ;
    @FXML private TextField textSearch;
    @FXML private TableView<Product> tabelViewProd;
    @FXML private TableColumn<Long,Product> columnId;
    @FXML private TableColumn<String,Product> columnNom;
    @FXML private TableColumn<String,Product> columnRef;
    @FXML private TableColumn<Float,Product> columnPrix;
    @FXML private TableColumn<Category,Product> columnCat;
    private CatalogueService catalogueService;

    ObservableList<Product> data= FXCollections.observableArrayList();
    private Product selectedProduct;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabelViewProd.setItems(data);
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
        columnPrix.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnCat.setCellValueFactory(new PropertyValueFactory<>("category"));
        catalogueService=new CatalogueServiceImpl(new ProductDaoImpl(),new CategoryDaoImpl());
        ComboCategerie.getItems().addAll(catalogueService.getAllCategories());
        loadData();
        textSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                data.clear();
                data.addAll(catalogueService.searchProductByQuery(newValue));
            }
        });
    }
    private void loadData(){
        data.clear();
       data.addAll(catalogueService.getAllProduct());
    }
    public void addProduct(){
        Product p =new Product();
        p.setName(textNom.getText());
        p.setReference(textRef.getText());
        p.setprice(Float.parseFloat(textPrix.getText()));
        p.setCategory(ComboCategerie.getSelectionModel().getSelectedItem());
        catalogueService.addProduct(p);
        loadData();
    }
    public void deleteProduct(){
        Product p=tabelViewProd.getSelectionModel().getSelectedItem();
        catalogueService.deleteProduct(p);
        loadData();
    }
    public void updateProduct(){
        selectedProduct.setName(textNom.getText());
        selectedProduct.setprice(Float.parseFloat(textPrix.getText()));
        selectedProduct.setReference(textRef.getText());
        selectedProduct.setCategory(ComboCategerie.getSelectionModel().getSelectedItem());
        catalogueService.updateProduct(selectedProduct);
        loadData();
    }

    public void editProduct(){
        selectedProduct=tabelViewProd.getSelectionModel().getSelectedItem();
        textNom.setText(selectedProduct.getName());
        textPrix.setText(String.valueOf(selectedProduct.getprice()));
        textRef.setText(selectedProduct.getReference());


    }


}

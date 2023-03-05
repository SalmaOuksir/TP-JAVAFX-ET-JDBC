package ma.enset.JavaFxJdbc.service;

import ma.enset.JavaFxJdbc.dao.CategoryDaoImpl;
import ma.enset.JavaFxJdbc.dao.ProductDaoImpl;
import ma.enset.JavaFxJdbc.dao.entities.Category;
import ma.enset.JavaFxJdbc.dao.entities.Product;

import java.util.List;

public class Application {
    public static void main(String[] args){
        CatalogueService catalService = new CatalogueServiceImpl(new ProductDaoImpl(),new CategoryDaoImpl());
        Category c1 =new Category();
        c1.setName("Gaming");
        c1.setId(1);
        Product p1=new Product();
        p1.setName("HP");
        p1.setReference("REF0001");
        p1.setprice(12000);
        p1.setCategory(c1);
        //catalService.addProduct(p1);
        List<Product> products=catalService.getAllProduct();
        for (Product p:products){
            System.out.println("ID:"+p.getId() +" Name:"+p.getName()+" Price: "+p.getprice());

        }


    }
}

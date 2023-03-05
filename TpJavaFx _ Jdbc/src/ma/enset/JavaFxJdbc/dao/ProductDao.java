package ma.enset.JavaFxJdbc.dao;

import ma.enset.JavaFxJdbc.dao.entities.Product;

import java.util.List;

public interface ProductDao extends Dao<Product> {
     List<Product> findbyQuery(String query);
}

package ma.enset.JavaFxJdbc.service;

import ma.enset.JavaFxJdbc.dao.CategoryDao;
import ma.enset.JavaFxJdbc.dao.ProductDao;
import ma.enset.JavaFxJdbc.dao.entities.Category;
import ma.enset.JavaFxJdbc.dao.entities.Product;

import java.util.List;

public class CatalogueServiceImpl implements CatalogueService{
    private ProductDao productDao;
    private CategoryDao categoryDao;

    public CatalogueServiceImpl(ProductDao productDao, CategoryDao categoryDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public void addProduct(Product p) {
        productDao.save(p);

    }
    @Override
    public List<Product> getAllProduct() {
        return productDao.findAll();
    }

    @Override
    public List<Product> searchProductByQuery(String query) {
        return productDao.findbyQuery(query);
    }


    @Override
    public void updateProduct(Product p) {
       productDao.update(p);
    }

    @Override
    public void deleteProduct(Product p) {
        productDao.delete(p);
    }


    @Override
    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    @Override
    public void addCategory(Category c) {
        categoryDao.save(c);
    }

    @Override
    public void updateCategory(Category c) {
        categoryDao.update(c);
    }

    @Override
    public void deleteCategory(Category c) {
        categoryDao.delete(c);
    }


}

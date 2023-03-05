package ma.enset.JavaFxJdbc.dao;

import ma.enset.JavaFxJdbc.dao.entities.Category;
import ma.enset.JavaFxJdbc.dao.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public Product find(long id) {
        Connection connection=ConnexionDBSingleton.getConnection();
        Product p=new Product();
        try {
            PreparedStatement pstm=connection.prepareStatement("select * from PRODUCTS where ID=? ");
            pstm.setLong(1,id);
              ResultSet rs=pstm.executeQuery();
              if(rs.next()){
                  p.setId(rs.getLong("ID"));
                  p.setName(rs.getString("NAME"));
                  p.setReference(rs.getString("REFERENCE"));
                  p.setprice(rs.getFloat("PRICE"));
              }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    @Override
    public List<Product> findAll() {
        //mapping object relationnel
        List<Product> products =new ArrayList<>();
        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pstm=connection.prepareStatement("select * from PRODUCTS");
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Product p=new Product();
                p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));;
                p.setReference(rs.getString("REFERENCE"));
                p.setprice(rs.getFloat("PRICE"));
                PreparedStatement pstm1=connection.prepareStatement("select * from CATEGORY where ID=?");
                pstm1.setLong(1,rs.getLong("ID_CAT"));
                ResultSet rs1 = pstm1.executeQuery();
                Category c=new Category();
                if(rs1.next())
                    c.setName(rs1.getString("NAME"));
                p.setCategory(c);
                products.add(p);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return products;
    }

    @Override
    public void save(Product o) {
        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pstm=connection.prepareStatement(" insert into PRODUCTS(NAME,REFERENCE,PRICE,ID_CAT) " +
                    "values(?,?,?,?)");
            pstm.setString(1,o.getName());
            pstm.setString(2,o.getReference());
            pstm.setFloat(3,o.getprice());
            pstm.setLong(4,o.getCategory().getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
        }


    }

    @Override
    public void delete(Product o) {
        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pstm=connection.prepareStatement("delete from PRODUCTS where ID=?");
            pstm.setLong(1,o.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Product o) {
        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pstm=connection.prepareStatement("update PRODUCTS set NAME=?,REFERENCE=?,PRICE=?, ID_CAT=? where ID=?");
            pstm.setString(1,o.getName());
            pstm.setString(2,o.getReference());
            pstm.setFloat(3,o.getprice());
            pstm.setLong(4,o.getCategory().getId());
            pstm.setLong(5,o.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Product> findbyQuery(String query) {
        List<Product> products =new ArrayList<>();
        Connection connection=ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement pstm=connection.prepareStatement("select * from PRODUCTS where NAME like ? or REFERENCE like ? or PRICE like ? ");
            pstm.setString(1,"%"+query+"%");
            pstm.setString(2,"%"+query+"%");
            pstm.setString(3,"%"+query+"%");
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Product p=new Product();
                p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));;
                p.setReference(rs.getString("REFERENCE"));
                p.setprice(rs.getFloat("PRICE"));
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}

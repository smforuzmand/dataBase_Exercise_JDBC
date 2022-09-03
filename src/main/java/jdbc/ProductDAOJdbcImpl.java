package jdbc;

import DAOs.ProductDAO;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//when we are going to close a class and methods that are connected to a database we should implement disconnecting method which are defined in the AbstractOpertations *How?
//through extending that abstract method while this class is going to implement the abstract methods of another interface
public class ProductDAOJdbcImpl extends AbstractOperations implements ProductDAO {


    @Override
    public Product create(Product product) {
        int rowAffected = Integer.MIN_VALUE;


//Insert when id==0 and Update when id>0

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product(id, name, price) VALUES (?,?,?)");
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());
            rowAffected = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rowAffected == 1) {
            return product;
        } else {
            return null;
        }
    }

    @Override
    public Optional<Product> findById(Integer id) {
        Optional<Product> foundMatch = Optional.empty();


        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE id = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                foundMatch = Optional.of(mapToProduct(resultSet));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return foundMatch;
    }


    @Override
    public Product save(Product product) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product(id, name, price) VALUES (?,?,?)");
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());

            preparedStatement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return product;
    }


    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(mapToProduct(resultSet));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    @Override
    public Optional<Product> findById(String s) {
        return Optional.empty();
    }

    @Override
    public Integer delete(String s) {
        return null;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE product.name LIKE ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(mapToProduct(resultSet));


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return products;
    }

    @Override
    public List<Product> findBYPriceBetween(Double low, Double high) {
        List<Product> productList = new ArrayList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE price BETWEEN ?AND ?");
            preparedStatement.setDouble(1, low);
            preparedStatement.setDouble(2, high);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productList.add(mapToProduct(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }


        return productList;
    }

    @Override
    public void delete(int id) {

        try {
            Connection connection= getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();


        } catch (SQLException e) {
           e.printStackTrace();
        }

    }
}

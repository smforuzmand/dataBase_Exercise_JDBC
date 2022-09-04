package jdbc;

import DAOs.ShoppingCartDAO;
import model.ShoppingCart;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCartDAOJdbcImpl extends AbstractOperations implements ShoppingCartDAO {


    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {

        int rowAffected = Integer.MIN_VALUE;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shopping_cart (id, last_update, order_status, delivery_address, customer_reference) VALUES (?,?,?,?,?)");

            preparedStatement.setInt(1, shoppingCart.getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(shoppingCart.getLastUpdate()));
            preparedStatement.setString(3, shoppingCart.getOrderStatus());
            preparedStatement.setString(4, shoppingCart.getDeliveryAddress());
            preparedStatement.setString(5, shoppingCart.getCustomerReference());


            preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (rowAffected == 1) {
            return shoppingCart;
        } else {
            return null;
        }

    }

    @Override
    public ShoppingCart save(ShoppingCart cart) {

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shopping_cart (id, last_update, order_status, delivery_address, customer_reference) " +
                    "VALUES (?,?,?,?,?)");

            preparedStatement.setInt(1, cart.getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(cart.getLastUpdate()));
            preparedStatement.setString(3, cart.getOrderStatus());
            preparedStatement.setString(4, cart.getDeliveryAddress());
            preparedStatement.setString(5, cart.getCustomerReference());


            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cart;
    }

    @Override
    public List<ShoppingCart> findByOrderStatus(ShoppingCart status) {

        List<ShoppingCart> carts = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM shopping_cart WHERE order_status LIKE ?");
            preparedStatement.setString(1, status.getOrderStatus());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                carts.add(mapToShoppingCart(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return carts;
    }

    @Override
    public List<ShoppingCart> findByReference(ShoppingCart customer) {

        final String FINDBYREFRENCE = "SELECT * FROM shopping_cart WHERE customer_reference LIKE ?";
        List<ShoppingCart> cartList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FINDBYREFRENCE);
            preparedStatement.setString(1, customer.getCustomerReference());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cartList.add(mapToShoppingCart(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return cartList;
    }

    @Override
    public List<ShoppingCart> findAll() {
        List<ShoppingCart> cartList = new ArrayList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM shopping_cart");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String order_status = resultSet.getString("order_status");
                int id = resultSet.getInt("id");
                LocalDateTime last_update = resultSet.getTimestamp("Last_update").toLocalDateTime();
                String delivery_address = resultSet.getString("delivery_address");
                String customer_reference = resultSet.getString("customer_reference");
                ShoppingCart e = new ShoppingCart(
                        id,
                        last_update,
                        order_status,
                        delivery_address,
                        customer_reference);
                cartList.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartList;
    }

    @Override
    public Optional<ShoppingCart> findById(String s) {
        return Optional.empty();
    }

    @Override
    public Integer delete(String s) {
        return null;
    }

    @Override
    public Optional<ShoppingCart> findById(int id) {

        Optional<ShoppingCart> shoppingCartFound = Optional.empty();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM shopping_cart WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                shoppingCartFound = Optional.of(mapToShoppingCart(resultSet));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return shoppingCartFound;
    }

    @Override
    public void delete(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM shopping_cart WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
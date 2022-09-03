package jdbc;

import DAOs.ShoppingCardItemDAO;
import com.mysql.cj.MysqlConnection;
import model.ShoppingCartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static jdbc.AbstractOperations.*;

public class ShoppingCartItemJdbcImpl implements ShoppingCardItemDAO {
    @Override
    public ShoppingCartItem save(ShoppingCartItem cartItem) {

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shopping_cart_item " +
                    "VALUES (?,?,?,?,?)");

            preparedStatement.setInt(1, cartItem.getId());
            preparedStatement.setInt(2, cartItem.getAmount());
            preparedStatement.setDouble(3, cartItem.getTotalPrice());
            preparedStatement.setInt(4, cartItem.getItem().getId());
            preparedStatement.setInt(5, cartItem.getCard().getId());
            preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return cartItem;
    }

    @Override
    public Optional<ShoppingCartItem> findById(int id) {

        Optional<ShoppingCartItem> cartItems = Optional.empty();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM shopping_cart_item WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cartItems = Optional.of(mapToShoppingCartItem(resultSet));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return cartItems;
    }

    @Override
    public List<ShoppingCartItem> findAll() {

        List<ShoppingCartItem> cartItems = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM shopping_cart_item");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cartItems.add(mapToShoppingCartItem(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return cartItems;

    }

    @Override
    public List<ShoppingCartItem> findByCartId(int cartId) {
        List<ShoppingCartItem> cartItemsFound = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM shopping_cart_item WHERE id = ?");
            preparedStatement.setInt(1, cartId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cartItemsFound.add(mapToShoppingCartItem(resultSet));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return cartItemsFound;
    }

    @Override
    public List<ShoppingCartItem> findByProductId(int productId) {
        List<ShoppingCartItem> cartItemsFound = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM shopping_cart_item WHERE product_id = ?");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cartItemsFound.add(mapToShoppingCartItem(resultSet));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartItemsFound;
    }
}

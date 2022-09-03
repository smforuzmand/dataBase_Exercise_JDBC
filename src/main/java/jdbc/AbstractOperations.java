package jdbc;

import com.mysql.cj.protocol.Resultset;
import model.Product;
import model.ShoppingCart;
import model.ShoppingCartItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractOperations {

    static ProductDAOJdbcImpl productDAOJdbc = new ProductDAOJdbcImpl();
    static ShoppingCartDAOJdbcImpl cartDAOJdbc = new ShoppingCartDAOJdbcImpl();

    public void closeAll(AutoCloseable... closeables) {
        if (closeables != null) {

            for (AutoCloseable autoCloseable : closeables) {
                try {
                    closeables.clone();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }


    }

    protected static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DataBaseCredential.getINSTANCE().getURL(),
                DataBaseCredential.getINSTANCE().getUSERNAME(),
                DataBaseCredential.getINSTANCE().getPASSWORD());


    }

    protected static Product mapToProduct (ResultSet resultSet) throws SQLException {

        return new Product(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getDouble("price"));
    }


    protected static ShoppingCart mapToShoppingCart(ResultSet resultSet) throws SQLException {
        return new ShoppingCart(resultSet.getInt("id"),
                resultSet.getTimestamp("last_update").toLocalDateTime(),
                resultSet.getString("order_status"),
                resultSet.getString("delivery_address"),
                resultSet.getString("customer_reference"));
    }


    protected static ShoppingCartItem mapToShoppingCartItem(ResultSet resultSet ) throws SQLException {

        return new ShoppingCartItem(resultSet.getInt("id"),
                resultSet.getInt("amount"),
                resultSet.getDouble("total_price"),
                productDAOJdbc.findById(resultSet.getInt("product_id")),
                cartDAOJdbc.findById(resultSet.getInt("shopping_cart_id")));



    }

}

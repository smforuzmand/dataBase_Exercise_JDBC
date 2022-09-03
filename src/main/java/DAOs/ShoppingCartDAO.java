package DAOs;

import model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartDAO extends GenericCrud <ShoppingCart , String>{

    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart save(ShoppingCart cart);

    List<ShoppingCart> findByOrderStatus(ShoppingCart status);

    List<ShoppingCart> findByReference(ShoppingCart customer);


    Optional<ShoppingCart> findById(int id);

    void delete(int id);
}

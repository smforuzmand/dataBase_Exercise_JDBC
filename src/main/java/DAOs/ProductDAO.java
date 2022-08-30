package DAOs;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {

    public Product save(Product product);

    Optional<Product> findById(int id);

    List<Product> findAll();

    List<Product> findByName(String name);

    List<Product> findBYPriceBetween(Double low, Double high);

    void delete(int id);



}

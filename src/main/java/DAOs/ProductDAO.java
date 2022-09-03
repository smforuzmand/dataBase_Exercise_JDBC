package DAOs;

import model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO extends GenericCrud<Product ,String> {

    Optional<Product> findById(Integer id);

    public Product save(Product product);

    List<Product> findAll();

    List<Product> findByName(String name);

    List<Product> findBYPriceBetween(Double low, Double high);

    void delete(int id);



}

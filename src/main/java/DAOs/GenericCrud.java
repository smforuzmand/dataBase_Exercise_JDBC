package DAOs;

import java.util.List;
import java.util.Optional;

public interface GenericCrud<T , ID> {
//This T refers to what data type like product, shopping cartItem....and the ID refers to what type of Id , int, string?
    T create(T t);

    List<T> findAll();

    Optional<T> findById(ID id);

   Integer delete(ID id);





}

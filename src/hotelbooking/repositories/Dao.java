package hotelbooking.repositories;
import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {
    Optional<T> findById(ID id);
    List<T> findAll();
    ID save(T entity);
    boolean deleteById(ID id);
}
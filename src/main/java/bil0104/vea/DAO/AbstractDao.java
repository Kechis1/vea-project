package bil0104.vea.DAO;


import java.util.List;

public interface AbstractDao<T> {
    T insert(T entity);
    List<T> getAll();
    T findById(long id);
    T update(T entity);
    void delete(long id);
}

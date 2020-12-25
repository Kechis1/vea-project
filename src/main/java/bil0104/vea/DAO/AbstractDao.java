package bil0104.vea.DAO;


import java.util.List;

public interface AbstractDao<T> {
    void insert(T entity);
    List<T> getAll();
    T findById(long id);
    void update(T entity);
    void delete(long id);
}

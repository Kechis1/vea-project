package bil0104.vea.DAO;


public interface PersonDao<T> extends AbstractDao<T> {
    T findByLogin(String login);
}

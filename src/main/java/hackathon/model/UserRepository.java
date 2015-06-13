package hackathon.model;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
public class UserRepository {

    @Inject
    private EntityManager entityManager;

    public User get(final Long id) {
        return entityManager.find(User.class, id);
    }

    public List<User> getAll() {
        final TypedQuery<User> query = entityManager.createNamedQuery(User.FIND_ALL, User.class);
        return query.getResultList();
    }

}

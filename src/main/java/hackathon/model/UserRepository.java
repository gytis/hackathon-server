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

    public List<User> getAll() {
        final TypedQuery<User> query = entityManager.createNamedQuery(User.FIND_ALL, User.class);
        return query.getResultList();
    }

}

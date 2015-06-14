package hackathon.model;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
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

    public User getByFacebookId(final String facebookId) {
        final TypedQuery<User> query = entityManager.createNamedQuery(User.FIND_BY_FB_ID, User.class).setParameter("facebookId", facebookId);

        return query.getSingleResult();
    }

    public List<User> getAll() {
        final TypedQuery<User> query = entityManager.createNamedQuery(User.FIND_ALL, User.class);
        return query.getResultList();
    }

    @Transactional
    public void save(final User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

}

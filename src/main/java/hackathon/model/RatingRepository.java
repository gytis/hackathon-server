package hackathon.model;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
public class RatingRepository {

    @Inject
    private EntityManager entityManager;

    public Rating getByOwners(final long userId, final long eventId) {
        final TypedQuery<Rating> query = entityManager.createNamedQuery(Rating.FIND_BY_OWNERS, Rating.class)
                .setParameter("userId", userId)
                .setParameter("eventId", eventId);

        return query.getSingleResult();
    }

    public List<Rating> getAll() {
        final TypedQuery<Rating> query = entityManager.createNamedQuery(Rating.FIND_ALL, Rating.class);
        return query.getResultList();
    }

    @Transactional
    public void save(final Rating rating) {
        if (rating.getId() == null) {
            entityManager.persist(rating);
        } else {
            entityManager.merge(rating);
        }
    }

}

package hackathon.model;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
public class RatingRepository {

    @Inject
    private EntityManager entityManager;

    public List<Rating> getAll() {
        final TypedQuery<Rating> query = entityManager.createNamedQuery(Rating.FIND_ALL, Rating.class);
        return query.getResultList();
    }

}

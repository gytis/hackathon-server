package hackathon.model;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
public class TicketRepository {

    @Inject
    private EntityManager entityManager;

    public List<Ticket> getAll() {
        final TypedQuery<Ticket> query = entityManager.createNamedQuery(Ticket.FIND_ALL, Ticket.class);
        return query.getResultList();
    }

}

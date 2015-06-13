package hackathon.rest;

import hackathon.model.Ticket;
import hackathon.model.TicketRepository;
import hackathon.model.User;
import hackathon.model.UserRepository;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

/**
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
@Path("/tickets")
public class TicketResource {

    @Inject
    private TicketRepository ticketRepository;

    @GET
    public String getAll() {
        final List<Ticket> tickets = ticketRepository.getAll();

        return ticketsToJson(tickets).toString();
    }

    @POST
    public void post() {

    }

    private JsonArray ticketsToJson(final List<Ticket> tickets) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (final Ticket ticket : tickets) {
            arrayBuilder.add(ticketToJson(ticket));
        }

        return arrayBuilder.build();
    }

    private JsonObject ticketToJson(final Ticket ticket) {
        return Json.createObjectBuilder()
                .add("id", ticket.getId())
                .add("user_id", ticket.getUserId())
                .add("event_id", ticket.getEventId())
                .add("date", ticket.getDate().toString())
                .add("created_at", ticket.getCreatedAt().toString())
                .build();
    }
}

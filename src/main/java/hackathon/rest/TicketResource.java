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
import java.io.StringReader;
import java.util.List;

/**
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
@Path("/tickets")
public class TicketResource {

    @Inject
    private TicketRepository ticketRepository;

    @Inject
    private UserRepository userRepository;

    @GET
    public String getAll() {
        final List<Ticket> tickets = ticketRepository.getAll();

        return ticketsToJson(tickets).toString();
    }

    @POST
    public String post(String body) {
        final Ticket ticket = jsonToTicket(body);

        ticketRepository.save(ticket);

        return ticketToJson(ticket).toString();
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
                .add("user_id", ticket.getUser().getId())
                .add("event_id", ticket.getEventId())
                .add("date", ticket.getDate().toString())
                .add("created_at", ticket.getCreatedAt().toString())
                .build();
    }

    private Ticket jsonToTicket(final String json) {
        final JsonObject jsonObject = Json.createReader(new StringReader(json)).readObject();
        final Ticket ticket = new Ticket();
        final Long userId = Long.valueOf(jsonObject.getInt("user_id"));

        ticket.setUser(userRepository.get(userId));
        ticket.setDate(Long.valueOf(jsonObject.getString("date")));
        ticket.setEventId(Long.valueOf(jsonObject.getInt("event_id")));

        return ticket;
    }
}

package hackathon.rest;

import hackathon.model.Rating;
import hackathon.model.RatingRepository;

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
@Path("/ratings")
public class RatingResource {

    @Inject
    private RatingRepository ratingRepository;

    @GET
    public String getAll() {
        final List<Rating> ratings = ratingRepository.getAll();

        return ratingsToJson(ratings).toString();
    }

    @POST
    public void post() {

    }

    private JsonArray ratingsToJson(final List<Rating> ratings) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (final Rating rating : ratings) {
            arrayBuilder.add(ratingToJson(rating));
        }

        return arrayBuilder.build();
    }

    private JsonObject ratingToJson(final Rating rating) {
        return Json.createObjectBuilder()
                .add("id", rating.getId())
                .add("user_id", rating.getUserId())
                .add("event_id", rating.getEventId())
                .add("value", rating.getValue())
                .add("created_at", rating.getCreatedAt().toString())
                .build();
    }
}

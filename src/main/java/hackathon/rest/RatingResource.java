package hackathon.rest;

import hackathon.model.Rating;
import hackathon.model.RatingRepository;
import hackathon.model.UserRepository;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.StringReader;
import java.util.List;

/**
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
@Path("/ratings")
public class RatingResource {

    @Inject
    private RatingRepository ratingRepository;

    @Inject
    private UserRepository userRepository;

    @GET
    public String getAll() {
        final List<Rating> ratings = ratingRepository.getAll();

        return ratingsToJson(ratings).toString();
    }

    @POST
    public String post(String body) {
        Rating rating = jsonToRating(body);

        try {
            rating = ratingRepository.getByOwners(rating.getUser().getId(), rating.getEventId());
        } catch (NoResultException e) {
            ratingRepository.save(rating);
        }

        return ratingToJson(rating).toString();
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
                .add("user_id", rating.getUser().getId())
                .add("event_id", rating.getEventId())
                .add("value", rating.getValue())
                .add("created_at", rating.getCreatedAt().toString())
                .build();
    }

    private Rating jsonToRating(final String json) {
        final JsonObject jsonObject = Json.createReader(new StringReader(json)).readObject();
        final Rating rating = new Rating();
        final Long userId = Long.valueOf(jsonObject.getInt("user_id"));

        rating.setUser(userRepository.get(userId));
        rating.setValue(jsonObject.getInt("value"));
        rating.setEventId(Long.valueOf(jsonObject.getInt("event_id")));

        return rating;
    }
}

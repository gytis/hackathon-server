package hackathon.rest;

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
@Path("/users")
public class UserResource {

    @Inject
    private UserRepository userRepository;

    @GET
    public String getAll() {
        final List<User> users = userRepository.getAll();

        return usersToJson(users).toString();
    }

    @POST
    public void post() {

    }

    private JsonArray usersToJson(final List<User> users) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (final User user : users) {
            arrayBuilder.add(userToJson(user));
        }

        return arrayBuilder.build();
    }

    private JsonObject userToJson(final User user) {
        return Json.createObjectBuilder()
                .add("id", user.getId())
                .add("name", user.getName())
                .add("facebook_id", user.getFacebookId())
                .add("photo", user.getPhoto())
                .add("registered", user.getRegistered())
                .build();
    }
}

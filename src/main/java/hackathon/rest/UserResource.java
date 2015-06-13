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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
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

    @GET
    @Path("/{userId}/friends")
    public Response getFriends(@PathParam("userId") Long userId) {
        final User user = userRepository.get(userId);

        if (user == null) {
            return Response.status(404).build();
        }

        final List<User> friends = user.getFriends();

        return Response.status(200).entity(usersToJson(friends).toString()).build();
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

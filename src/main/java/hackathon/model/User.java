package hackathon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
@Entity
@NamedQueries({
        @NamedQuery(name = User.FIND_ALL, query = "SELECT u FROM User u"),
        @NamedQuery(name = User.FIND_BY_FB_ID, query = "SELECT u FROM User u WHERE u.facebookId = :facebookId")
})
public class User implements Serializable {

    public static final String FIND_ALL = "User.findAll";

    public static final String FIND_BY_FB_ID = "User.findByFbId";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    @Column(name = "facebook_id")
    private String facebookId;

    private String photo;

    private boolean registered;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Frienship", joinColumns = {@JoinColumn(name = "user1", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user2", referencedColumnName = "id")})
    private List<User> friends;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean getRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}

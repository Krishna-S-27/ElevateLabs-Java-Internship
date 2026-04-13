package Task3;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserManager {
    private final Map<Integer, User> users = new LinkedHashMap<>();

    public boolean addUser(User user) {
        if (users.containsKey(user.getId())) {
            return false;
        }
        users.put(user.getId(), user);
        return true;
    }

    public User getUserById(int id) {
        return users.get(id);
    }

    public boolean removeUser(int id) {
        return users.remove(id) != null;
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }
}


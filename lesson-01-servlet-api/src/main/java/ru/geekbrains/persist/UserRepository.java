package ru.geekbrains.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository {
    private Map<Long, User> usersMap = new ConcurrentHashMap<>();

    private AtomicLong identity = new AtomicLong(0);

    public List<User> findAll() {
        return new ArrayList<>(usersMap.values());
    }

    public User findById(long id) {
        return usersMap.get(id);
    }

    public void insert(User user) {
        long id = identity.incrementAndGet();
        user.setId(id);
        usersMap.put(id, user);
    }

    public void update(User user) {
        usersMap.put(user.getId(), user);
    }

    public void delete(long id) {
        usersMap.remove(id);
    }


}

package ru.perminov.user;

import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.perminov.client.BaseClient;
import ru.perminov.dto.user.UserDtoIn;

@Service
public class UserClient extends BaseClient {
    private static final String API_PREFIX = "api/users";

    @Autowired
    public UserClient(@Value("${userApp-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build());
    }

    public ResponseEntity<Object> getAllUsers() {
        return get("");
    }

    public ResponseEntity<Object> getById(long userId) {
        return get("/" + userId);
    }

    public ResponseEntity<Object> create(UserDtoIn user) {
        return post("", user);
    }

    public ResponseEntity<Object> update(UserDtoIn userDto, Long id) {
        return patch("/" + id, userDto);
    }

    public ResponseEntity<Object> deleteById(long userId) {
        return delete("/" + userId);
    }

    public ResponseEntity<Object> getUserRole(@Positive Long id) {
        return get("/" + id + "/roles");
    }

    public ResponseEntity<Object> addRoleByUser(@Positive Long userId, @Positive Long roleId) {
        return postRole("/" + userId + "/" + roleId );
    }

    public ResponseEntity<Object> deleteRoleByUser(@Positive Long userId, @Positive Long roleId) {
        return delete("/" + userId + "/" + roleId);
    }
}

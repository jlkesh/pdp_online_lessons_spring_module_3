package uz.pdp.springadvanced.user;

import lombok.NonNull;

public interface UserService {
    User create(@NonNull User user);

    User get(@NonNull Integer id);

    void delete(Integer id);

    User update(Integer id, UserUpdateDTO dto);
}

package uz.pdp.springadvanced.service;

import uz.pdp.springadvanced.dto.UserCreateDto;
import uz.pdp.springadvanced.entity.Users;

public interface UsersService {
    Users create(UserCreateDto dto);
}

package uz.pdp.springadvanced.mapper;

import org.mapstruct.Mapper;
import uz.pdp.springadvanced.dto.UserCreateDto;
import uz.pdp.springadvanced.entity.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users fromCreateDto(UserCreateDto dto);
}

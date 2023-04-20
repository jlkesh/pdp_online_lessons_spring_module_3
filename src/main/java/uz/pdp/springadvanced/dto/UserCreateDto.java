package uz.pdp.springadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link uz.pdp.springadvanced.entity.Users} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto implements Serializable {
    private String email;
    private String username;
    private String password;
}
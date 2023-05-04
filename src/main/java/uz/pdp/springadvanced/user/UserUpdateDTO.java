package uz.pdp.springadvanced.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    private Integer id;
    private String firstName;
    private String lastName;
}
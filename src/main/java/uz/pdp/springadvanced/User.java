package uz.pdp.springadvanced;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;
}

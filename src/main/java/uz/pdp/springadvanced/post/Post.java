package uz.pdp.springadvanced.post;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.springadvanced.user.Users;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    private Integer id;
    private String title;
    private String body;
    private Rating rating;
    @ManyToOne(cascade = CascadeType.ALL)
    private Users user;

}

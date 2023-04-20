package uz.pdp.springadvanced.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springadvanced.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
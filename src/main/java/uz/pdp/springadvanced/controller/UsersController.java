package uz.pdp.springadvanced.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.springadvanced.dto.UserCreateDto;
import uz.pdp.springadvanced.entity.Users;
import uz.pdp.springadvanced.service.UsersService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;


    @PostMapping
    public ResponseEntity<Users> create(@RequestBody UserCreateDto dto) {
        return ResponseEntity.status(201).body(usersService.create(dto));
    }

}

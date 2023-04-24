package uz.pdp.springadvanced.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springadvanced.dto.UserCreateDto;
import uz.pdp.springadvanced.entity.Users;
import uz.pdp.springadvanced.service.CacheService;
import uz.pdp.springadvanced.service.MailService;
import uz.pdp.springadvanced.service.MailServiceImpl;
import uz.pdp.springadvanced.service.UsersService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;
    private final MailService mailService;
    private final CacheService cacheService;


    @PostMapping
    public ResponseEntity<Users> create(@RequestBody UserCreateDto dto) {
        return ResponseEntity.status(201).body(usersService.create(dto));
    }

    @PostMapping("/smtp/on-off")
    public ResponseEntity<Boolean> turnOnOffSMTPServer() {
        return ResponseEntity.ok(mailService.turnOnOffSMTPServer());
    }
    @GetMapping
    public ResponseEntity<ConcurrentHashMap<Object, Map<Object,Object>>> getCache() {
        return ResponseEntity.ok(cacheService.getCache());
    }


}

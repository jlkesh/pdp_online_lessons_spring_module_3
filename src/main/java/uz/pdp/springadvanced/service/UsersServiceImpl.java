package uz.pdp.springadvanced.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.springadvanced.dto.UserCreateDto;
import uz.pdp.springadvanced.entity.Users;
import uz.pdp.springadvanced.events.OtpGenerateEvent;
import uz.pdp.springadvanced.mapper.UserMapper;
import uz.pdp.springadvanced.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserMapper userMapper;
    private final UsersRepository usersRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    @Transactional
    public Users create(UserCreateDto dto) {
        Users users = userMapper.fromCreateDto(dto);
        usersRepository.save(users);
        publisher.publishEvent(new OtpGenerateEvent(users));
        return users;
    }
}

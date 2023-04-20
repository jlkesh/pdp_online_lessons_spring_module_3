package uz.pdp.springadvanced.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.springadvanced.entity.Users;
import uz.pdp.springadvanced.repository.UsersRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final UsersRepository usersRepository;

    @Override
    public void generateOtp(Users user) {
        user.setOtp(UUID.randomUUID().toString());
        usersRepository.save(user);

    }
}

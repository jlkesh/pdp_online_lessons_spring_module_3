package uz.pdp.springadvanced.service;

import uz.pdp.springadvanced.entity.Users;

public interface OtpService {
    void generateOtp(Users user);
}

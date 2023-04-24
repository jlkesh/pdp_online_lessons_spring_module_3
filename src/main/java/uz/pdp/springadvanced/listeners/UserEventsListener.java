package uz.pdp.springadvanced.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import uz.pdp.springadvanced.entity.Users;
import uz.pdp.springadvanced.events.OtpGenerateEvent;
import uz.pdp.springadvanced.events.SendMailEvent;
import uz.pdp.springadvanced.service.MailService;
import uz.pdp.springadvanced.service.OtpService;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventsListener {
    private final OtpService otpService;
    private final MailService mailService;

    @EventListener({OtpGenerateEvent.class})
    @Async
    public CompletableFuture<SendMailEvent> generateOtpEventListener(OtpGenerateEvent event) throws InterruptedException {
        Users users = event.getUsers();
        otpService.generateOtp(users);
        log.info("OTP Generated : {}", users);
        return CompletableFuture.completedFuture(new SendMailEvent(users.getId(), users.getEmail(), users.getOtp()));
    }

    @EventListener({SendMailEvent.class})
    public void verificationMailSenderListener(SendMailEvent event) {
        Map<Object, Object> model = Map.of(
                "userID", event.getId(),
                "email", event.getEmail(),
                "otp", event.getOtp()
        );
        mailService.sendVerificationMail(model);
    }

}

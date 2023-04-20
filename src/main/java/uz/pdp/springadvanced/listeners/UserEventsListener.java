package uz.pdp.springadvanced.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import uz.pdp.springadvanced.entity.Users;
import uz.pdp.springadvanced.events.OtpGenerateEvent;
import uz.pdp.springadvanced.events.SendMailEvent;
import uz.pdp.springadvanced.repository.UsersRepository;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventsListener {
    private final UsersRepository usersRepository;

    /*@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, condition = "#event.users.email ne null")
    @Transactional(propagation = Propagation.REQUIRES_NEW)*/
    @EventListener({OtpGenerateEvent.class})
    /*@Order(2)*/
    @Async
    public CompletableFuture<SendMailEvent> generateOtpEventListener(OtpGenerateEvent event) throws InterruptedException {
        Users users = event.getUsers();
        TimeUnit.SECONDS.sleep(8);
        users.setOtp(UUID.randomUUID().toString());
        log.info("Generate OTP with : {}", users);
        usersRepository.save(users);
        /*throw new RuntimeException("Exception For Testing EOP in Spring Boot");*/
        return CompletableFuture.completedFuture(new SendMailEvent(users.getEmail(), users.getOtp()));
    }

    @EventListener({SendMailEvent.class})
    /*@Order(1)*/
    public void verificationMailSenderListener(SendMailEvent event) {
        log.info("Mail Send To :  {}", event);
        // smtp server connect
        // mail send
        // shutdown
    }

}

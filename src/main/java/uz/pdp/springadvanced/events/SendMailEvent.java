package uz.pdp.springadvanced.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public final class SendMailEvent {
    private final Integer id;
    private final String email;
    private final String otp;
}

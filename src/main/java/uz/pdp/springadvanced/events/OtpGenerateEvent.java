package uz.pdp.springadvanced.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uz.pdp.springadvanced.entity.Users;

@Getter
@RequiredArgsConstructor
public final class OtpGenerateEvent {
    private final Users users;
}

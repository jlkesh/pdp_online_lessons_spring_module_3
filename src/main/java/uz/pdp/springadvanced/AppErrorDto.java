package uz.pdp.springadvanced;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppErrorDto {
    private String errorPath;
    private Integer errorCode;
    private String friendlyMessage;
    private Object developerMessage;
}

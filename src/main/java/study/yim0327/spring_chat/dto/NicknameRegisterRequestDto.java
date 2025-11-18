package study.yim0327.spring_chat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class NicknameRegisterRequestDto {
    @Positive
    private Long roomId;

    @NotBlank
    private String nickname;

}

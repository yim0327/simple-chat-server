package study.yim0327.spring_chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NicknameRegisterResponseDto {

    private Long sessionId;
    private String clientId;
    private Long roomId;
    private String nickname;
}

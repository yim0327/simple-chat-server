package study.yim0327.spring_chat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import study.yim0327.spring_chat.entity.MessageType;

@Getter
public class ChatMessageRequestDto {
    @NotNull
    private MessageType type;

    @Positive
    private Long roomId;

    @Positive
    private Long sessionId;

    @NotBlank
    private String content;
}

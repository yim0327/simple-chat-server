package study.yim0327.spring_chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import study.yim0327.spring_chat.entity.MessageType;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ChatMessageResponseDto {

    private Long messageId;
    private MessageType type;
    private String sender;
    private String content;
    private LocalDateTime createdAt;
    private Long roomId;
    private Long sessionId;
}

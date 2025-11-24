package study.yim0327.spring_chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import study.yim0327.spring_chat.entity.ChatMessage;
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

    /** DB에 저장된 채팅 메시지를 DTO로 변환 */
    public static ChatMessageResponseDto fromEntity(ChatMessage message) {
        return new ChatMessageResponseDto(
                message.getId(),
                message.getMessageType(),
                message.getSender(),
                message.getContent(),
                message.getCreatedAt(),
                message.getChatRoom().getId(),
                message.getSession().getId()
        );
    }

}

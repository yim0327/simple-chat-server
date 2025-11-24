package study.yim0327.spring_chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ChatRoomCreateResponseDto {

    private Long roomId;
    private String roomName;
    private String roomCode;
    private LocalDateTime createdAt;
}
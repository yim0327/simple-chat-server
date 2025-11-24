package study.yim0327.spring_chat.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ChatRoomCreateRequestDto {

    @NotBlank
    private String roomName;
}
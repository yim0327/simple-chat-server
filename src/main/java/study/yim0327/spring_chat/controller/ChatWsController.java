package study.yim0327.spring_chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import study.yim0327.spring_chat.dto.ChatMessageRequestDto;
import study.yim0327.spring_chat.dto.ChatMessageResponseDto;
import study.yim0327.spring_chat.service.ChatMessageService;

@Controller
@RequiredArgsConstructor
public class ChatWsController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void handleChat(ChatMessageRequestDto request) {
        // DB 저장 + DTO 변환
        ChatMessageResponseDto response = chatMessageService.save(request);

        // roomId(방)별 브로드캐스트
        messagingTemplate.convertAndSend("/topic/rooms/" + response.getRoomId(), response);
    }
}

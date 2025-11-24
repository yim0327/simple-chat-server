package study.yim0327.spring_chat.service;

import org.springframework.stereotype.Service;
import study.yim0327.spring_chat.dto.ChatMessageRequestDto;
import study.yim0327.spring_chat.dto.ChatMessageResponseDto;

@Service
public class ChatMessageService {

    public ChatMessageResponseDto save(ChatMessageRequestDto request) {
        throw new IllegalArgumentException("Not implemented");
    }
}

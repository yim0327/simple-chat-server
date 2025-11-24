package study.yim0327.spring_chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.yim0327.spring_chat.dto.ChatRoomCreateRequestDto;
import study.yim0327.spring_chat.dto.ChatRoomCreateResponseDto;
import study.yim0327.spring_chat.dto.ChatRoomSearchResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomService {

    public ChatRoomCreateResponseDto createRoom(ChatRoomCreateRequestDto request) {
        throw new IllegalArgumentException("Not implemented");
    }

    public List<ChatRoomSearchResponseDto> getAllRooms() {
        throw new IllegalArgumentException("Not implemented");
    }

}

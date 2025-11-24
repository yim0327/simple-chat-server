package study.yim0327.spring_chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.yim0327.spring_chat.dto.ChatRoomCreateRequestDto;
import study.yim0327.spring_chat.dto.ChatRoomCreateResponseDto;
import study.yim0327.spring_chat.dto.ChatRoomSearchResponseDto;
import study.yim0327.spring_chat.entity.ChatRoom;
import study.yim0327.spring_chat.repository.ChatRoomRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    /** 채팅방 생성 */
    public ChatRoomCreateResponseDto createRoom(ChatRoomCreateRequestDto request) {
        String roomName = request.getRoomName();

        if (chatRoomRepository.existsByRoomName(roomName)) {
            throw new IllegalArgumentException("이미 존재하는 채팅방 이름입니다.");
        }

        // 랜덤 roomCode 8자 생성
        String roomCode = UUID.randomUUID().toString().substring(0, 8);

        ChatRoom room = ChatRoom.create(roomName, roomCode);
        ChatRoom saved = chatRoomRepository.save(room);

        return new ChatRoomCreateResponseDto(
                saved.getId(),
                saved.getRoomName(),
                saved.getRoomCode(),
                saved.getCreatedAt()
        );
    }

    /** 전체 채팅방 목록 조회 */
    public List<ChatRoomSearchResponseDto> getAllRooms() {
        return chatRoomRepository.findAll().stream()
                .map(room -> new ChatRoomSearchResponseDto(
                        room.getId(),
                        room.getRoomName(),
                        room.getRoomCode(),
                        room.getCreatedAt(),
                        room.getUpdatedAt()
                ))
                .toList();
    }

}

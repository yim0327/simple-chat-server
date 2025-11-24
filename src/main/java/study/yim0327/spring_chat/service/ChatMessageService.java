package study.yim0327.spring_chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.yim0327.spring_chat.dto.ChatMessageRequestDto;
import study.yim0327.spring_chat.dto.ChatMessageResponseDto;
import study.yim0327.spring_chat.entity.*;
import study.yim0327.spring_chat.repository.ChatMessageRepository;
import study.yim0327.spring_chat.repository.SessionRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final SessionRepository sessionRepository;

    public ChatMessageResponseDto save(ChatMessageRequestDto request) {

        Session session = sessionRepository.findById(request.getSessionId())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 세션입니다."));

        ActiveNickname activeNickname = session.getActiveNickname();
        ChatRoom chatRoom = activeNickname.getChatRoom();

        // roomId 검증 (세션이 속한 방과 요청 방이 같은지)
        if (!chatRoom.getId().equals(request.getRoomId())) {
            throw new IllegalArgumentException("세션이 속한 채팅방과 요청한 채팅방이 일치하지 않습니다.");
        }

        // TALK 타입 확인
        if (request.getType() != MessageType.TALK) {
            throw new IllegalArgumentException("채팅 메시지는 TALK 타입만 저장할 수 있습니다.");
        }

        // 메시지 엔티티 생성
        ChatMessage chatMessage = ChatMessage.create(
                request.getType(),
                activeNickname.getActiveNickname(),
                request.getContent(),
                chatRoom,
                session
        );

        // 메시지 DB 저장
        ChatMessage saved = chatMessageRepository.save(chatMessage);

        // 응답 DTO 변환
        return ChatMessageResponseDto.fromEntity(saved);
    }
}

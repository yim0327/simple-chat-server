package study.yim0327.spring_chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.yim0327.spring_chat.dto.NicknameRegisterResponseDto;
import study.yim0327.spring_chat.entity.ActiveNickname;
import study.yim0327.spring_chat.entity.ChatRoom;
import study.yim0327.spring_chat.entity.Session;
import study.yim0327.spring_chat.repository.ActiveNicknameRepository;
import study.yim0327.spring_chat.repository.ChatRoomRepository;
import study.yim0327.spring_chat.repository.SessionRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final ActiveNicknameRepository activeNicknameRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public NicknameRegisterResponseDto register(Long roomId, String nickname) {
        validateNickname(nickname);

        ChatRoom room = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다: id=" + roomId));

        // 방 안에서 닉네임 중복 체크
        if (activeNicknameRepository.existsByChatRoom_IdAndActiveNickname(roomId, nickname)) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        // 활성 닉네임 생성 & 저장
        ActiveNickname activeNickname = ActiveNickname.create(nickname, room);
        activeNicknameRepository.save(activeNickname);

        // 고유 clientId 생성 (브라우저 탭 식별용)
        String clientId = UUID.randomUUID().toString();

        // 세션 생성 & 저장
        Session session = Session.create(clientId, activeNickname);
        sessionRepository.save(session);

        return new NicknameRegisterResponseDto(
                session.getId(),
                session.getClientId(),
                room.getId(),
                activeNickname.getActiveNickname()
        );
    }

    @Transactional
    public void disconnect(Long sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("세션을 찾을 수 없습니다."));

        if (!session.isActive()) {
            // 이미 종료된 세션이면 그냥 리턴하거나, 예외 던질지 선택
            return;
        }

        // 세션 상태 변경 (소프트 종료)
        session.disconnect();  // disconnectedAt, active=false

        // 닉네임 / 방 정보 정리
        ActiveNickname activeNickname = session.getActiveNickname();
        Long roomId = activeNickname.getChatRoom().getId();
        String nickname = activeNickname.getActiveNickname();

        // 활성 닉네임 참조 끊기
        session.detachActiveNickname();

        // 닉네임 엔티티 삭제
        activeNicknameRepository.delete(activeNickname);
    }

    private void validateNickname(String nickname) {
        if (nickname == null || nickname.isBlank()) {
            throw new IllegalArgumentException("닉네임은 비어 있을 수 없습니다.");
        }
        if (nickname.length() > 20) {
            throw new IllegalArgumentException("닉네임은 20자 이하만 가능합니다.");
        }
    }

}

package study.yim0327.spring_chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.yim0327.spring_chat.entity.ActiveNickname;

@Repository
public interface ActiveNicknameRepository extends JpaRepository<ActiveNickname, Long> {

    // 같은 방 안에서 닉네임 중복 체크
    boolean existsByChatRoom_IdAndActiveNickname(Long roomId, String activeNickname);
}

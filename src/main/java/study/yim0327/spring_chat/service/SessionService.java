package study.yim0327.spring_chat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.yim0327.spring_chat.dto.NicknameRegisterResponseDto;

@Service
public class SessionService {

    @Transactional
    public NicknameRegisterResponseDto register(Long roomId, String nickname) {
        throw new IllegalArgumentException("Not implemented");
    }

}

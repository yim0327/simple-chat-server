package study.yim0327.spring_chat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.yim0327.spring_chat.dto.NicknameRegisterRequestDto;
import study.yim0327.spring_chat.dto.NicknameRegisterResponseDto;
import study.yim0327.spring_chat.service.SessionService;
import study.yim0327.spring_chat.util.ApiResponse;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping("/sessions")
    public ResponseEntity<ApiResponse<NicknameRegisterResponseDto>> registerNickname(@Valid @RequestBody NicknameRegisterRequestDto request) {
        NicknameRegisterResponseDto response =
                sessionService.register(request.getRoomId(), request.getNickname());

        return ResponseEntity.ok(ApiResponse.success(response));
    }

}

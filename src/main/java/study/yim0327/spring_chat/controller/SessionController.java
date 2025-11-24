package study.yim0327.spring_chat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.yim0327.spring_chat.dto.NicknameRegisterRequestDto;
import study.yim0327.spring_chat.dto.NicknameRegisterResponseDto;
import study.yim0327.spring_chat.service.SessionService;
import study.yim0327.spring_chat.util.ApiResponse;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<ApiResponse<NicknameRegisterResponseDto>> registerNickname(@Valid @RequestBody NicknameRegisterRequestDto request) {
        NicknameRegisterResponseDto response =
                sessionService.register(request.getRoomId(), request.getNickname());

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 자동 퇴장
    @PostMapping("/{sessionId}")
    public ResponseEntity<ApiResponse<Void>> disconnectByPost(@PathVariable Long sessionId) {
        sessionService.disconnect(sessionId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

}

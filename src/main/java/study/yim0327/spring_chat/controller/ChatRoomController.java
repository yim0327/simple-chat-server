package study.yim0327.spring_chat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.yim0327.spring_chat.dto.ChatRoomCreateRequestDto;
import study.yim0327.spring_chat.dto.ChatRoomCreateResponseDto;
import study.yim0327.spring_chat.dto.ChatRoomSearchResponseDto;
import study.yim0327.spring_chat.service.ChatRoomService;
import study.yim0327.spring_chat.util.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    /** 채팅방 생성 */
    @PostMapping
    public ResponseEntity<ApiResponse<ChatRoomCreateResponseDto>> createRoom(@Valid @RequestBody ChatRoomCreateRequestDto request) {
        ChatRoomCreateResponseDto response = chatRoomService.createRoom(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /** 전체 채팅방 목록 조회 */
    @GetMapping
    public ResponseEntity<ApiResponse<List<ChatRoomSearchResponseDto>>> getAllRooms() {
        List<ChatRoomSearchResponseDto> rooms = chatRoomService.getAllRooms();
        return ResponseEntity.ok(ApiResponse.success(rooms));
    }

}

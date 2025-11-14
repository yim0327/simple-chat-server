package study.yim0327.spring_chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "chat_room")
public class ChatRoom {

    /** 채팅방 PK */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Long id;

    /** 채팅방 이름 */
    @Column(name = "room_name", nullable = false, length = 100)
    private String roomName;

    /** 생성 시각 */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** 마지막 활동 시각 (메시지 등록 시 갱신) */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /** 채팅방 고유 코드 (URL 식별용) */
    @Column(name = "room_code", nullable = false, unique = true, length = 100)
    private String roomCode;

    /** 생성 시 자동 타임스탬프 설정 */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /** 수정 시 updatedAt 자동 갱신 */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}

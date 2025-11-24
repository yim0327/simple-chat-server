package study.yim0327.spring_chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "chat_message")
public class ChatMessage {

    /** 메시지 PK */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false)
    private Long id;

    /** 송신자 닉네임 스냅샷 */
    @Column(name = "sender", nullable = false, length = 20)
    private String sender;

    /** 메시지 내용 */
    @Column(name = "content", nullable = false, length = 500)
    private String content;

    /** 메시지 입력 시각 */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** 메시지 타입 */
    @Enumerated(EnumType.STRING) // Enum 이름을 문자열 그대로 DB 저장
    @Column(name = "message_type", nullable = false, length = 10)
    private MessageType messageType;

    /**
     * 채팅방 (N:1)
     * - 여러 메시지가 하나의 채팅방에 속함
     * - 외래키(FK): room_id
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // 방 삭제 시 함께 삭제
    private ChatRoom chatRoom;

    /**
     * 세션 (N:1)
     * - 여러 메시지가 하나의 세션에 속함
     * - 외래키(FK): session_id
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    /** 세션 저장 시 자동 타임스탬프 설정 */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public static ChatMessage create(MessageType type, String sender, String content,
                                     ChatRoom chatRoom, Session session) {
        ChatMessage message = new ChatMessage();
        message.messageType = type;
        message.sender = sender;
        message.content = content;
        message.chatRoom = chatRoom;
        message.session = session;
        return message;
    }

}

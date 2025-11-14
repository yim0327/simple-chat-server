package study.yim0327.spring_chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

/**
 * 세션
 * - WebSocket 연결 1회를 나타냄 (브라우저 탭 단위)
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "session")
public class Session {

    /** 세션 PK */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id", nullable = false)
    private Long id;

    /** 세션 연결 시각 */
    @Column(name = "connected_at", nullable = false, updatable = false)
    private LocalDateTime connectedAt;

    /** 세션 종료 시각 */
    @Column(name = "disconnected_at")
    private LocalDateTime disconnectedAt;

    /** 현재 활성 여부 */
    @Column(name = "active", nullable = false)
    private boolean active;

    /** 클라이언트 식별자 (중복 구독/연결 방지용) */
    @Column(name = "client_id", nullable = false, unique = true, length = 64)
    private String clientId;

    /**
     * 활성 닉네임 (1:1)
     * - 한 (활성)닉네임으로 한 세션만 연결됨
     * - 외래키(FK): active_nickname_id
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "active_nickname_id", nullable = false, unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE) // 퇴장(닉네임 삭제) 시 함께 삭제
    private ActiveNickname activeNickname;

    /** 연결 시 자동 타임스탬프 설정 */
    @PrePersist
    protected void onCreate() {
        this.connectedAt = LocalDateTime.now();
        this.active = true;
    }

    /** 종료 시 자동 타임스탬프 설정 */
    public void disconnect() {
        this.disconnectedAt = LocalDateTime.now();
        this.active = false;
    }

}

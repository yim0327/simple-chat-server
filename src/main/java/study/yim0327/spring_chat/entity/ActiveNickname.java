package study.yim0327.spring_chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "active_nickname",
        // 각 채팅방 별 닉네임 중복 방지
        uniqueConstraints = @UniqueConstraint(
                name = "uq_room_nickname", columnNames = {"room_id", "active_nickname"}
        )
)
public class ActiveNickname {

    /** 활성 닉네임 PK */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "active_nickname_id", nullable = false)
    private Long id;

    /** 활성 닉네임 */
    @Column(name = "active_nickname", nullable = false, length = 20)
    private String activeNickname;

    /**
     * 채팅방 (N:1)
     * - 여러 활성 닉네임이 하나의 채팅방에 속함
     * - 외래키(FK): room_id
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // 방 삭제 시 함께 삭제
    private ChatRoom chatRoom;

}

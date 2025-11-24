package study.yim0327.spring_chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.yim0327.spring_chat.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}

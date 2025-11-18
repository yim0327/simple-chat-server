package study.yim0327.spring_chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.yim0327.spring_chat.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}

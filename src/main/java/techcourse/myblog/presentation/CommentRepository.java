package techcourse.myblog.presentation;

import org.springframework.data.jpa.repository.JpaRepository;
import techcourse.myblog.domain.coment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

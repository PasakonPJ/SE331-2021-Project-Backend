package se331.lab.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Page<Comment> findByPatient_Id(Long id, Pageable pageable);
}

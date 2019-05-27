package hackathon.board.dao;

import hackathon.board.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author cst
 */
@Repository
public interface LessonDao extends JpaRepository<Lesson, Integer> {
  List<Lesson> findByCourseId(int courseId);

  Optional<Lesson> findByIdAndTeacherId(int id, int teacherId);
}

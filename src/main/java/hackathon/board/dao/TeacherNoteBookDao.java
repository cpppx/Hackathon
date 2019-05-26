package hackathon.board.dao;

import hackathon.board.entity.TeacherNoteBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author cst
 */
@Repository
public interface TeacherNoteBookDao extends JpaRepository<TeacherNoteBook, Integer> {
  Optional<TeacherNoteBook> findByLessonId(int lessonId);

  Optional<TeacherNoteBook> findByLessonIdAndTeacherId(int lessonId, int teacherId);

  Optional<TeacherNoteBook> findByIdAndTeacherId(int id, int teacherId);
}

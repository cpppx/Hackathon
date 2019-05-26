package hackathon.board.dao;

import hackathon.board.entity.StudentNoteBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author cst
 */
@Repository
public interface StudentNoteBookDao extends JpaRepository<StudentNoteBook, Integer> {
  Optional<StudentNoteBook> findByLessonIdAndStudentId(int lessonId, int studentId);

  List<StudentNoteBook> findByLessonId(int lessonId);

  Optional<StudentNoteBook> findByIdAndStudentId(int id, int studentId);

  boolean existsByLessonIdAndStudentId(int lessonId, int studentId);
}

package hackathon.board.dao;

import hackathon.board.entity.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cst
 */
@Repository
public interface CourseStudentDao extends JpaRepository<CourseStudent, Integer> {
  @Query("select courseId from CourseStudent where studentId = ?1")
  List<Integer> findCourseIdsByStudentId(int studentId);

  boolean existsByCourseIdAndStudentId(int courseId, int studentId);
}

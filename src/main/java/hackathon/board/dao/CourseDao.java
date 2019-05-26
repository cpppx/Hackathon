package hackathon.board.dao;

import hackathon.board.entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author cst
 */
public interface CourseDao extends JpaRepository<Course, Integer> {
  Optional<Course> findByIdAndTeacherId(int id, int teacherId);

  boolean existsByIdAndTeacherId(int id, int teacherId);

  List<Course> findByTeacherIdAndFinished(int teacherId, boolean finished);

  @Query(value = "from Course where name like concat('%',?1,'%')")
  List<Course> findByNameLike(String keyword, Pageable pageable);
}

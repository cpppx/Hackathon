package hackathon.board.dao;

import hackathon.board.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author cst
 */
public interface CourseDao extends JpaRepository<Course, Integer> {
  Optional<Course> findByIdAndTeacherId(int id, int teacherId);

  boolean existsByIdAndTeacherId(int id, int teacherId);

  List<Course> findByTeacherIdAndFinished(int teacherId, boolean finished);

  //  @Query(value = "from Course where name like concat('%',?1,'%') and id not in ?2")
  List<Course> findByNameLikeAndIdIsNotIn(String keyword, List<Integer> ids);

  List<Course> findByIdIsNotIn(List<Integer> courseIds);
}

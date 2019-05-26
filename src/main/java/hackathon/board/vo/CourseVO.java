package hackathon.board.vo;

import hackathon.board.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author cst
 */
@Getter
@AllArgsConstructor
@ToString
public class CourseVO {
  private Integer id;

  private String name;

  private Boolean finished;

  private Integer teacherId;

  private String teacherName;

  public CourseVO(Course course) {
    this.id = course.getId();
    this.name = course.getName();
    this.finished = course.isFinished();
    this.teacherId = course.getTeacherId();
    this.teacherName = course.getTeacherName();
  }
}

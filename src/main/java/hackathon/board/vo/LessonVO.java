package hackathon.board.vo;

import hackathon.board.entity.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author cst
 */
@Getter
@AllArgsConstructor
public class LessonVO {
  private Integer id;

  private String name;

  private Integer courseId;

  private Integer teacherId;

  private Long startTime;

  private Long endTime;

  public LessonVO(Lesson lesson) {
    this.id = lesson.getId();
    this.name = lesson.getName();
    this.courseId = lesson.getCourseId();
    this.teacherId = lesson.getTeacherId();
    this.startTime = lesson.getStartTime();
    this.endTime = lesson.getEndTime();
  }
}

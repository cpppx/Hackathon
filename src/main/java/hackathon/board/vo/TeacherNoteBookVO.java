package hackathon.board.vo;

import hackathon.board.entity.TeacherNoteBook;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cst
 */
@Getter
@AllArgsConstructor
@ToString
public class TeacherNoteBookVO {
  private Integer id;

  private Integer teacherId;

  private Integer lessonId;

  private String color;
  private List<TeacherNoteItemVO> items;

  public TeacherNoteBookVO(TeacherNoteBook book) {
    this.id = book.getId();
    this.teacherId = book.getTeacherId();
    this.lessonId = book.getLessonId();
    this.color = book.getColor();
    this.items = book.getItems().stream().map(TeacherNoteItemVO::new).collect(Collectors.toList());
  }
}

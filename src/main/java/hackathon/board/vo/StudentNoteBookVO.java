package hackathon.board.vo;

import hackathon.board.entity.StudentNoteBook;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cst
 */
@Getter
@AllArgsConstructor
public class StudentNoteBookVO {
  private Integer id;
  private Integer lessonId;
  private Integer studentId;
  private List<StudentNoteItemVO> items;

  public StudentNoteBookVO(StudentNoteBook book) {
    this.id = book.getId();
    this.lessonId = book.getLessonId();
    this.studentId = book.getStudentId();
    this.items = book.getItems().stream().map(StudentNoteItemVO::new).collect(Collectors.toList());
  }
}

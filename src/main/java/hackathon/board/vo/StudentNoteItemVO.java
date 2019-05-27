package hackathon.board.vo;

import hackathon.board.entity.StudentNoteItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author cst
 */
@Getter
@AllArgsConstructor
public class StudentNoteItemVO {
  private Integer id;
  private Integer teacherNoteItemId;
  private String content;

  public StudentNoteItemVO(StudentNoteItem item) {
    this.id = item.getId();
    this.teacherNoteItemId = item.getTeacherNoteItemId();
    this.content = item.getContent();
  }
}

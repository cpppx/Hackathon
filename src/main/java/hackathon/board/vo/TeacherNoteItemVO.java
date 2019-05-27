package hackathon.board.vo;

import hackathon.board.entity.Point;
import hackathon.board.entity.TeacherNoteItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author cst
 */
@Getter
@AllArgsConstructor
public class TeacherNoteItemVO {
  private int id;

  private int page;
  private Long createTime;

  private String color;

  private String content;

  private List<Point> coordinates;

  public TeacherNoteItemVO(TeacherNoteItem teacherNoteItem) {
    this.id = teacherNoteItem.getId();
    this.page = teacherNoteItem.getPage();
    this.color = teacherNoteItem.getColor();
    this.content = teacherNoteItem.getContent();
    this.coordinates = teacherNoteItem.getCoordinates();
    this.createTime = teacherNoteItem.getCreateTime();
  }
}

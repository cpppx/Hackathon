package hackathon.board.vo;

import lombok.Getter;

/**
 * @author cst
 */
@Getter
public class LiveLessonData {
  private String operationType;
  private Object teacherNoteItem;

  public LiveLessonData(String operationType, Object teacherNoteItem) {
    this.operationType = operationType;
    this.teacherNoteItem = teacherNoteItem;
  }
}

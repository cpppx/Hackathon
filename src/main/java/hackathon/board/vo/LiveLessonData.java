package hackathon.board.vo;

/**
 * @author cst
 */
public class LiveLessonData {
  String operationType;
  Object teacherNoteItem;

  public LiveLessonData(String operationType, Object teacherNoteItem) {
    this.operationType = operationType;
    this.teacherNoteItem = teacherNoteItem;
  }
}

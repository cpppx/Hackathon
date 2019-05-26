package hackathon.board.service;

import hackathon.board.vo.LessonVO;
import hackathon.board.vo.TeacherNoteBookVO;

import java.util.List;

/**
 * @author cst
 */
public interface LessonService {
  int joinLesson(int lessonId, int studentId);

  TeacherNoteBookVO createLesson(int teacherId, LessonVO lessonVO);

  void endLesson(int lessonId, int teacherId);

  List<LessonVO> getLessonsByCourseId(int courseId);
}

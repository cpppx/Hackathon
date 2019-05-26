package hackathon.board.service;

import hackathon.board.vo.TeacherNoteBookVO;
import hackathon.board.vo.TeacherNoteItemVO;

/**
 * @author cst
 */
public interface TeacherNoteService {
  TeacherNoteBookVO getTeacherNoteBookByLessonId(int lessonId);

  TeacherNoteItemVO createTeacherNoteItem(int teacherId, int bookId, TeacherNoteItemVO teacherNoteItemVO);

  TeacherNoteItemVO updateTeacherNoteItem(int teacherId, int bookId, TeacherNoteItemVO teacherNoteItemVO);

  void deleteTeacherNoteItem(int teacherId, int bookId, int teacherNoteId);
}

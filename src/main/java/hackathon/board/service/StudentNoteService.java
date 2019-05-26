package hackathon.board.service;

import hackathon.board.vo.StudentNoteBookVO;
import hackathon.board.vo.StudentNoteItemVO;

import java.util.List;

/**
 * @author cst
 */
public interface StudentNoteService {
  StudentNoteBookVO getStudentNoteBookByLessonId(int lessonId, int studentId);

  StudentNoteItemVO createStudentNoteItem(int studentId, int bookId, StudentNoteItemVO studentNoteItemVO);

  StudentNoteItemVO updateStudentNoteItem(int studentId, int bookId, StudentNoteItemVO studentNoteItemVO);

  void deleteStudentNoteItem(int studentId, int bookId, int studentNoteId);

  List<StudentNoteBookVO> getShareNoteBooks(int lessonId);

  void clone(int studentId, int studentNoteBookId, int cloneNoteBookId);
}

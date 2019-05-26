package hackathon.board.service.impl;

import hackathon.board.dao.LessonDao;
import hackathon.board.dao.StudentNoteBookDao;
import hackathon.board.entity.Lesson;
import hackathon.board.entity.StudentNoteBook;
import hackathon.board.entity.StudentNoteItem;
import hackathon.board.exception.ForbiddenException;
import hackathon.board.exception.NotFoundException;
import hackathon.board.service.StudentNoteService;
import hackathon.board.vo.StudentNoteBookVO;
import hackathon.board.vo.StudentNoteItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cst
 */
@Service
public class StudentNoteServiceImpl implements StudentNoteService {
  private final LessonDao lessonDao;

  private final StudentNoteBookDao studentNoteBookDao;

  @Autowired
  public StudentNoteServiceImpl(StudentNoteBookDao studentNoteBookDao, LessonDao lessonDao) {
    this.studentNoteBookDao = studentNoteBookDao;
    this.lessonDao = lessonDao;
  }

  @Override
  public StudentNoteItemVO createStudentNoteItem(int studentId, int bookId, StudentNoteItemVO studentNoteItemVO) {
    StudentNoteBook book = studentNoteBookDao.findByIdAndStudentId(bookId, studentId)
            .orElseThrow(() -> new ForbiddenException("你不具有操作该笔记的权限"));
    StudentNoteItem studentNoteItem = new StudentNoteItem();
    studentNoteItem.setTeacherNoteItemId(studentNoteItemVO.getTeacherNoteItemId());
    studentNoteItem.setContent(studentNoteItemVO.getContent());
    book.getItems().add(studentNoteItem);

    book = studentNoteBookDao.save(book);
    return new StudentNoteItemVO(book.getItems().get(book.getItems().size() - 1));
  }

  @Override
  public StudentNoteItemVO updateStudentNoteItem(int studentId, int bookId, StudentNoteItemVO studentNoteItemVO) {
    StudentNoteBook book = studentNoteBookDao.findByIdAndStudentId(bookId, studentId)
            .orElseThrow(() -> new ForbiddenException("你不具有操作该笔记的权限"));

    int index = -1;
    for (int i = 0; i < book.getItems().size(); i++) {
      StudentNoteItem item = book.getItems().get(i);
      if (item.getId() == studentNoteItemVO.getId()) {
        index = i;
        item.setTeacherNoteItemId(studentNoteItemVO.getTeacherNoteItemId());
        item.setContent(studentNoteItemVO.getContent());
        break;
      }
    }

    if (index != -1) {
      book = studentNoteBookDao.save(book);
      return new StudentNoteItemVO(book.getItems().get(index));
    } else {
      throw new NotFoundException("找不到该笔记项目");
    }
  }

  @Override
  public void deleteStudentNoteItem(int studentId, int bookId, int studentNoteId) {
    StudentNoteBook book = studentNoteBookDao.findByIdAndStudentId(bookId, studentId)
            .orElseThrow(() -> new ForbiddenException("您不具有删除该笔记项目的权限"));

    List<StudentNoteItem> items = book.getItems();
    items.removeIf(x -> x.getId() == studentNoteId);

    studentNoteBookDao.save(book);
  }

  @Override
  public StudentNoteBookVO getStudentNoteBookByLessonId(int lessonId, int studentId) {
    StudentNoteBook book = studentNoteBookDao.findByLessonIdAndStudentId(lessonId, studentId)
            .orElseThrow(() -> new NotFoundException("找不到该节课的笔记"));

    return new StudentNoteBookVO(book);
  }

  @Override
  public List<StudentNoteBookVO> getShareNoteBooks(int lessonId) {
    Lesson lesson = lessonDao.findById(lessonId)
            .orElseThrow(() -> new NotFoundException("找不到该节课"));

    if (lesson.getEndTime() == 0) {
      throw new ForbiddenException("未下课");
    }
    return studentNoteBookDao.findByLessonId(lessonId)
            .stream()
            .map(StudentNoteBookVO::new)
            .collect(Collectors.toList());
  }

  @Override
  public void clone(int studentId, int studentNoteBookId, int cloneNoteBookId) {
    StudentNoteBook selfNoteBook = studentNoteBookDao.findByIdAndStudentId(studentNoteBookId, studentId)
            .orElseThrow(() -> new ForbiddenException("您不具有修改该笔记的权限"));

    StudentNoteBook cloneNoteBook = studentNoteBookDao.findById(cloneNoteBookId)
            .orElseThrow(() -> new NotFoundException("找不到克隆的笔记"));

    if (selfNoteBook.getLessonId() != cloneNoteBook.getLessonId()) {
      throw new ForbiddenException("两篇笔记不属于同一节课");
    }

    Lesson lesson = lessonDao.findById(selfNoteBook.getLessonId())
            .orElseThrow(() -> new NotFoundException("找不到该节课"));


    if (lesson.getEndTime() == 0) {
      throw new ForbiddenException("未下课");
    }

    List<StudentNoteItem> items = selfNoteBook.getItems();
    items.clear();

    cloneNoteBook.getItems()
            .forEach(cloneNoteItem -> {
              StudentNoteItem newNoteItem = new StudentNoteItem();
              newNoteItem.setContent(cloneNoteItem.getContent());
              newNoteItem.setTeacherNoteItemId(cloneNoteItem.getTeacherNoteItemId());
              items.add(newNoteItem);
            });

    selfNoteBook.setItems(items);

    studentNoteBookDao.save(selfNoteBook);
  }
}

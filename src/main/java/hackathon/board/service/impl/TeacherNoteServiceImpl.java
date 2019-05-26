package hackathon.board.service.impl;

import hackathon.board.dao.TeacherNoteBookDao;
import hackathon.board.entity.TeacherNoteBook;
import hackathon.board.entity.TeacherNoteItem;
import hackathon.board.exception.ForbiddenException;
import hackathon.board.exception.NotFoundException;
import hackathon.board.service.TeacherNoteService;
import hackathon.board.vo.TeacherNoteBookVO;
import hackathon.board.vo.TeacherNoteItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cst
 */
@Service
public class TeacherNoteServiceImpl implements TeacherNoteService {
  private final TeacherNoteBookDao teacherNoteBookDao;

  @Autowired
  public TeacherNoteServiceImpl(TeacherNoteBookDao teacherNoteBookDao) {
    this.teacherNoteBookDao = teacherNoteBookDao;
  }

  @Override
  public TeacherNoteBookVO getTeacherNoteBookByLessonId(int lessonId) {
    TeacherNoteBook book = teacherNoteBookDao.findByLessonId(lessonId)
            .orElseThrow(() -> new NotFoundException("找不到该板书"));

    return new TeacherNoteBookVO(book);
  }

  @Override
  public TeacherNoteItemVO createTeacherNoteItem(int teacherId, int bookId, TeacherNoteItemVO teacherNoteItemVO) {
    TeacherNoteBook book = teacherNoteBookDao.findByIdAndTeacherId(bookId, teacherId)
            .orElseThrow(() -> new ForbiddenException("您不具有修改该板书的权限"));

    teacherNoteItemVO
            .getCoordinates()
            .forEach(x -> x.setId(0));

    TeacherNoteItem item = new TeacherNoteItem();
    item.setPage(teacherNoteItemVO.getPage());
    item.setColor(teacherNoteItemVO.getColor());
    item.setContent(teacherNoteItemVO.getContent());
    item.setCreateTime(teacherNoteItemVO.getCreateTime());
    item.setCoordinates(teacherNoteItemVO.getCoordinates());

    book.getItems().add(item);

    book = teacherNoteBookDao.save(book);
    item = book.getItems().get(book.getItems().size() - 1);
    return new TeacherNoteItemVO(item);
  }

  @Override
  public TeacherNoteItemVO updateTeacherNoteItem(int teacherId, int bookId, TeacherNoteItemVO teacherNoteItemVO) {
    TeacherNoteBook book = teacherNoteBookDao.findByIdAndTeacherId(bookId, teacherId)
            .orElseThrow(() -> new ForbiddenException("您不具有修改该板书的权限"));

    int index = -1;
    teacherNoteItemVO
            .getCoordinates()
            .forEach(x -> x.setId(0));

    for (int i = 0; i < book.getItems().size(); i++) {
      TeacherNoteItem item = book.getItems().get(i);
      if (item.getId() == teacherNoteItemVO.getId()) {
        index = i;
        item.setPage(teacherNoteItemVO.getPage());
        item.setColor(teacherNoteItemVO.getColor());
        item.setContent(teacherNoteItemVO.getContent());
        item.setCreateTime(teacherNoteItemVO.getCreateTime());
        item.setCoordinates(teacherNoteItemVO.getCoordinates());
        break;
      }
    }

    if (index != -1) {
      book = teacherNoteBookDao.save(book);
      return new TeacherNoteItemVO(book.getItems().get(index));
    } else {
      throw new NotFoundException("找不到该板书项目");
    }
  }

  @Override
  public void deleteTeacherNoteItem(int bookId, int teacherNoteId, int teacherId) {
    TeacherNoteBook book = teacherNoteBookDao.findByIdAndTeacherId(bookId, teacherId)
            .orElseThrow(() -> new ForbiddenException("您不具有删除该板书项目的权限"));

    List<TeacherNoteItem> items = book.getItems();
    for (int i = 0, length = items.size(); i < length; i++) {
      TeacherNoteItem item = items.get(i);
      if (item.getId() == teacherNoteId) {
        items.remove(i);
        break;
      }
    }

    teacherNoteBookDao.save(book);
  }
}

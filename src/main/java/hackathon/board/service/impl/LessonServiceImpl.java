package hackathon.board.service.impl;

import hackathon.board.dao.CourseDao;
import hackathon.board.dao.LessonDao;
import hackathon.board.dao.StudentNoteBookDao;
import hackathon.board.dao.TeacherNoteBookDao;
import hackathon.board.entity.Lesson;
import hackathon.board.entity.StudentNoteBook;
import hackathon.board.entity.TeacherNoteBook;
import hackathon.board.exception.ForbiddenException;
import hackathon.board.exception.NotFoundException;
import hackathon.board.service.LessonService;
import hackathon.board.vo.LessonVO;
import hackathon.board.vo.TeacherNoteBookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cst
 */
@Service
public class LessonServiceImpl implements LessonService {
  private final CourseDao courseDao;

  private final LessonDao lessonDao;

  private final TeacherNoteBookDao teacherNoteBookDao;
  private final StudentNoteBookDao studentNoteBookDao;

  @Autowired
  public LessonServiceImpl(CourseDao courseDao, LessonDao lessonDao, TeacherNoteBookDao teacherNoteBookDao, StudentNoteBookDao studentNoteBookDao) {
    this.courseDao = courseDao;
    this.lessonDao = lessonDao;
    this.teacherNoteBookDao = teacherNoteBookDao;
    this.studentNoteBookDao = studentNoteBookDao;
  }

  @Override
  public int joinLesson(int lessonId, int studentId) {
    if (!studentNoteBookDao.existsByLessonIdAndStudentId(lessonId, studentId)) {
      StudentNoteBook book = new StudentNoteBook();
      book.setLessonId(lessonId);
      book.setStudentId(studentId);
      studentNoteBookDao.save(book);
    }

    TeacherNoteBook teacherNoteBook = teacherNoteBookDao.findByLessonId(lessonId)
            .orElseThrow(() -> new NotFoundException("找不到该节课的板书"));
    return teacherNoteBook.getId();
  }

  @Override
  public TeacherNoteBookVO createLesson(int teacherId, LessonVO lessonVO) {
    if (!courseDao.existsByIdAndTeacherId(lessonVO.getCourseId(), teacherId)) {
      throw new ForbiddenException("您不具有创建该课程课的权限");
    }

    Lesson lesson = new Lesson();
    lesson.setCourseId(lessonVO.getCourseId());
    lesson.setTeacherId(teacherId);
    lesson.setStartTime(System.currentTimeMillis());
    lesson.setName(lessonVO.getName());
    lesson.setEndTime(0);

    lesson = lessonDao.save(lesson);

    TeacherNoteBook teacherNoteBook = new TeacherNoteBook();
    teacherNoteBook.setColor("BLACK");
    teacherNoteBook.setLessonId(lesson.getId());
    teacherNoteBook.setTeacherId(teacherId);

    teacherNoteBook = teacherNoteBookDao.save(teacherNoteBook);

    return new TeacherNoteBookVO(teacherNoteBook);
  }

  @Override
  public void endLesson(int lessonId, int teacherId) {
    Lesson lesson = lessonDao.findByIdAndTeacherId(lessonId, teacherId)
            .orElseThrow(() -> new ForbiddenException("您不具有修改该节课的权限"));

    lesson.setEndTime(System.currentTimeMillis());

    lessonDao.save(lesson);
  }

  @Override
  public List<LessonVO> getLessonsByCourseId(int courseId) {
    return lessonDao.findByCourseId(courseId)
            .stream()
            .map(LessonVO::new)
            .collect(Collectors.toList());
  }
}

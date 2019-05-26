package hackathon.board.service.impl;

import hackathon.board.dao.CourseDao;
import hackathon.board.dao.CourseStudentDao;
import hackathon.board.dao.LessonDao;
import hackathon.board.entity.Course;
import hackathon.board.entity.CourseStudent;
import hackathon.board.entity.Lesson;
import hackathon.board.exception.ForbiddenException;
import hackathon.board.service.CourseService;
import hackathon.board.vo.CourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cst
 */
@Service
public class CourseServiceImpl implements CourseService {
  private final CourseDao courseDao;

  private final CourseStudentDao courseStudentDao;

  private final LessonDao lessonDao;

  @Autowired
  public CourseServiceImpl(CourseDao courseDao, CourseStudentDao courseStudentDao, LessonDao lessonDao) {
    this.courseDao = courseDao;
    this.courseStudentDao = courseStudentDao;
    this.lessonDao = lessonDao;
  }

  @Override
  public CourseVO createCourse(int teacherId, CourseVO courseVO) {
    Course course = new Course();
    course.setName(courseVO.getName());
    course.setTeacherId(teacherId);
    course.setTeacherName(courseVO.getTeacherName());
    course.setFinished(false);
    return new CourseVO(courseDao.save(course));
  }

  @Override
  public CourseVO updateCourse(int teacherId, CourseVO courseVO) {
    Course course = courseDao.findByIdAndTeacherId(courseVO.getId(), teacherId)
            .orElseThrow(() -> new ForbiddenException("不具有修改 Course 权限"));

    course.setFinished(courseVO.getFinished());
    course.setName(courseVO.getName());

    course = courseDao.save(course);

    return new CourseVO(course);
  }

  @Override
  public List<CourseVO> teacherGetCourses(int teacherId, boolean finished) {
    return courseDao.findByTeacherIdAndFinished(teacherId, finished)
            .stream()
            .map(CourseVO::new)
            .collect(Collectors.toList());
  }

  @Override
  public List<CourseVO> studentGetCourses(int studentId, boolean finished) {
    List<Integer> courseIds = courseStudentDao.findCourseIdsByStudentId(studentId);

    return courseDao.findAllById(courseIds)
            .stream()
            .filter(course -> course.isFinished() == finished)
            .map(CourseVO::new)
            .collect(Collectors.toList());
  }

  @Override
  public List<CourseVO> studentGetCoursesByOngoingLessons(int studentId) {
    List<Integer> courseIds = courseStudentDao.findCourseIdsByStudentId(studentId);
    courseIds = courseIds.stream()
            .filter(courseId -> {
              List<Lesson> lessons = lessonDao.findByCourseId(courseId);
              return !lessons.isEmpty();
            })
            .collect(Collectors.toList());
    return courseDao.findAllById(courseIds)
            .stream()
            .map(CourseVO::new)
            .collect(Collectors.toList());
  }

  @Override
  public List<CourseVO> searchCourses(String keyword, int index, int offset) {
    return courseDao.findByNameLike(keyword, PageRequest.of(index, offset))
            .stream()
            .map(CourseVO::new)
            .collect(Collectors.toList());
  }

  @Override
  public void joinCourse(int courseId, int studentId) {
    if (courseStudentDao.existsByCourseIdAndStudentId(courseId, studentId)) {
      throw new ForbiddenException("已加入该课程");
    }

    CourseStudent courseStudent = new CourseStudent();
    courseStudent.setCourseId(courseId);
    courseStudent.setStudentId(studentId);
    courseStudentDao.save(courseStudent);
  }
}

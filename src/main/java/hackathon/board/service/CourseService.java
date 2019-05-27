package hackathon.board.service;

import hackathon.board.vo.CourseVO;

import java.util.List;

/**
 * @author cst
 */
public interface CourseService {
  CourseVO createCourse(int teacherId, CourseVO courseVO);

  CourseVO updateCourse(int teacherId, CourseVO courseVO);


  /**
   * 取得老师课程
   *
   * @param teacherId 教师Id
   * @param finished   完成
   * @return 课程
   */
  List<CourseVO> teacherGetCourses(int teacherId, boolean finished);

  /**
   * 取得学生课程
   *
   * @param studentId 学生id
   * @param finished  完成
   * @return 课程
   */
  List<CourseVO> studentGetCourses(int studentId, boolean finished);

  List<CourseVO> studentGetCoursesByOngoingLessons(int studentId);

  List<CourseVO> searchCourses(int studentId, String keyword, int index, int offset);

  void joinCourse(int courseId, int studentId);
}

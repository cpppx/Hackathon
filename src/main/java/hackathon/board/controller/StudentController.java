package hackathon.board.controller;

import hackathon.board.controller.response.HttpResponse;
import hackathon.board.service.CourseService;
import hackathon.board.service.LessonService;
import hackathon.board.service.StudentNoteService;
import hackathon.board.util.ConstUtil;
import hackathon.board.vo.CourseVO;
import hackathon.board.vo.StudentNoteBookVO;
import hackathon.board.vo.StudentNoteItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static hackathon.board.util.SafeUtil.checkLogin;

/**
 * @author cst
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
public class StudentController {
  private final StudentNoteService studentNoteService;
  private final LessonService lessonService;
  private final CourseService courseService;

  @Autowired
  public StudentController(StudentNoteService studentNoteService, LessonService lessonService, CourseService courseService) {
    this.studentNoteService = studentNoteService;
    this.lessonService = lessonService;
    this.courseService = courseService;
  }

  @GetMapping("/searchCourses")
  public HttpResponse<List<CourseVO>> searchCourses(String keyword, int index, int offset) {
    return new HttpResponse<>(courseService.searchCourses(keyword, index, offset));
  }

  @GetMapping("/studentGetUnfinishedCourses")
  public HttpResponse<List<CourseVO>> studentGetUnfinishedCourses(HttpSession session) {
    int studentId = checkLogin(session, ConstUtil.STUDENT);
    return new HttpResponse<>(courseService.studentGetCourses(studentId, false));
  }

  @GetMapping("/studentGetFinishedCourses")
  public HttpResponse<List<CourseVO>> studentGetFinishedCourses(HttpSession session) {
    int studentId = checkLogin(session, ConstUtil.STUDENT);
    return new HttpResponse<>(courseService.studentGetCourses(studentId, true));
  }

  @GetMapping("/studentGetOngoingCourses")
  public HttpResponse<List<CourseVO>> studentGetOngoingCourses(HttpSession session) {
    int studentId = checkLogin(session, ConstUtil.STUDENT);
    return new HttpResponse<>(courseService.studentGetCoursesByOngoingLessons(studentId));
  }

  @PostMapping("/joinCourse")
  public HttpResponse<Void> joinCourse(HttpSession session, int courseId) {
    int studentId = checkLogin(session, ConstUtil.STUDENT);
    courseService.joinCourse(courseId, studentId);
    return new HttpResponse<>(null);
  }

  @PostMapping("/joinLesson")
  public HttpResponse<String> joinLesson(HttpSession session, int lessonId) {
    int studentId = checkLogin(session, ConstUtil.STUDENT);
    int bookId = lessonService.joinLesson(lessonId, studentId);

    return new HttpResponse<>(ConstUtil.URL + bookId + "/" + studentId);
  }

  @GetMapping("/getStudentNoteBook")
  public HttpResponse<StudentNoteBookVO> getStudentNoteBook(HttpSession session, int lessonId) {
    int studentId = checkLogin(session, ConstUtil.STUDENT);

    return new HttpResponse<>(studentNoteService.getStudentNoteBookByLessonId(lessonId, studentId));
  }

  @PostMapping("/writeStudentNote")
  public HttpResponse<StudentNoteItemVO> writeStudentNote(HttpSession session, int bookId, @RequestBody StudentNoteItemVO studentNoteItemVO) {
    int studentId = checkLogin(session, ConstUtil.STUDENT);

    return new HttpResponse<>(studentNoteService.createStudentNoteItem(studentId, bookId, studentNoteItemVO));
  }

  @PostMapping("/updateStudentNote")
  public HttpResponse<StudentNoteItemVO> updateStudentNote(HttpSession session, int bookId, @RequestBody StudentNoteItemVO studentNoteItemVO) {
    int studentId = checkLogin(session, ConstUtil.STUDENT);

    return new HttpResponse<>(studentNoteService.updateStudentNoteItem(studentId, bookId, studentNoteItemVO));
  }

  @PostMapping("/deleteStudentNote")
  public HttpResponse<Void> deleteStudentNote(HttpSession session, int bookId, int studentNoteId) {
    int studentId = checkLogin(session, ConstUtil.STUDENT);

    studentNoteService.deleteStudentNoteItem(studentId, bookId, studentNoteId);
    return new HttpResponse<>(null);
  }

  @GetMapping("/getSharedNoteBooks")
  public HttpResponse<List<StudentNoteBookVO>> getSharedNoteBooks(int lessonId) {
    return new HttpResponse<>(studentNoteService.getShareNoteBooks(lessonId));
  }

  @PostMapping("/cloneNoteBook")
  public HttpResponse<Void> cloneNoteBook(HttpSession session, int bookId, int cloneNoteBookId) {
    int studentId = checkLogin(session, ConstUtil.STUDENT);
    studentNoteService.clone(studentId, bookId, cloneNoteBookId);
    return new HttpResponse<>(null);
  }
}

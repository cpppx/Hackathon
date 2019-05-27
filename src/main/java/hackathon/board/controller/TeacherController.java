package hackathon.board.controller;

import com.google.gson.Gson;
import hackathon.board.controller.response.HttpResponse;
import hackathon.board.service.CourseService;
import hackathon.board.service.LessonService;
import hackathon.board.service.TeacherNoteService;
import hackathon.board.util.ConstUtil;
import hackathon.board.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static hackathon.board.util.SafeUtil.checkLogin;

/**
 * @author cst
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
public class TeacherController {
  private final TeacherNoteService teacherNoteService;
  private final LessonService lessonService;
  private final CourseService courseService;

  @Autowired
  public TeacherController(TeacherNoteService teacherNoteService, LessonService lessonService, CourseService courseService) {
    this.teacherNoteService = teacherNoteService;
    this.lessonService = lessonService;
    this.courseService = courseService;
  }

  @PostMapping("/createCourse")
  public HttpResponse<CourseVO> createCourse(HttpSession session, @RequestBody CourseVO courseVO) {
    int teacherId = checkLogin(session, ConstUtil.TEACHER);

    return new HttpResponse<>(courseService.createCourse(teacherId, courseVO));
  }

  @PostMapping("/updateCourse")
  public HttpResponse<CourseVO> updateCourse(HttpSession session, @RequestBody CourseVO courseVO) {
    int teacherId = checkLogin(session, ConstUtil.TEACHER);

    return new HttpResponse<>(courseService.updateCourse(teacherId, courseVO));
  }

  @GetMapping("/teacherGetRunningCourses")
  public HttpResponse<List<CourseVO>> teacherGetRunningCourses(HttpSession session) {
    int teacherId = checkLogin(session, ConstUtil.TEACHER);

    return new HttpResponse<>(courseService.teacherGetCourses(teacherId, false));
  }

  @GetMapping("/teacherGetFinishedCourses")
  public HttpResponse<List<CourseVO>> teacherGetFinishedCourses(HttpSession session) {
    int teacherId = checkLogin(session, ConstUtil.TEACHER);

    return new HttpResponse<>(courseService.teacherGetCourses(teacherId, true));
  }

  @PostMapping("/createLesson")
  public HttpResponse<TeacherNoteBookVO> createLesson(HttpSession session, @RequestBody LessonVO lessonVO) {
    int teacherId = checkLogin(session, ConstUtil.TEACHER);

    return new HttpResponse<>(lessonService.createLesson(teacherId, lessonVO));
  }

  @PostMapping("/sendTeacherNote")
  public HttpResponse<TeacherNoteItemVO> sendTeacherNote(HttpSession session, int bookId, @RequestBody TeacherNoteItemVO teacherNoteItemVO) throws IOException {
    int teacherId = checkLogin(session, ConstUtil.TEACHER);

    teacherNoteItemVO = teacherNoteService.createTeacherNoteItem(teacherId, bookId, teacherNoteItemVO);

    LiveLessonData liveLessonData = new LiveLessonData("CREATE", new Gson().toJson(teacherNoteItemVO));
    WebSocketController.sendMessage(bookId, new Gson().toJson(liveLessonData));

    return new HttpResponse<>(teacherNoteItemVO);
  }

  @PostMapping("/updateTeacherNote")
  public HttpResponse<TeacherNoteItemVO> updateTeacherNote(HttpSession session, int bookId, @RequestBody TeacherNoteItemVO teacherNoteItemVO) throws IOException {
    int teacherId = checkLogin(session, ConstUtil.TEACHER);

    teacherNoteItemVO = teacherNoteService.updateTeacherNoteItem(teacherId, bookId, teacherNoteItemVO);

    LiveLessonData liveLessonData = new LiveLessonData("UPDATE", new Gson().toJson(teacherNoteItemVO));
    WebSocketController.sendMessage(bookId, new Gson().toJson(liveLessonData));

    return new HttpResponse<>(teacherNoteItemVO);
  }

  @PostMapping("/deleteTeacherNote")
  public HttpResponse<Void> deleteTeacherNote(HttpSession session, int bookId, int teacherNoteId) throws IOException {
    int teacherId = checkLogin(session, ConstUtil.TEACHER);

    teacherNoteService.deleteTeacherNoteItem(bookId, teacherNoteId, teacherId);

    LiveLessonData liveLessonData = new LiveLessonData("DELETE", teacherNoteId);
    WebSocketController.sendMessage(bookId, new Gson().toJson(liveLessonData));
    return new HttpResponse<>(null);
  }

  @PostMapping("/endLesson")
  public HttpResponse<Void> endLesson(int lessonId, HttpSession session) throws IOException {
    int teacherId = checkLogin(session, ConstUtil.TEACHER);

    lessonService.endLesson(lessonId, teacherId);
    int bookId = teacherNoteService.getTeacherNoteBookByLessonId(lessonId).getId();
    LiveLessonData liveLessonData = new LiveLessonData("END", "");
    WebSocketController.sendMessage(bookId, new Gson().toJson(liveLessonData));

    return new HttpResponse<>(null);
  }
}

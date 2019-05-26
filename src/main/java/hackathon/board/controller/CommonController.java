package hackathon.board.controller;

import hackathon.board.controller.response.HttpResponse;
import hackathon.board.exception.ParamErrorException;
import hackathon.board.service.FileService;
import hackathon.board.service.LessonService;
import hackathon.board.service.TeacherNoteService;
import hackathon.board.service.UserService;
import hackathon.board.util.ConstUtil;
import hackathon.board.util.SafeUtil;
import hackathon.board.vo.LessonVO;
import hackathon.board.vo.TeacherNoteBookVO;
import hackathon.board.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author cst
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
public class CommonController {
  private static final String USERNAME = "username";
  private static final String NAME = "name";
  private static final String PSD = "password";

  private final LessonService lessonService;
  private final UserService userService;
  private final TeacherNoteService teacherNoteService;
  private final FileService fileService;

  @Autowired
  public CommonController(LessonService lessonService, UserService userService, TeacherNoteService teacherNoteService, FileService fileService) {
    this.lessonService = lessonService;
    this.userService = userService;
    this.teacherNoteService = teacherNoteService;
    this.fileService = fileService;
  }

  @PostMapping("/register")
  public HttpResponse<UserVO> register(@RequestBody UserVO userVO) {
    SafeUtil.checkParam(userVO, USERNAME, NAME, PSD);

    if (userVO.getType() >= ConstUtil.getAllUserTypes().length || userVO.getType() < 0) {
      throw new ParamErrorException("type 参数错误");
    }

    return new HttpResponse<>(userService.register(userVO));
  }

  @PostMapping("/login")
  public HttpResponse<UserVO> login(@RequestBody UserVO userVO, HttpSession session) {
    SafeUtil.checkParam(userVO, USERNAME, PSD);

    UserVO resultUserVO = userService.login(userVO);
    session.setAttribute(ConstUtil.getAllUserTypes()[resultUserVO.getType()], resultUserVO.getId());

    return new HttpResponse<>(resultUserVO);
  }

  @GetMapping("/getLessons")
  public HttpResponse<List<LessonVO>> getLessons(int courseId) {
    return new HttpResponse<>(lessonService.getLessonsByCourseId(courseId));
  }

  @GetMapping("/getTeacherNoteBook")
  public HttpResponse<TeacherNoteBookVO> getTeacherNoteBook(int lessonId) {
    return new HttpResponse<>(teacherNoteService.getTeacherNoteBookByLessonId(lessonId));
  }

  @PostMapping("/uploadFile")
  public HttpResponse<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
    return new HttpResponse<>(fileService.saveFile(file));
  }
}

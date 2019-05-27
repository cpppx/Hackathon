package hackathon.board;

import com.google.gson.Gson;
import hackathon.board.controller.response.HttpCode;
import hackathon.board.entity.Point;
import hackathon.board.util.ConstUtil;
import hackathon.board.vo.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("test")
public class BoardApplicationTests {
  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;
  private MockHttpSession session;

  private static final String UTF8 = "utf-8";
  private static final String RESPONSE_CODE = "$.code";

  private static final String STUDENT1_USERNAME = UUID.randomUUID().toString();
  private static final String STUDENT1_PSD = UUID.randomUUID().toString();

  private static final String STUDENT2_USERNAME = UUID.randomUUID().toString();
  private static final String STUDENT2_PSD = UUID.randomUUID().toString();

  private static final String TEACHER_USERNAME = UUID.randomUUID().toString();
  private static final String TEACHER_PSD = UUID.randomUUID().toString();

  @Before
  public void setWebApplicationContext() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    if (session == null) {
      session = new MockHttpSession();
    }
  }

  @Test
  public void test00() throws Exception {
    mockMvc.perform(
            post("/register")
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.ERROR.toString()));
  }


  @Test
  public void test01() throws Exception {
    UserVO userVO = new UserVO(0, null, null, null, 0);
    mockMvc.perform(
            post("/register")
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))


    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.PARAM_ERROR.toString()));

    userVO = new UserVO(0, "", null, null, 0);
    mockMvc.perform(
            post("/register")
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))


    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.PARAM_ERROR.toString()));

    userVO = new UserVO(0, "Mr Chen", null, null, 0);
    mockMvc.perform(
            post("/register")
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))


    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.PARAM_ERROR.toString()));

    userVO = new UserVO(0, "Mr Chen", "", null, 0);
    mockMvc.perform(
            post("/register")
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))


    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.PARAM_ERROR.toString()));

    userVO = new UserVO(0, "Mr Chen", "root", null, 0);
    mockMvc.perform(
            post("/register")
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))


    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.PARAM_ERROR.toString()));


    userVO = new UserVO(0, "Mr Chen", "root", "", 0);
    mockMvc.perform(
            post("/register")
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))


    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.PARAM_ERROR.toString()));

    userVO = new UserVO(0, "Mr Chen", "root", "root", -1);
    mockMvc.perform(
            post("/register")
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))


    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.PARAM_ERROR.toString()));

    userVO = new UserVO(0, "Mr Chen", "root", "root", 5);
    mockMvc.perform(
            post("/register")
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.PARAM_ERROR.toString()));

    userVO = new UserVO(0, "Mr Chen", TEACHER_USERNAME, TEACHER_PSD, 0);
    mockMvc.perform(
            post("/register")
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))


    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));


    userVO = new UserVO(0, "Mr Chen", STUDENT1_USERNAME, STUDENT1_PSD, 1);
    mockMvc.perform(
            post("/register")
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))


    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    userVO = new UserVO(0, "Mr Chen", STUDENT1_USERNAME, STUDENT1_PSD, 1);
    mockMvc.perform(
            post("/register")
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))


    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.USERNAME_EXISTS.toString()));

    userVO = new UserVO(0, "Mr Chen", STUDENT2_USERNAME, STUDENT2_PSD, 1);
    mockMvc.perform(
            post("/register")
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))


    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));


  }

  @Test
  public void test03() throws Exception {
    UserVO userVO = new UserVO(0, "Mr Chen", TEACHER_USERNAME, TEACHER_PSD, 0);

    mockMvc.perform(
            post("/login")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));
  }

  @Test
  public void test04() throws Exception {
    UserVO userVO = new UserVO(0, "Mr Chen", STUDENT1_USERNAME, STUDENT1_PSD, 1);

    mockMvc.perform(
            post("/login")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));
  }

  @Test
  public void test05() throws Exception {
    UserVO userVO = new UserVO(0, "Mr Chen", STUDENT2_USERNAME, STUDENT2_PSD, 1);

    mockMvc.perform(
            post("/login")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));
  }

  @Test
  public void test06() throws Exception {
    UserVO userVO = new UserVO(0, "Mr Chen", STUDENT1_USERNAME, TEACHER_PSD, 1);

    mockMvc.perform(
            post("/login")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(userVO))
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.LOGIN_ERROR.toString()));
  }

  @Test
  public void test07() throws Exception {
    mockMvc.perform(
            get("/getLessons")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("courseId", "1")

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));
  }

  @Test
  public void test08() throws Exception {
    session.removeAttribute(ConstUtil.TEACHER);

    CourseVO courseVO = new CourseVO();
    mockMvc.perform(
            post("/createCourse")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(courseVO))
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.NOT_LOGIN.toString()));
  }

  @Test
  public void test10() throws Exception {
    test03();

    CourseVO courseVO = new CourseVO(0, "课程1", false, 1, "");
    mockMvc.perform(
            post("/createCourse")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(courseVO))
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    // todo
  }

  @Test
  public void test11() throws Exception {
    test03();

    CourseVO courseVO = new CourseVO(1, "我的课程1", false, 1, "");
    mockMvc.perform(
            post("/updateCourse")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(courseVO))
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    courseVO = new CourseVO(2, "我的课程1", false, 1, "");
    mockMvc.perform(
            post("/updateCourse")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(courseVO))
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.FORBIDDEN.toString()));
  }

  @Test
  public void test12() throws Exception {
    test03();
    mockMvc.perform(
            get("/teacherGetRunningCourses")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));
  }

  @Test
  public void test13() throws Exception {
    test03();
    mockMvc.perform(
            get("/teacherGetFinishedCourses")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));
  }

  @Test
  public void test14() throws Exception {
    test03();

    LessonVO lessonVO = new LessonVO(0, "生化", 1, 1, 123456L, 0L);
    mockMvc.perform(
            post("/createLesson")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(lessonVO))

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    lessonVO = new LessonVO(0, "生化", 2, 1, 123456L, 0L);
    mockMvc.perform(
            post("/createLesson")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(lessonVO))

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.FORBIDDEN.toString()));

    lessonVO = new LessonVO(0, "生化", 1, 1, 123456L, 0L);
    mockMvc.perform(
            post("/createLesson")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(lessonVO))

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    mockMvc.perform(
            get("/getLessons")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new Gson().toJson(lessonVO))
                    .param("courseId", "1")

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));
  }

  @Test
  public void test15() throws Exception {
    mockMvc.perform(
            get("/getTeacherNoteBook")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("lessonId", "1")

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    mockMvc.perform(
            get("/getTeacherNoteBook")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("lessonId", "10000")

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.NOT_FOUND.toString()));
  }

  @Test
  public void test16() throws Exception {
    test03();
    TeacherNoteItemVO itemVO = new TeacherNoteItemVO(0, 1, 123451723L, "BLACK", "", Collections.singletonList(new Point(0, 4.6, 5.7)));

    mockMvc.perform(
            post("/sendTeacherNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "1")
                    .content(new Gson().toJson(itemVO))
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    itemVO = new TeacherNoteItemVO(0, 1, 123451723L, "BLACK", "", new ArrayList<>());

    mockMvc.perform(
            post("/sendTeacherNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "100000")
                    .content(new Gson().toJson(itemVO))
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.FORBIDDEN.toString()));

  }

  @Test
  public void test17() throws Exception {
    test03();
    TeacherNoteItemVO itemVO = new TeacherNoteItemVO(1, 1, 123451723L, "BLACK", "", Collections.emptyList());

    mockMvc.perform(
            post("/updateTeacherNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "1")
                    .content(new Gson().toJson(itemVO))
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    itemVO = new TeacherNoteItemVO(0, 1, 123451723L, "BLACK", "", Collections.emptyList());

    mockMvc.perform(
            post("/updateTeacherNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "100000")
                    .content(new Gson().toJson(itemVO))
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.FORBIDDEN.toString()));

    itemVO = new TeacherNoteItemVO(100000, 1, 123451723L, "BLACK", "", Collections.emptyList());

    mockMvc.perform(
            post("/updateTeacherNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "1")
                    .content(new Gson().toJson(itemVO))
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.NOT_FOUND.toString()));

  }

  @Test
  public void test18() throws Exception {
    test03();

    mockMvc.perform(
            post("/deleteTeacherNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "1")
                    .param("teacherNoteId", "100000")

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    mockMvc.perform(
            post("/deleteTeacherNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "100000")
                    .param("teacherNoteId", "1")

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.FORBIDDEN.toString()));
  }

  @Test
  public void test19() throws Exception {
    test04();
    mockMvc.perform(
            get("/studentGetUnfinishedCourses")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    mockMvc.perform(
            get("/studentGetFinishedCourses")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    mockMvc.perform(
            post("/joinCourse")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("courseId", "1")
    ).andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    mockMvc.perform(
            get("/studentGetOngoingCourses")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    mockMvc.perform(
            get("/searchCourses")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("keyword", "课")
                    .param("index", "0")
                    .param("offset", "5")
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));
  }

  @Test
  public void test20() throws Exception {
    test04();

    mockMvc.perform(
            post("/joinCourse")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("courseId", "1")
    ).andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.FORBIDDEN.toString()));
  }

  @Test
  public void test21() throws Exception {
    test04();

    mockMvc.perform(
            post("/joinLesson")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("lessonId", "1")
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()))
            .andDo(print());
    test03();

    TeacherNoteItemVO itemVO = new TeacherNoteItemVO(0, 1, 123451723L, "BLACK", "", Collections.singletonList(new Point(0, 4.6, 5.7)));

    mockMvc.perform(
            post("/sendTeacherNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "1")
                    .content(new Gson().toJson(itemVO))
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    test04();

    mockMvc.perform(
            post("/joinLesson")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("lessonId", "100000")
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.NOT_FOUND.toString()));
  }

  @Test
  public void test22() throws Exception {
    test04();

    mockMvc.perform(
            get("/getStudentNoteBook")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("lessonId", "1")
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    mockMvc.perform(
            get("/getStudentNoteBook")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("lessonId", "100000")
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.NOT_FOUND.toString()));
  }

  @Test
  public void test23() throws Exception {
    test04();

    StudentNoteItemVO studentNoteItemVO = new StudentNoteItemVO(0, 1, "");

    mockMvc.perform(
            post("/writeStudentNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "1")
                    .content(new Gson().toJson(studentNoteItemVO))

    )
            .andExpect(status().isOk())
    ;

    studentNoteItemVO = new StudentNoteItemVO(0, 1, "");

    mockMvc.perform(
            post("/writeStudentNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "100000")
                    .content(new Gson().toJson(studentNoteItemVO))

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.FORBIDDEN.toString()));
  }

  @Test
  public void test24() throws Exception {
    test04();

    StudentNoteItemVO studentNoteItemVO = new StudentNoteItemVO(1, 1, "");

    mockMvc.perform(
            post("/updateStudentNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "1")
                    .content(new Gson().toJson(studentNoteItemVO))

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    studentNoteItemVO = new StudentNoteItemVO(1, 1, "");

    mockMvc.perform(
            post("/updateStudentNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "100000")
                    .content(new Gson().toJson(studentNoteItemVO))

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.FORBIDDEN.toString()));

    studentNoteItemVO = new StudentNoteItemVO(10000, 1, "");

    mockMvc.perform(
            post("/updateStudentNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "1")
                    .content(new Gson().toJson(studentNoteItemVO))

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.NOT_FOUND.toString()));
  }

  @Test
  public void test25() throws Exception {
    test04();

    mockMvc.perform(
            post("/deleteStudentNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "100000")
                    .param("studentNoteId", "1")


    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.FORBIDDEN.toString()));

    mockMvc.perform(
            post("/deleteStudentNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "1")
                    .param("studentNoteId", "100000")


    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));
  }

  @Test
  public void test26() throws Exception {
    test04();
    mockMvc.perform(
            get("/getSharedNoteBooks")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("lessonId", "100000")
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.NOT_FOUND.toString()));

    mockMvc.perform(
            get("/getSharedNoteBooks")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("lessonId", "1")
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.FORBIDDEN.toString()));
  }

  @Test
  public void test27() throws Exception {
    test04();

    mockMvc.perform(
            post("/cloneNoteBook")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "100000")
                    .param("cloneNoteBookId", "1")

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.FORBIDDEN.toString()));

    mockMvc.perform(
            post("/cloneNoteBook")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "1")
                    .param("cloneNoteBookId", "100000")

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.NOT_FOUND.toString()));

    test05();

    mockMvc.perform(
            post("/joinLesson")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("lessonId", "1")
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    mockMvc.perform(
            post("/joinLesson")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("lessonId", "2")
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    test04();
    mockMvc.perform(
            post("/cloneNoteBook")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "1")
                    .param("cloneNoteBookId", "2")

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.FORBIDDEN.toString()));

    mockMvc.perform(
            post("/cloneNoteBook")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "1")
                    .param("cloneNoteBookId", "3")

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.FORBIDDEN.toString()));


  }

  @Test
  public void test28() throws Exception {
    test03();

    mockMvc.perform(
            post("/endLesson")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("lessonId", "1")

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    mockMvc.perform(
            post("/endLesson")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("lessonId", "100000")

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.FORBIDDEN.toString()));

    test05();
    mockMvc.perform(
            get("/getSharedNoteBooks")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("lessonId", "1")
    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));

    mockMvc.perform(
            post("/cloneNoteBook")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "2")
                    .param("cloneNoteBookId", "1")

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));
  }

  @Test
  public void test29() throws Exception {

    test03();
    mockMvc.perform(
            post("/deleteTeacherNote")
                    .session(session)
                    .characterEncoding(UTF8)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("bookId", "1")
                    .param("teacherNoteId", "1")

    )
            .andExpect(status().isOk())
            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));
  }


  @Test
  public void test30() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders
                    .multipart("/uploadFile")
                    .file(
                            new MockMultipartFile("file", "test.txt", ",multipart/form-data", "hello upload".getBytes(UTF8))
                    )
    ).andExpect(MockMvcResultMatchers.status().isOk());
    File dir = new File(ConstUtil.FILE_ROOT);
    String[] fileNames = dir.list();
    Arrays.asList(fileNames)
            .forEach(name -> new File(ConstUtil.FILE_ROOT + name).delete());
    dir.delete();

//
//    )
//            .andExpect(status().isOk())
//            .andExpect(jsonPath(RESPONSE_CODE).value(HttpCode.SUCCESS.toString()));
  }

}

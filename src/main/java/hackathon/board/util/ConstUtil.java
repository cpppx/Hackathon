package hackathon.board.util;

/**
 * @author cst
 */
public class ConstUtil {
  private ConstUtil() {

  }

  private static final String[] USER_TYPES = {"teacher", "student"};

  public static String[] getAllUserTypes() {
    return USER_TYPES;
  }

  public static final String TEACHER = USER_TYPES[0];
  public static final String STUDENT = USER_TYPES[1];

  public static final String COURSE = "course";
  public static final String LESSON = "lesson";
}

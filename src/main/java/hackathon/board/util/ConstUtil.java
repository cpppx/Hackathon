package hackathon.board.util;

import java.util.Objects;

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

  public static final String URL = "http://106.14.178.184:8080/board/";
  public static final String FILE_URL = "static/";
  public static final String FILE_ROOT = Objects.requireNonNull(ConstUtil.class.getClassLoader().getResource("")).getPath() + FILE_URL;
}

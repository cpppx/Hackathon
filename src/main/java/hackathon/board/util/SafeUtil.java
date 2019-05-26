package hackathon.board.util;

import hackathon.board.exception.NotLoginException;
import hackathon.board.exception.ParamErrorException;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;

/**
 * @author cst
 */
public class SafeUtil {
  private SafeUtil() {

  }

  public static void checkParam(Object object, String... paramNames) {
    try {
      Class objectClass = object.getClass();
      for (String paramName : paramNames) {
        Field field = objectClass.getDeclaredField(paramName);
        field.setAccessible(true);
        if (field.get(object) == null || "".equals(field.get(object))) {
          throw new ParamErrorException(paramName + " 参数错误");
        }
      }
    } catch (Exception e) {
      throw new ParamErrorException(e.getMessage());
    }
  }

  public static int checkLogin(HttpSession session, String type) {
    Integer userId;
    if ((userId = (Integer) session.getAttribute(type)) != null) {
      return userId;
    } else {
      throw new NotLoginException();
    }
  }
}

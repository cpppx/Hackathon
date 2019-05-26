package hackathon.board.controller.response;

/**
 * @author cst
 */
public enum HttpCode {
  /**
   * 成功
   */
  SUCCESS,

  /**
   * 用户名已存在
   */
  USERNAME_EXISTS,

  /**
   * 未登录 401
   */
  NOT_LOGIN,


  /**
   * 用户名或密码错误
   */
  LOGIN_ERROR,

  /**
   * 400
   */
  PARAM_ERROR,

  /**
   * 404
   */
  NOT_FOUND,

  /**
   * 403
   */
  FORBIDDEN,

  /**
   * 失败
   */
  ERROR,
}

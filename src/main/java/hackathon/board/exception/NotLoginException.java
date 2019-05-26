package hackathon.board.exception;

import hackathon.board.controller.response.HttpCode;


/**
 * @author cst
 */
public class NotLoginException extends SelfException {
  public NotLoginException() {
    super(HttpCode.NOT_LOGIN, "未登录");
  }
}

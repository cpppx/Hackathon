package hackathon.board.exception;


import hackathon.board.controller.response.HttpCode;

/**
 * @author cst
 */
public class LoginErrorException extends SelfException {
  public LoginErrorException() {
    super(HttpCode.LOGIN_ERROR, "用户名或密码错误");
  }
}

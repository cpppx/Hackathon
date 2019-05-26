package hackathon.board.exception;

import hackathon.board.controller.response.HttpCode;

/**
 * @author cst
 */
public class UsernameExistsException extends SelfException {
  public UsernameExistsException() {
    super(HttpCode.USERNAME_EXISTS, "用户名已存在");
  }
}

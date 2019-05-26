package hackathon.board.exception;


import hackathon.board.controller.response.HttpCode;

/**
 * @author cst
 */
public class ForbiddenException extends SelfException {
  public ForbiddenException(String message) {
    super(HttpCode.FORBIDDEN, message);
  }
}

package hackathon.board.exception;

import hackathon.board.controller.response.HttpCode;

/**
 * @author cst
 */
public class NotFoundException extends SelfException {
  public NotFoundException(String message) {
    super(HttpCode.NOT_FOUND, message);
  }
}

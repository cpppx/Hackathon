package hackathon.board.exception;

import hackathon.board.controller.response.HttpCode;
import lombok.Getter;

/**
 * @author cst
 */
@Getter
public class SelfException extends RuntimeException {
  private final HttpCode code;
  private final String message;

  public SelfException(HttpCode code, String message) {
    this.code = code;
    this.message = message;
  }
}

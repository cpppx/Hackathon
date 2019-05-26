package hackathon.board.exception;

import hackathon.board.controller.response.HttpCode;


/**
 * @author cst
 */
public class ParamErrorException extends SelfException {
  public ParamErrorException(String message) {
    super(HttpCode.PARAM_ERROR, message);
  }
}

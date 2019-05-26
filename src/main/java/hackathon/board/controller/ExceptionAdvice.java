package hackathon.board.controller;

import hackathon.board.controller.response.HttpCode;
import hackathon.board.controller.response.HttpResponse;
import hackathon.board.exception.SelfException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cst
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
  @ExceptionHandler(SelfException.class)
  public HttpResponse selfExceptionHandle(SelfException e) {
    return new HttpResponse(e.getCode(), e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public HttpResponse exceptionHandle(Exception e) {
    return new HttpResponse(HttpCode.ERROR, e.getMessage());
  }
}

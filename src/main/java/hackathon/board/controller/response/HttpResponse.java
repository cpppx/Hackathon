package hackathon.board.controller.response;

import lombok.Getter;

/**
 * @author cst
 */
@Getter
public class HttpResponse<T> {
  T data;
  HttpCode code;
  String message;

  public HttpResponse(T data) {
    this.data = data;
    this.code = HttpCode.SUCCESS;
    this.message = "";
  }

  public HttpResponse(HttpCode code, String message) {
    this.data = null;
    this.code = code;
    this.message = message;
  }
}

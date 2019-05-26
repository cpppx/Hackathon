package hackathon.board.vo;

import hackathon.board.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author cst
 */
@Getter
@AllArgsConstructor
@ToString
public class UserVO {
  private Integer id;

  private String name;

  private String username;

  private String password;

  /**
   * 0 老师 1 学生
   */
  private Integer type;

  public UserVO(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.type = user.getType();
  }
}

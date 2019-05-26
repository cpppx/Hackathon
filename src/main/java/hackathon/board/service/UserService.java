package hackathon.board.service;

import hackathon.board.vo.UserVO;

/**
 * @author cst
 */
public interface UserService {
  /**
   * 注册
   *
   * @param userVO 用户
   * @return 用户
   */
  UserVO register(UserVO userVO);

  /**
   * 登录
   *
   * @param userVO 用户
   * @return 用户
   */
  UserVO login(UserVO userVO);
}

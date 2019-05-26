package hackathon.board.service.impl;

import hackathon.board.dao.UserDao;
import hackathon.board.entity.User;
import hackathon.board.exception.LoginErrorException;
import hackathon.board.exception.UsernameExistsException;
import hackathon.board.service.UserService;
import hackathon.board.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author cst
 */
@Service
public class UserServiceImpl implements UserService {
  private final UserDao userDao;

  @Autowired
  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public UserVO register(UserVO userVO) {
    if (userDao.existsByUsername(userVO.getUsername())) {
      throw new UsernameExistsException();
    }

    User user = new User();
    user.setName(userVO.getName());
    user.setUsername(userVO.getUsername());
    user.setPassword(DigestUtils.md5DigestAsHex(userVO.getPassword().getBytes()));
    user.setType(userVO.getType());

    return new UserVO(userDao.save(user));
  }

  @Override
  public UserVO login(UserVO userVO) {
    return new UserVO(userDao.findByUsernameAndPassword(userVO.getUsername(), DigestUtils.md5DigestAsHex(userVO.getPassword().getBytes())).orElseThrow(LoginErrorException::new));
  }
}

package hackathon.board.dao;

import hackathon.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author cst
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
  /**
   * 检查是否存在该用户名
   *
   * @param username 用户名
   * @return boolean
   */
  boolean existsByUsername(String username);

  /**
   * 检查是否存在用户名或密码对应的用户
   *
   * @param username 用户名
   * @param password 密码
   * @return 用户
   */
  Optional<User> findByUsernameAndPassword(String username, String password);

  Optional<User> findByIdAndType(int id, int type);
}

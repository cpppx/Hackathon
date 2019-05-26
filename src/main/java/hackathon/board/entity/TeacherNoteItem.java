package hackathon.board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author cst
 */
@Entity
@Getter
@Setter
public class TeacherNoteItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int page;

  Long createTime;
  private String color;

  private String content;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn
  private List<Point> coordinates;
}

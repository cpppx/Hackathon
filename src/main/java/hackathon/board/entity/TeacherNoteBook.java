package hackathon.board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cst
 */
@Entity
@Getter
@Setter
public class TeacherNoteBook {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int teacherId;

  private int lessonId;

  private String color;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn
  private List<TeacherNoteItem> items = new ArrayList<>();
}

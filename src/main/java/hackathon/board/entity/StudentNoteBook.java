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
public class StudentNoteBook {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int lessonId;
  private int studentId;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn
  private List<StudentNoteItem> items = new ArrayList<>();
}

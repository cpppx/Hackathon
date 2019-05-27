package hackathon.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author cst
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Point {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private double x;
  private double y;
}

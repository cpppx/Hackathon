package hackathon.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author cst
 */
@SpringBootApplication
public class BoardApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(BoardApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(BoardApplication.class);
  }
}

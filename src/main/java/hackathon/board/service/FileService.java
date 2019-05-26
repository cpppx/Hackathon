package hackathon.board.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author cst
 */
public interface FileService {
  String saveFile(MultipartFile file) throws IOException;
}

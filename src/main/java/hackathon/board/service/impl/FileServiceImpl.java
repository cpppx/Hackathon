package hackathon.board.service.impl;

import hackathon.board.service.FileService;
import hackathon.board.util.ConstUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author cst
 */
@Service
public class FileServiceImpl implements FileService {
  @Override
  public String saveFile(MultipartFile multipartFile) throws IOException {
    File file = new File(ConstUtil.FILE_ROOT);
    if (!file.exists()) {
      file.mkdirs();
    }
    String suffix = Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(Objects.requireNonNull(multipartFile.getOriginalFilename()).lastIndexOf('.'));
    String fileName = UUID.randomUUID().toString();
    multipartFile.transferTo(new File(ConstUtil.FILE_ROOT + fileName + suffix));
    return ConstUtil.URL + fileName + suffix;
  }
}

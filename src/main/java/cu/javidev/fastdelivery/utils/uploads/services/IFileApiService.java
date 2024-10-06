package cu.javidev.fastdelivery.utils.uploads.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface IFileApiService {

    String saveFile(MultipartFile file);

    Resource loadFile(String filename) ;

    Stream<Path> loadFiles();
}

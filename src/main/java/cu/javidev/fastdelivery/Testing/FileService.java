package cu.javidev.fastdelivery.Testing;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface FileService {

    void saveFile(MultipartFile file) throws Exception;

    Resource loadFile(String filename) throws Exception;

    void saveFiles(List<MultipartFile> files) throws Exception;

    Stream<Path> loadFiles() throws Exception;
}

package cu.javidev.fastdelivery.Testing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class FileServiceImpl implements FileService {

    @Value("${upload.media.folder}")
    private  String UPLOAD_DIR;

    private final Path rootLocation = Paths.get(UPLOAD_DIR);

    @Override
    public void saveFile(MultipartFile file) throws Exception {

        Files.copy(
                file.getInputStream(),
                rootLocation.resolve(Objects.requireNonNull(file.getOriginalFilename()))
        );
    }

    @Override
    public Resource loadFile(String filename) throws Exception {
        return null;
    }

    @Override
    public void saveFiles(List<MultipartFile> files) throws Exception {

    }

    @Override
    public Stream<Path> loadFiles() throws Exception {
        return Stream.empty();
    }
}

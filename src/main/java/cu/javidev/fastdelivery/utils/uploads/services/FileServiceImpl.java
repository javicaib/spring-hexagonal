package cu.javidev.fastdelivery.utils.uploads.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements IFileApiService{

    @Value("${upload.media.folder}")
    String UPLOAD_DIR;


    @Override
    public String saveFile(MultipartFile file) {

        String fileName = UUID.randomUUID() + file.getOriginalFilename();

        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        try {
            Files.write(filePath, file.getBytes());
            return filePath.toString().replace("\\", "/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Resource loadFile(String filename) {
        return null;
    }

    @Override
    public Stream<Path> loadFiles() {
        return Stream.empty();
    }
}

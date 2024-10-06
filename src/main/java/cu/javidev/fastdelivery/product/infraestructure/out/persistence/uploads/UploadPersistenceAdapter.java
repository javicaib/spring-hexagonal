package cu.javidev.fastdelivery.product.infraestructure.out.persistence.uploads;

import cu.javidev.fastdelivery.commons.PersistenceAdapter;
import cu.javidev.fastdelivery.product.application.ports.out.UploadPersistencePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@PersistenceAdapter
public class UploadPersistenceAdapter implements UploadPersistencePort {

    @Value("${upload.media.folder}")
    String UPLOAD_DIR;

    @Override
    public String saveFileToMediaFolder(MultipartFile file) {
        String fileName = UUID.randomUUID() + file.getOriginalFilename();

        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        try {
            Files.write(filePath, file.getBytes());
            return filePath.toString().replace("\\", "/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

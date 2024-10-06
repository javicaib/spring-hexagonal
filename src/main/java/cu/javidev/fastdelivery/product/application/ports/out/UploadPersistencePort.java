package cu.javidev.fastdelivery.product.application.ports.out;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface UploadPersistencePort {
    String saveFileToMediaFolder(MultipartFile file);
}

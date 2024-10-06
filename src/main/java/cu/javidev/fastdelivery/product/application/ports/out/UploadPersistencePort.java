package cu.javidev.fastdelivery.product.application.ports.out;

import org.springframework.web.multipart.MultipartFile;

public interface UploadPersistencePort {
    String saveFile(MultipartFile file);
}

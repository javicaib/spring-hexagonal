package cu.javidev.fastdelivery.product.application.ports.out;

import org.springframework.web.multipart.MultipartFile;

public interface FilePersistencePort {
    String saveFile(MultipartFile file);
}

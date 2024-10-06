package cu.javidev.fastdelivery.utils.uploads;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class UploadConfiguration implements InitializingBean {
    @Value("${upload.media.folder}")
    private String UPLOADED_FOLDER;

    @Override
    public void afterPropertiesSet() throws Exception {
        File uploadDir = new File(UPLOADED_FOLDER);
        if (!uploadDir.exists()) {
            boolean isCreated = uploadDir.mkdir();
            if (!isCreated) {
                throw new Exception("Unable to create folder " + UPLOADED_FOLDER);
            }
        }
    }
}

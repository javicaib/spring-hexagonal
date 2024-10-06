package cu.javidev.fastdelivery.product.domain.models;


import java.util.UUID;

public record Image(
        UUID uuid,
        String path
) {
    public Image(String path) {
        this(UUID.randomUUID(), path);
    }
}

package cu.javidev.fastdelivery.product.infraestructure.out.persistence.postgres.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "images")
public class ImagesEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID uuid;
    String path;

    @PrePersist
    public void prePersist() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}

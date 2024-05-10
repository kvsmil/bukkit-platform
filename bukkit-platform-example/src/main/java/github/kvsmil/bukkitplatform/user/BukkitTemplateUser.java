package github.kvsmil.bukkitplatform.user;

import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public final class BukkitTemplateUser extends Document {
    private String name;
}

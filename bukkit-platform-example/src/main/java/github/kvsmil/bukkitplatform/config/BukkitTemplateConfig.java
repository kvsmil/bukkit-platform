package github.kvsmil.bukkitplatform.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import github.kvsmil.bukkitplatform.annotation.ConfigurationPath;

/* refactored "path" to "file" in configuration path. */
@ConfigurationPath(file = "config.yml", removeOrphans = true)
public final class BukkitTemplateConfig extends OkaeriConfig {

    @Comment("Wysylac dodatkowe informacje do konsoli?")
    public boolean debug = true;

    @Comment({"Ciag laczenia do bazy danych", "Przyklad: jdbc:mysql://localhost:3306/template?user=root&password="})
    public String databaseURI = "jdbc:mysql://localhost:3306/template?user=root&password=";
    public String prefix = "template";

}

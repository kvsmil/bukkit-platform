package github.kvsmil.bukkitutilities;

import lombok.Data;
import lombok.NonNull;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public final class BukkitItemBuilder {

    private final Material material;
    private final ItemStack item;
    private final ItemMeta meta;

    public BukkitItemBuilder(@NonNull Material material) {
        this.material = material;
        this.item = new ItemStack(material);
        this.meta = this.item.getItemMeta();
    }

    public BukkitItemBuilder(@NonNull Material material, int amount) {
        this.material = material;
        this.item = new ItemStack(material, amount);
        this.meta = this.item.getItemMeta();
    }

    public BukkitItemBuilder withLore(@NonNull List<String> lore) {
        this.meta.setLore(lore);
        return this;
    }

    public BukkitItemBuilder withName(@NonNull String name) {
        this.meta.setDisplayName(name);
        return this;
    }

    public BukkitItemBuilder withEnchants(@NonNull Map<Enchantment, Integer> enchants) {
        enchants.forEach((enchant, level) -> this.meta.addEnchant(enchant, level, false));
        return this;
    }

    public BukkitItemBuilder withGlowing(boolean glowing) {
        if(glowing) this.withEnchants(Collections.singletonMap(Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        return this;
    }

    public BukkitItemBuilder withUnbreakable(boolean unbreakable) {
        this.meta.setUnbreakable(unbreakable);
        return this;
    }

    public BukkitItemBuilder withFlags(@NonNull List<ItemFlag> flags) {
        flags.forEach(this.meta::addItemFlags);
        return this;
    }

    public ItemStack build() {
        this.item.setItemMeta(this.meta);
        return this.item;
    }

}

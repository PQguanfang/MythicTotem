package cn.superiormc.mythictotem.managers;

import cn.superiormc.mythictotem.MythicTotem;
import dev.lone.itemsadder.api.CustomBlock;
import io.th0rgal.oraxen.api.OraxenBlocks;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Optional;

public class MaterialManager {

    private String material;

    private Block block;

    public MaterialManager(String material, Block block) {
        this.material = material;
        this.block = block;
    }
    public boolean CheckMaterial(){
        if (MythicTotem.instance.getConfig().getBoolean("settings.debug")) {
            Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicTotem] §aReal block: " + block + ".");
        }
        if (material.equals("none")) {
            return true;
        } else if (material.startsWith("minecraft:")) {
            try {
                return (Material.valueOf(material.split(":")[1].toUpperCase()) == block.getType());
            } catch (IllegalArgumentException | NullPointerException ignored) {
            }
        } else if (material.startsWith("itemsadder:")) {
            try {
                return (material.split(":")[1] + ":" + material.split(":")[2]).equals(CustomBlock.byAlreadyPlaced(block).getNamespacedID());
            } catch (NullPointerException ignored) {
            }
        } else if (material.startsWith("oraxen:")) {
            try {
                if (OraxenBlocks.getNoteBlockMechanic(block).getItemID() == null){
                    return (material.split(":")[1]).equals(OraxenBlocks.getStringMechanic(block).getItemID());
                }
                else if (OraxenBlocks.getStringMechanic(block).getItemID() == null) {
                    return (material.split(":")[1]).equals(OraxenBlocks.getNoteBlockMechanic(block).getItemID());
                }
            } catch (NullPointerException ignored) {
            }
        } else if (material.startsWith("mmoitems:")) {
            Optional<net.Indyuce.mmoitems.api.block.CustomBlock> opt = MMOItems.plugin.getCustomBlocks().
                    getFromBlock(block.getBlockData());
            return opt.filter(customBlock -> customBlock.getId() == Integer.parseInt(material.split(":")[1])).isPresent();
        } else {
            try {
                return (Material.valueOf(material.split(":")[1].toUpperCase()) == block.getType());
            } catch (IllegalArgumentException | NullPointerException ignored) {
            }
        }
        return false;
    }
}

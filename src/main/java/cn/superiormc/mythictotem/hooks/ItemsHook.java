package cn.superiormc.mythictotem.hooks;

import cn.superiormc.mythictotem.MythicTotem;
import cn.superiormc.mythictotem.utils.CheckPluginLoad;
import com.willfp.ecoarmor.sets.ArmorSet;
import com.willfp.ecoarmor.sets.ArmorSets;
import com.willfp.ecoarmor.sets.ArmorSlot;
import com.willfp.ecoitems.items.EcoItem;
import com.willfp.ecoitems.items.EcoItems;
import dev.lone.itemsadder.api.CustomStack;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.th0rgal.oraxen.api.OraxenItems;
import io.th0rgal.oraxen.items.ItemBuilder;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class ItemsHook {
    public static ItemStack getHookItem(String pluginName, String itemID) {
        if (!CheckPluginLoad.DoIt(pluginName)) {
            MythicTotem.checkError("§x§9§8§F§B§9§8[MythicTotem] §cError: Your server don't have " + pluginName +
                    " plugin, but your totem config try use its hook!");
            return null;
        }
        if (pluginName.equals("ItemsAdder")) {
            if (CustomStack.getInstance(itemID) == null) {
                Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicTotemd] §cError: Can not get "
                        + pluginName + " item: " + itemID + "!");
                return null;
            }
            else {
                CustomStack customStack = CustomStack.getInstance(itemID);
                return customStack.getItemStack();
            }
        }
        else if (pluginName.equals("Oraxen")) {
            if (OraxenItems.getItemById(itemID) == null) {
                MythicTotem.checkError("§x§9§8§F§B§9§8[MythicTotem] §cError: Can not get "
                        + pluginName + " item: " + itemID + "!");
                return null;
            }
            else {
                ItemBuilder builder = OraxenItems.getItemById(itemID);
                return builder.build();
            }
        }
        else if (pluginName.equals("MMOItems")) {
            if (MMOItems.plugin.getTypes().get(itemID.split(";;")[0]) == null) {
                MythicTotem.checkError("§x§9§8§F§B§9§8[MythicTotem] §cError: Can not get "
                        + pluginName + " item: " + itemID + "!");
                return null;
            }
            else if (MMOItems.plugin.getItem(itemID.split(";;")[0], itemID.split(";;")[1]) == null){
                MythicTotem.checkError("§x§9§8§F§B§9§8[MythicTotem] §cError: Can not get "
                        + pluginName + " item: " + itemID + "!");
                return null;
            }
            return MMOItems.plugin.getItem(itemID.split(";;")[0], itemID.split(";;")[1]);
        }
        else if (pluginName.equals("EcoItems")) {
            if (EcoItems.getByID(itemID) == null) {
                MythicTotem.checkError("§x§9§8§F§B§9§8[MythicTotem] §cError: Can not get "
                        + pluginName + " item: " + itemID + "!");
                return null;
            }
            else {
                EcoItem ecoItem = EcoItems.getByID(itemID);
                return ecoItem.getItemStack();
            }
        }
        else if (pluginName.equals("EcoArmor")) {
            if (ArmorSets.getByID(itemID.split(";;")[0]) == null) {
                return null;
            }
            ArmorSet armorSet = ArmorSets.getByID(itemID);
            ItemStack itemStack = null;
            if (itemID.split(";;")[1].toUpperCase().equals("BOOTS")) {
                itemStack = armorSet.getItemStack(ArmorSlot.BOOTS);
            } else if (itemID.split(";;")[1].toUpperCase().equals("CHESTPLATE")) {
                itemStack = armorSet.getItemStack(ArmorSlot.CHESTPLATE);
            } else if (itemID.split(";;")[1].toUpperCase().equals("ELYTRA")) {
                itemStack = armorSet.getItemStack(ArmorSlot.ELYTRA);
            } else if (itemID.split(";;")[1].toUpperCase().equals("HELMET")) {
                itemStack = armorSet.getItemStack(ArmorSlot.HELMET);
            } else if (itemID.split(";;")[1].toUpperCase().equals("LEGGINGS")) {
                itemStack = armorSet.getItemStack(ArmorSlot.LEGGINGS);
            }
            if (itemStack == null) {
                MythicTotem.checkError("§x§9§8§F§B§9§8[MythicTotem] §cError: Can not get "
                        + pluginName + " item: " + itemID + "!");
                return null;
            }
            else {
                return itemStack;
            }
        }
        else if (pluginName.equals("MythicMobs")) {
            try {
                if (MythicBukkit.inst().getItemManager().getItemStack(itemID) == null) {
                    MythicTotem.checkError("§x§9§8§F§B§9§8[MythicTotem] §cError: Can not get "
                            + pluginName + " v5 item: " + itemID + "!");
                    return null;
                } else {
                    return MythicBukkit.inst().getItemManager().getItemStack(itemID);
                }
            }
            catch (NoClassDefFoundError ep) {
                if (MythicMobs.inst().getItemManager().getItemStack(itemID) == null) {
                    MythicTotem.checkError("§x§9§8§F§B§9§8[MythicTotem] §cError: Can not get "
                            + pluginName + " v4 item: " + itemID + "!");
                    return null;
                } else {
                    return MythicMobs.inst().getItemManager().getItemStack(itemID);
                }
            }
        }
        MythicTotem.checkError("§x§9§8§F§B§9§8[MythicTotem] §cError: You set hook plugin to "
                + pluginName + " in UI config, however for now MythicTotem does not support it!");
        return null;
    }
}

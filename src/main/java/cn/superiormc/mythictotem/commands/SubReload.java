package cn.superiormc.mythictotem.commands;

import cn.superiormc.mythictotem.MythicTotem;
import cn.superiormc.mythictotem.configs.Messages;
import cn.superiormc.mythictotem.configs.TotemConfigs;
import cn.superiormc.mythictotem.managers.SavedItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class SubReload {

    public static void SubReloadCommand(CommandSender sender)
    {
        if (sender.hasPermission("mythictotem.admin")) {
            MythicTotem.getTotemMaterial.clear();
            MythicTotem.getTotemMap.clear();
            MythicTotem.getCheckingPlayer.clear();
            MythicTotem.getCheckingBlock.clear();
            MythicTotem.threeDtotemAmount = 0;
            MythicTotem.instance.reloadConfig();
            TotemConfigs.initTotemConfigs();
            SavedItemManager.ReadSavedItems();
            if (MythicTotem.freeVersion && MythicTotem.threeDtotemAmount > 3) {
                Bukkit.getConsoleSender().sendMessage("§x§9§8§F§B§9§8[MythicTotem] §cYou are using free version, " +
                        "you can only create up to 3 3D totems with this version. Normal totems don't have this limit!");
            }
            sender.sendMessage(Messages.GetMessages("plugin-reloaded"));
        }
        else {
            sender.sendMessage(Messages.GetMessages("error-miss-permission"));
        }
    }

}

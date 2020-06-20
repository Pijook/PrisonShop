package pl.Pijok.PrisonShop.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        Location location = new Location(Bukkit.getWorld("shop_world"), 0, 80, 0);

        player.teleport(location);

        return true;
    }
}

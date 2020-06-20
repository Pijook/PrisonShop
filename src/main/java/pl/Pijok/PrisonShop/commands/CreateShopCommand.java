package pl.Pijok.PrisonShop.commands;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.Pijok.PrisonShop.shop.Shop;
import pl.Pijok.PrisonShop.utils.ShopUtils;
import pl.Pijok.PrisonShop.utils.Utils;
import tesdev.Money.utils.ChatUtil;

import java.util.HashSet;
import java.util.Set;

public class CreateShopCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if(!player.hasPermission("prison.shop.create")){
            ChatUtil.sendMessage(player, "&cNie masz dostepu do tej komendy!");
            return true;
        }

        if(args.length != 1){
            ChatUtil.sendMessage(player, "&7/" + label + " <cena>");
            ChatUtil.sendMessage(player, "&7/" + label + " save");
            return true;
        }

        if(args[0].equalsIgnoreCase("save")){
            ShopUtils.sync();
            ChatUtil.sendMessage(player, "&7Zapisano!");
            return true;
        }

        if(Utils.isInteger(args[0])){
            ChatUtil.sendMessage(player, "&cCena musi byc liczba calkowita!");
            return true;
        }

        int price = Integer.parseInt(args[0]);

        Block block = player.getTargetBlock((Set<Material>) null, 5);

        if(!block.getType().equals(Material.WALL_SIGN)){
            System.out.println(block);
            ChatUtil.sendMessage(player, "&cMusisz patrzec na tabliczke!");
            return true;
        }

        ItemStack itemStack = player.getItemInHand();

        if(itemStack.getType().equals(Material.AIR) || itemStack.getType() == null){
            ChatUtil.sendMessage(player, "&cMusisz trzymac przedmiot w rece!");
            return true;
        }

        Shop.shops.put(block.getLocation(), new Shop(price, itemStack.getType(), itemStack.getAmount()));
        ChatUtil.sendMessage(player, "&a&lDodano sklep!");

        return true;
    }
}

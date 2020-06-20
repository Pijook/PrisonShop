package pl.Pijok.PrisonShop.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.Pijok.PrisonShop.shop.Shop;
import pl.Pijok.PrisonShop.utils.ShopUtils;
import tesdev.Money.api.EconomyProfile;
import tesdev.Money.utils.ChatUtil;

public class InteractListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if(!player.getWorld().getName().equalsIgnoreCase("shop_world")){
            return;
        }

        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            return;
        }

        Block block = event.getClickedBlock();

        if(!ShopUtils.isBlockShop(block)){
            return;
        }

        Shop shop = Shop.shops.get(block.getLocation());

        if(EconomyProfile.FastAccess.getMoney(player) < shop.getPrice()){
            ChatUtil.sendMessage(player, "&cNie masz wystarczajacej ilosci pieniedzy!");
            return;
        }

        ShopUtils.completeTransaction(shop, player);
        
    }
}

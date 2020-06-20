package pl.Pijok.PrisonShop.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.Pijok.PrisonShop.Main;
import pl.Pijok.PrisonShop.shop.Shop;
import tesdev.Money.api.EconomyProfile;
import tesdev.Money.utils.ChatUtil;

import java.io.File;
import java.io.IOException;

public class ShopUtils {

    public static Boolean isBlockShop(Block block){
        return Shop.shops.containsKey(block.getLocation());
    }

    public static void addShop(Block block, int price, Material material, int amount){
        Shop.shops.put(block.getLocation(), new Shop(price, material, amount));
    }

    public static void removeShop(Block block){
        Shop.shops.remove(block.getLocation());
    }

    public static Shop getShop(Block block){
        return Shop.shops.get(block.getLocation());
    }

    public static void completeTransaction(Shop shop, Player player){
        EconomyProfile.FastAccess.takeMoney(player, shop.getPrice());
        ItemStack itemStack = new ItemStack(shop.getMaterial(), shop.getAmount());
        player.getInventory().addItem(itemStack);
        ChatUtil.sendMessage(player, "&6Kupiles/as &7" + itemStack.getType() + " x" + itemStack.getAmount() + " &6za " + shop.getPrice());
    }

    public static void load(){
        File file = new File(Main.getInstance().getDataFolder(), "shops.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        int last_id = configuration.getInt("last_id");
        if(!configuration.contains("shop")){
            return;
        }
        for(int i = 0; i < last_id; i++){
            Material material = Material.getMaterial(configuration.getInt("shop." + i + ".material"));
            int amount = configuration.getInt("shop." + i + ".amount");
            int price = configuration.getInt("shop." + i + ".price");
            int x = configuration.getInt("shop." + i + ".x");
            int y = configuration.getInt("shop." + i + ".y");
            int z = configuration.getInt("shop." + i + ".z");
            String world = configuration.getString("shop." + i + ".world");

            Location location = new Location(Bukkit.getWorld(world), x, y, z);
            Block block = location.getBlock();

            if(block.getType().equals(Material.WALL_SIGN)){
                addShop(block, price, material, amount);
            }
        }
    }

    public static void sync(){
        File file = new File(Main.getInstance().getDataFolder(), "shops.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        int last_id = 0;
        for(Location location : Shop.shops.keySet()){
            Shop shop = Shop.shops.get(location);

            configuration.set("shop." + last_id + ".material", shop.getMaterial().getId());
            configuration.set("shop." + last_id + ".amount", shop.getAmount());
            configuration.set("shop." + last_id + ".price", shop.getPrice());
            configuration.set("shop." + last_id + ".x", location.getX());
            configuration.set("shop." + last_id + ".y", location.getY());
            configuration.set("shop." + last_id + ".z", location.getZ());
            configuration.set("shop." + last_id + ".world", location.getWorld().getName());

            last_id++;
        }

        configuration.set("last_id", last_id);

        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

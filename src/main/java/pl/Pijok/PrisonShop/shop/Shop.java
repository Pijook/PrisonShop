package pl.Pijok.PrisonShop.shop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Shop {

    public static HashMap<Location, Shop> shops = new HashMap<Location, Shop>();

    private int price;
    private Material material;
    private int amount;

    public Shop(int price, Material material, int amount){
        this.price = price;
        this.material = material;
        this.amount = amount;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public int getPrice(){ return price; }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

package pl.Pijok.PrisonShop;

import org.bukkit.plugin.java.JavaPlugin;
import pl.Pijok.PrisonShop.commands.CreateShopCommand;
import pl.Pijok.PrisonShop.commands.ShopCommand;
import pl.Pijok.PrisonShop.listeners.InteractListener;
import pl.Pijok.PrisonShop.utils.ShopUtils;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("pshop").setExecutor(new CreateShopCommand());
        getCommand("shop").setExecutor(new ShopCommand());

        getServer().getPluginManager().registerEvents(new InteractListener(), this);

        ShopUtils.load();
    }

    @Override
    public void onDisable() {

        ShopUtils.sync();

    }
    public static Main getInstance() {
        return instance;
    }

}

package com.KYRLA_Ktiw;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class MyAdvancedCommands extends JavaPlugin implements CommandExecutor, TabCompleter {

    @Override
    public void onEnable() {
        getLogger().info("Плагин MyAdvancedCommands включился!");
        // Регистрируем нашу команду "mycommand"
        getCommand("mycommand").setExecutor(this);
        getCommand("mycommand").setTabCompleter(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Плагин MyAdvancedCommands выключился!");
    }

    @Override
    // если игрок вводит /mycommand hello world то будет args[0] (hello) args[1] (world)
    // если игрок вводит просто /mycommand то args будет пустым массивом
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // проверка что именно игрок ввел команду
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("mycommand")) {
                player.sendMessage("§aПривет! Ты ввел команду mycommand.");
                // если агумент пустой
                if (args.length > 0) {
                    //получаем первый аргумент
                    String FirstArg = args[0];
                    // здесь вводим при каких условиях будет выводится то или иное сообщение
                    if (FirstArg.equalsIgnoreCase("hello")) {
                        player.sendMessage(ChatColor.GREEN + "Привет, " + player.getName() + "! Ты ввел аргумент 'hello'.");
                    } else if (FirstArg.equalsIgnoreCase("info")) {
                        player.sendMessage(ChatColor.AQUA + "Это плагин MyAdnancedCommands, версия" + getDescription().getVersion() + "!");
                    } else {
                        player.sendMessage(ChatColor.RED + "Пожалуйста введите аргумент!\n например: /mycommand hello");
                    }
                    return true;
                }
            } else {
                // это для консоли
                sender.sendMessage("§bЭта команда предназначена только для игроков, но ты можешь использовать ее без аргументов!");
            }

        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("mycommand")) {
            List<String> comletions = new ArrayList<>();
            // если вводит первый агрумент
            if (args.length == 1) {
                // Мы проверяем начинается ли наше предложение с того что пользователь уже написал
                // tolowercase делает тоже самое что и equalsIgnoreCase
                // startsWith проверяет начинается ли одна строка тип проверяет то ли я написал и надо ли идти дальше
                if ("hello".startsWith(args[0].toLowerCase())) {
                    comletions.add("hello");
                }
                if ("info".startsWith(args[0].toLowerCase())) {
                    comletions.add("info");
                }
                // если вводит второй аргумент
//             if (args.length == 2)
            }
            return comletions;
        }
        return null;
    }
}






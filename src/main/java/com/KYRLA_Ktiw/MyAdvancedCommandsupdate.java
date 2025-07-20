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
                // проверяем есть ли аргументы после команды
                if (args.length > 0) {
                    //получаем первый аргумент
                    String firstArg = args[0];
                    if (firstArg.equalsIgnoreCase("hello")) {
                        if (args.length > 1) {
                            String nameToGreet = args[1];
                            player.sendMessage(ChatColor.GREEN + "Привет," + nameToGreet + "! Рад тебя видеть!");
                        } else {
                            player.sendMessage(ChatColor.GREEN + "Привет," + player.getName() + "! Ты ввел аргумент 'hello'.");
                        }
                    } else if (firstArg.equalsIgnoreCase("info")) {
                        player.sendMessage(ChatColor.AQUA + "это плагин MyAdvancedCommands версия " + getDescription().getVersion() + "!");
                        // новая команда проверяем есть ли что писать после say
                    } else if (firstArg.equalsIgnoreCase("say")) {
                        if (args.length > 1) {
                            StringBuilder messageBuilder = new StringBuilder(); // для обьеденения строк
                            for (int i = 1; i < args.length; i++) {
                                messageBuilder.append(args[i]).append(" ");
                            } // Преобразуем StringBuilder в стринг и убираем лишний пробел в конце
                            String fullMessage = messageBuilder.toString().trim();
                            player.sendMessage(ChatColor.LIGHT_PURPLE + "[Эхо] " + fullMessage);
                        } else {
                            player.sendMessage(ChatColor.RED + "Что сказать? используйте: " + ChatColor.WHITE + "mycommand say <сообщение>");
                        }
                    } else {
                        player.sendMessage(ChatColor.YELLOW + "Неизвестный аргумент: " + firstArg + ". Попробуй 'hello' 'info' или 'say' ");
                    }
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Пожалуйста, введите аргумент! например " + ChatColor.WHITE + "/mycommand hello");
                }
            }
        } else {
            sender.sendMessage(ChatColor.AQUA + "Эта команда предназначена только для игроков.");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // Проверяем, что это наша команда "mycommand".
        // Эта проверка должна быть только один раз в начале метода.
        if (command.getName().equalsIgnoreCase("mycommand")) {
            List<String> completions = new ArrayList<>(); // Используй "completions" вместо "comletions" для единообразия и правильности

            // Если пользователь вводит первый аргумент (например, /mycommand <arg1>)
            if (args.length == 1) {
                // Предлагаем "hello", "info", "say"
                if ("hello".startsWith(args[0].toLowerCase())) {
                    completions.add("hello");
                }
                if ("info".startsWith(args[0].toLowerCase())) {
                    completions.add("info");
                }
                if ("say".startsWith(args[0].toLowerCase())) {
                    completions.add("say");
                }
            } else if (args.length == 2) { // Если пользователь вводит второй аргумент (например, /mycommand hello <arg2>)
                if (args[0].equalsIgnoreCase("hello")) {
                    completions.add("<ник_игрока>");
                } else if (args[0].equalsIgnoreCase("say")) {
                    completions.add("<сообщение>");
                }
            }
            // Возвращаем список предложений. Если список пустой, автодополнение не будет предложено.
            return completions;
        }
        // Если команда не "mycommand", или если нет предложений от этого плагина для этой команды,
        // возвращаем null, чтобы Minecraft мог использовать свои стандартные автодополнения.
        return null;
    }
}







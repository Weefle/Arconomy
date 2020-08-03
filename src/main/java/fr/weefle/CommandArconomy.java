package fr.weefle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandArconomy implements CommandExecutor {

    private Arconomy m;
    public CommandArconomy(Arconomy m){
        this.m = m;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length < 1) {
                sender.sendMessage(ChatColor.RED + "Use \"/arconomy help\" for command reference.");
                return true;
            }

            if (args[0].equalsIgnoreCase("add")) {

                if (args.length == 3) {

                    Player pl = Bukkit.getPlayer(args[1]);
                    int moneyToAdd = Integer.parseInt(args[2]);
                    int money = (int) m.getConfig().get("Players." + pl.getName());
                    money += moneyToAdd;
                    m.getConfig().set("Players." + pl.getName(), money);
                    m.saveConfig();
                    p.sendMessage(ChatColor.GREEN + "Successfully added " + ChatColor.WHITE + moneyToAdd + ChatColor.GREEN + " to balance of " + ChatColor.WHITE + pl.getName());
                }else {
                    p.sendMessage(ChatColor.RED + "Too less arguments! Use this command: /arconomy add <player> <money>");
                    return true;
                }
            }else if(args[0].equalsIgnoreCase("get")){
                if (args.length == 2) {
                    Player pl = Bukkit.getPlayer(args[1]);
                    int money = (int) m.getConfig().get("Players." + pl.getName());
                p.sendMessage(ChatColor.GREEN + "There is " + ChatColor.WHITE + money + ChatColor.GREEN + " in balance of " + ChatColor.WHITE + pl.getName());
            }else {
                p.sendMessage(ChatColor.RED + "Too less arguments! Use this command: /arconomy get <player>");
                return true;
            }
            }else if (args[0].equalsIgnoreCase("remove")) {

                if (args.length == 3) {

                    Player pl = Bukkit.getPlayer(args[1]);
                    int moneyToRemove = Integer.parseInt(args[2]);
                    int money = (int) m.getConfig().get("Players." + pl.getName());
                    money -= moneyToRemove;
                    m.getConfig().set("Players." + pl.getName(), money);
                    m.saveConfig();
                    p.sendMessage(ChatColor.GREEN + "Successfully removed " + ChatColor.WHITE + moneyToRemove + ChatColor.GREEN + " to balance of " + ChatColor.WHITE + pl.getName());
                }else {
                    p.sendMessage(ChatColor.RED + "Too less arguments! Use this command: /arconomy remove <player> <money>");
                    return true;
                }
            }else if (args[0].equalsIgnoreCase("set")) {

                if (args.length == 3) {

                    Player pl = Bukkit.getPlayer(args[1]);
                    int money = Integer.parseInt(args[2]);
                    m.getConfig().set("Players." + pl.getName(), money);
                    m.saveConfig();
                    p.sendMessage(ChatColor.GREEN + "Successfully setted " + ChatColor.WHITE + money + ChatColor.GREEN + " to balance of " + ChatColor.WHITE + pl.getName());
                }else {
                    p.sendMessage(ChatColor.RED + "Too less arguments! Use this command: /arconomy set <player> <money>");
                    return true;
                }
            }

        }
        return true;

    }
}

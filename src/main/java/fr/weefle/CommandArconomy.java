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
                sender.sendMessage(ChatColor.RED + "Utilisez \"/arconomy help\" pour afficher l'aide d'Arconomy");
                return true;
            }

            if (args[0].equalsIgnoreCase("help")) {

                p.sendMessage("");
                p.sendMessage(ChatColor.GOLD + "------- Commandes d'Arconomy -------");
                p.sendMessage("");
                p.sendMessage(ChatColor.BLUE + "/arconomy get <player>");
                p.sendMessage(ChatColor.AQUA + "  Récupérer le solde d'un joueur");
                p.sendMessage(ChatColor.BLUE + "/arconomy set <player> <money>");
                p.sendMessage(ChatColor.AQUA + "  Définir le solde d'un joueur");
                p.sendMessage(ChatColor.BLUE + "/arconomy add <player> <money>");
                p.sendMessage(ChatColor.AQUA + "  Ajouter de la monnaie au solde d'un joueur");
                p.sendMessage(ChatColor.BLUE + "/arconomy remove <player> <money>");
                p.sendMessage(ChatColor.AQUA + "  Retirer de la monnaie du solde d'un joueur");
                p.sendMessage("");
                return true;

            }else if (args[0].equalsIgnoreCase("add")) {

                if (args.length == 3) {

                    Player pl = Bukkit.getPlayer(args[1]);
                    double moneyToAdd = Double.parseDouble(args[2]);
                    double money = (double) m.getConfig().get("Players." + pl.getName());
                    money += moneyToAdd;
                    m.getConfig().set("Players." + pl.getName(), money);
                    m.saveConfig();
                    p.sendMessage(ChatColor.GREEN + "Correctement ajouté " + ChatColor.WHITE + moneyToAdd + ChatColor.GREEN + " au solde de " + ChatColor.WHITE + pl.getName());
                }else {
                    p.sendMessage(ChatColor.RED + "Pas assez d'arguments! Utilisez cette commande: /arconomy add <player> <money>");
                    return true;
                }
            }else if(args[0].equalsIgnoreCase("get")){
                if (args.length == 2) {
                    Player pl = Bukkit.getPlayer(args[1]);
                    double money = (double) m.getConfig().get("Players." + pl.getName());
                p.sendMessage(ChatColor.GREEN + "Il y a " + ChatColor.WHITE + money + ChatColor.GREEN + " dans le solde de " + ChatColor.WHITE + pl.getName());
            }else {
                p.sendMessage(ChatColor.RED + "Pas assez d'arguments! Utilisez cette commande: /arconomy get <player>");
                return true;
            }
            }else if (args[0].equalsIgnoreCase("remove")) {

                if (args.length == 3) {

                    Player pl = Bukkit.getPlayer(args[1]);
                    double moneyToRemove = Double.parseDouble(args[2]);
                    double money = (double) m.getConfig().get("Players." + pl.getName());
                    money -= moneyToRemove;
                    m.getConfig().set("Players." + pl.getName(), money);
                    m.saveConfig();
                    p.sendMessage(ChatColor.GREEN + "Correctement retiré " + ChatColor.WHITE + moneyToRemove + ChatColor.GREEN + " du solde de " + ChatColor.WHITE + pl.getName());
                }else {
                    p.sendMessage(ChatColor.RED + "Pas assez d'arguments! Utilisez cette commande: /arconomy remove <player> <money>");
                    return true;
                }
            }else if (args[0].equalsIgnoreCase("set")) {

                if (args.length == 3) {

                    Player pl = Bukkit.getPlayer(args[1]);
                    double money = Double.parseDouble(args[2]);
                    m.getConfig().set("Players." + pl.getName(), money);
                    m.saveConfig();
                    p.sendMessage(ChatColor.GREEN + "Correctement défini " + ChatColor.WHITE + money + ChatColor.GREEN + " pour le solde de " + ChatColor.WHITE + pl.getName());
                }else {
                    p.sendMessage(ChatColor.RED + "Pas assez d'arguments! Utilisez cette commande: /arconomy set <player> <money>");
                    return true;
                }
            }

        }
        return true;

    }
}

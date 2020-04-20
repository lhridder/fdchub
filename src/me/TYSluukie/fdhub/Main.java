package me.TYSluukie.fdhub;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Main extends JavaPlugin implements Listener, PluginMessageListener {

	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}
	@Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
            if (!channel.equals("BungeeCord")) return;
           
            try {
                    DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
                    String command = in.readUTF();
                   
                    if (command.equals("PlayerCount")) {
                            String server = in.readUTF();
                            int playerCount = in.readInt();
                           
							Bukkit.getServer()
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }
   
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
            if (cmd.getName().equalsIgnoreCase("bungeecount")) {
                    String server = "ALL";
                   
                    if (args.length > 0) {
                            server = args[0];
                    }
                   
                    try {
                            ByteArrayOutputStream b = new ByteArrayOutputStream();
                            DataOutputStream out = new DataOutputStream(b);

                            out.writeUTF("PlayerCount");
                            out.writeUTF(server);
                            Bukkit.getServer().sendPluginMessage(this, "BungeeCord", b.toByteArray());
                    } catch (Exception e) {
                            e.printStackTrace();
                    }
            }
           
            return true;
    }
}

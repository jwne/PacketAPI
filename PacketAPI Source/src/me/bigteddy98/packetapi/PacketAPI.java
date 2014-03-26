package me.bigteddy98.packetapi;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import me.bigteddy98.packetapi.api.Cancellable;
import me.bigteddy98.packetapi.api.CustomPlayerConnection;
import me.bigteddy98.packetapi.api.PacketHandler;
import me.bigteddy98.packetapi.api.PacketSendEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class PacketAPI extends JavaPlugin implements Listener {

	private static PacketAPI plugin;
	private List<PacketListener> packetListeners = new ArrayList<PacketListener>();

	@Override
	public void onEnable() {

		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			// Failed to submit the stats :-(
		}

		plugin = this;
		this.getServer().getPluginManager().registerEvents(this, this);

		for (Player p : this.getServer().getOnlinePlayers()) {
			this.setConnection(p);
		}
	}

	public void addListener(PacketListener listener) {
		this.packetListeners.add(listener);
	}

	public static PacketAPI getInstance() {
		return plugin;
	}

	public void packetSend(PacketWrapper packet, Cancellable cancel, String recieverName) {
		for (PacketListener listener : this.packetListeners) {
			for (Method method : listener.getClass().getMethods()) {
				PacketHandler ann = method.getAnnotation(PacketHandler.class);
				if (ann != null) {
					try {
						method.setAccessible(true);
						method.invoke(listener, new PacketSendEvent(packet, cancel, recieverName));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		this.setConnection(p);
	}

	private void setConnection(Player p) {
		try {
			((org.bukkit.craftbukkit.v1_7_R2.entity.CraftPlayer) p).getHandle().playerConnection = new CustomPlayerConnection(((org.bukkit.craftbukkit.v1_7_R2.entity.CraftPlayer) p).getHandle());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

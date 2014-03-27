package me.bigteddy98.packetapi;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import me.bigteddy98.packetapi.api.Cancellable;
import me.bigteddy98.packetapi.api.PacketHandler;
import me.bigteddy98.packetapi.api.PacketListener;
import me.bigteddy98.packetapi.api.PacketSendEvent;

import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class PacketAPI extends JavaPlugin {

	private static PacketAPI plugin;
	private Map<PacketListener, List<Method>> packetListeners = new HashMap<PacketListener, List<Method>>();
	private ProtocolManager manager;

	@Override
	public void onEnable() {

		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			// Failed to submit the stats :-(
		}

		plugin = this;
		manager = new ProtocolManager(this);
	}
	
	@Override
	public void onDisable() {
		this.manager.disable();
	}

	public void addListener(PacketListener listener) {
		List<Method> methods = new ArrayList<Method>();
		for (Method method : listener.getClass().getMethods()) {
			PacketHandler ann = method.getAnnotation(PacketHandler.class);
			if (ann != null) {
				try {
					methods.add(method);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		this.packetListeners.put(listener, methods);
	}

	public static PacketAPI getInstance() {
		return plugin;
	}

	public void packetSend(PacketWrapper packet, Cancellable cancel, String recieverName) {
		for (Entry<PacketListener, List<Method>> listener : this.packetListeners.entrySet()) {
			for (Method method : listener.getValue()) {
				method.setAccessible(true);
				try {
					method.invoke(listener.getKey(), new PacketSendEvent(packet, cancel, recieverName));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

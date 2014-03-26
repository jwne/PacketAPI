package me.bigteddy98.packetapi;

import me.bigteddy98.packetapi.api.PacketHandler;
import me.bigteddy98.packetapi.api.PacketSendEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin implements PacketListener {

	@Override
	public void onEnable() {
		PacketAPI.getInstance().addListener(this);
	}

	@PacketHandler
	public void onSend(PacketSendEvent event) {
		if (event.getPacketName().equals("PacketPlayOutAnimation")) {
			event.setCancelled(true);
		}
	}
}

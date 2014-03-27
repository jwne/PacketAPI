package me.bigteddy98.packetapi;

import me.bigteddy98.packetapi.api.PacketHandler;
import me.bigteddy98.packetapi.api.PacketListener;
import me.bigteddy98.packetapi.api.PacketSendEvent;

import org.bukkit.plugin.java.JavaPlugin;

//implement PacketListener, to specify that this class listens for packets
public class ExamplePlugin extends JavaPlugin implements PacketListener {

	@Override
	public void onEnable() {
		//register the current class as a listener
		PacketAPI.getInstance().addListener(this);
	}

	//add annotation for handling packets (won't work without)
	@PacketHandler
	//packet send event, called for every packet send to a player
	public void onSend(PacketSendEvent event) {
		//check if the package name equals PacketPlayOutAnimation
		if (event.getPacketName().equals("PacketPlayOutAnimation")) {
			//cancel the sending
			event.setCancelled(true);
		}
	}
}

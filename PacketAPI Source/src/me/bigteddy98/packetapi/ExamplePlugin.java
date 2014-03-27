package me.bigteddy98.packetapi;

import me.bigteddy98.packetapi.api.PacketHandler;
import me.bigteddy98.packetapi.api.PacketListener;
import me.bigteddy98.packetapi.api.PacketRecieveEvent;
import me.bigteddy98.packetapi.api.PacketSendEvent;

public class ExamplePlugin implements PacketListener {

	public ExamplePlugin(PacketAPI packetAPI) {
		PacketAPI.getInstance().addListener(this);
	}

	

	@PacketHandler
	public void onRecieve(PacketRecieveEvent event) {
		System.out.println("in: " + event.getPacketName());
	}
	
	@PacketHandler
	public void onSend(PacketSendEvent event) {
		System.out.println("out: " + event.getPacketName());
	}
}

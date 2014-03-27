package me.bigteddy98.packetapi;

import me.bigteddy98.packetapi.api.PacketHandler;
import me.bigteddy98.packetapi.api.PacketListener;
import me.bigteddy98.packetapi.api.PacketRecieveEvent;
import me.bigteddy98.packetapi.api.PacketSendEvent;
import me.bigteddy98.packetapi.api.PacketType;

public class ExamplePlugin implements PacketListener {

	public ExamplePlugin(PacketAPI packetAPI) {
		PacketAPI.getInstance().addListener(this);
	}

	@PacketHandler(listenType = PacketType.PacketPlayInCloseWindow)
	public void onRecieve(PacketRecieveEvent event) {
		System.out.println("in: " + event.getPacketName());
	}

	@PacketHandler(listenType = PacketType.PacketPlayOutEntityHeadRotation)
	public void onSend(PacketSendEvent event) {
		System.out.println("out: " + event.getPacketName());
	}
}

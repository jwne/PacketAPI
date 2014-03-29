package me.bigteddy98.packetapi;

import me.bigteddy98.packetapi.api.PacketHandler;
import me.bigteddy98.packetapi.api.PacketListener;
import me.bigteddy98.packetapi.api.PacketRecieveEvent;
import me.bigteddy98.packetapi.api.PacketSendEvent;
import me.bigteddy98.packetapi.api.PacketType;
import net.minecraft.server.v1_7_R2.ServerPing;
import net.minecraft.server.v1_7_R2.ServerPingPlayerSample;

public class ExamplePlugin implements PacketListener {

	public ExamplePlugin(PacketAPI plugin) {
		PacketAPI.getInstance().addListener(this);
	}

	@PacketHandler(listenType = PacketType.PacketPlayInArmAnimation)
	public void onRecieve(PacketRecieveEvent event) {
		System.out.println("in: " + event.getPacket().getName());
		event.setCancelled(true);
	}

	@PacketHandler(listenType = PacketType.PacketStatusOutServerInfo)
	public void onSend(PacketSendEvent event) {
		System.out.println("out: " + event.getPacket().getName());

		ServerPing currentPing = (ServerPing) event.getPacket().getValue("b");
		currentPing.setPlayerSample(new ServerPingPlayerSample(903, 101));

		event.getPacket().setValue("b", currentPing);
	}
}

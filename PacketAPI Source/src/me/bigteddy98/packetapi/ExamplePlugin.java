package me.bigteddy98.packetapi;

import java.lang.reflect.Method;

import me.bigteddy98.packetapi.api.PacketHandler;
import me.bigteddy98.packetapi.api.PacketListener;
import me.bigteddy98.packetapi.api.PacketRecieveEvent;
import me.bigteddy98.packetapi.api.PacketSendEvent;
import me.bigteddy98.packetapi.api.PacketType;
import me.bigteddy98.packetapi.api.Reflection;

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
	public void onSend(PacketSendEvent event) throws Exception {
		Method setSample = Reflection.getMCClass("ServerPing").getMethod("setPlayerSample", Reflection.getMCClass("ServerPingPlayerSample"));
		Object currentPing = event.getPacket().getValue("b");
		setSample.invoke(currentPing, PacketDataWrapper.ServerPingPlayerSample(1002, 1021));
		event.getPacket().setValue("b", currentPing);
	}
}

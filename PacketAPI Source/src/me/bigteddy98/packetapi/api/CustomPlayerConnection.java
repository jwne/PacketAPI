package me.bigteddy98.packetapi.api;

import me.bigteddy98.packetapi.PacketAPI;
import me.bigteddy98.packetapi.PacketWrapper;
import net.minecraft.server.v1_7_R2.EntityPlayer;
import net.minecraft.server.v1_7_R2.MinecraftServer;
import net.minecraft.server.v1_7_R2.NetworkManager;
import net.minecraft.server.v1_7_R2.Packet;
import net.minecraft.server.v1_7_R2.PlayerConnection;

public class CustomPlayerConnection extends PlayerConnection {

	public CustomPlayerConnection(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
		super(minecraftserver, networkmanager, entityplayer);
	}

	public CustomPlayerConnection(EntityPlayer pl) throws IllegalArgumentException, IllegalAccessException {
		super(pl.server, pl.playerConnection.networkManager, pl);
	}

	@Override
	public void sendPacket(Packet packet) {
		Cancellable cancel = new Cancellable();
		PacketAPI.getInstance().packetSend(new PacketWrapper(packet), cancel, this.getPlayer().getName());
		if (cancel.isCancelled()) {
			return;
		}
		super.sendPacket(packet);
	}

}

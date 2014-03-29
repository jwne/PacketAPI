package me.bigteddy98.packetapi;

public class PacketDataWrapper {
	
	public static Object ServerPingPlayerSample(int players, int maxPlayers) {
		return new net.minecraft.server.v1_7_R2.ServerPingPlayerSample(players, maxPlayers);
	}
	
	
	
}

package me.bigteddy98.packetapi;

import java.lang.reflect.Field;

import net.minecraft.server.v1_7_R2.Packet;

import org.bukkit.craftbukkit.v1_7_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketWrapper {

	private net.minecraft.server.v1_7_R2.Packet packet;

	public PacketWrapper(Packet packet) {
		this.packet = packet;
	}

	public void setValue(String fieldName, String value) {
		try {
			Field f = this.packet.getClass().getDeclaredField(fieldName);
			f.set(this.packet, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getValue(String fieldName) {
		try {
			Field f = this.packet.getClass().getDeclaredField(fieldName);
			f.get(this.packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void send(Player p) {
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(this.packet);
	}

	public net.minecraft.server.v1_7_R2.Packet getNMSPacket() {
		return this.packet;
	}
}

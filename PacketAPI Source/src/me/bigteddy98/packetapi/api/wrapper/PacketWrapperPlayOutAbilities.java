package me.bigteddy98.packetapi.api.wrapper;

import me.bigteddy98.packetapi.PacketWrapper;
import me.bigteddy98.packetapi.api.Reflection;
import net.minecraft.server.v1_7_R2.PacketPlayOutAbilities;

public class PacketWrapperPlayOutAbilities extends PacketWrapper {

	private PacketPlayOutAbilities nms_packet;

	public PacketWrapperPlayOutAbilities(Object packet) {
		super(packet);
		nms_packet = (PacketPlayOutAbilities) packet;
	}

	public boolean isInvulnerable() {
		return nms_packet.a();
	}

	public void setInvulnerable(boolean invulnerable) {
		nms_packet.a(invulnerable);
	}

	public boolean isFlying() {
		return nms_packet.d();
	}

	public void setFlying(boolean flying) {
		nms_packet.b(flying);
	}

	public boolean canFly() {
		return nms_packet.e();
	}

	public void setCanFly(boolean fly) {
		nms_packet.c(fly);
	}

	public boolean canInstantlyBuild() {
		return nms_packet.f();
	}

	public void setCanInstantlyBuild(boolean instantlyBuild) {
		nms_packet.d(instantlyBuild);
	}

	public float getFlySpeed() {
		return nms_packet.g();
	}

	public void setFlySpeed(float speed) {
		nms_packet.a(speed);
	}

	public float getWalkSpeed() {
		return nms_packet.h();
	}

	public void setWalkSpeed(float speed) {
		nms_packet.b(speed);
	}
}

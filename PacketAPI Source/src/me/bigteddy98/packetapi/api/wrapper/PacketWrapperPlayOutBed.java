package me.bigteddy98.packetapi.api.wrapper;

import me.bigteddy98.packetapi.PacketWrapper;

public class PacketWrapperPlayOutBed extends PacketWrapper {

	public PacketWrapperPlayOutBed(Object packet) {
		super(packet);
	}

	public int getEntityId() {
		return (Integer) this.getValue("d");
	}

	public void setEntityId(int entityId) {
		this.setValue("d", entityId);
	}

	public int getX() {
		return (Integer) this.getValue("a");
	}

	public void setX(int x) {
		this.setValue("a", x);
	}

	public int getY() {
		return (Integer) this.getValue("b");
	}

	public void setY(int y) {
		this.setValue("b", y);
	}

	public int getZ() {
		return (Integer) this.getValue("c");
	}

	public void setZ(int z) {
		this.setValue("c", z);
	}

}

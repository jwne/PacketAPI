package me.bigteddy98.packetapi.api.wrapper;

import me.bigteddy98.packetapi.PacketWrapper;

public class PacketWrapperPlayOutAttachEntity extends PacketWrapper {

	public PacketWrapperPlayOutAttachEntity(Object packet) {
		super(packet);
	}

	public int getData() {
		return (Integer) getValue("a");
	}

	public void setData(int data) {
		setValue("a", data);
	}

	public int getEntityId1Field() {
		return (Integer) getValue("b");
	}

	public void setEntityId1Field(int entityId) {
		setValue("b", entityId);
	}

	public int getEntityId2Field() {
		return (Integer) getValue("c");
	}

	public void setEntityId2Field(int entityId) {
		setValue("c", entityId);
	}
}

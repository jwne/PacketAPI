package me.bigteddy98.packetapi.api.wrapper;

import net.minecraft.server.v1_7_R2.ChatComponentText;

public class ChatComponentWrapper {
	
	public static Object makeChatComponent(String text) {
		return new ChatComponentText(text);
	}
}

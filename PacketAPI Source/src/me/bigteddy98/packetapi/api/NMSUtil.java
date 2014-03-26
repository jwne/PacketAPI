package me.bigteddy98.packetapi.api;

import java.lang.reflect.Field;

import net.minecraft.server.v1_7_R2.ItemStack;

import org.bukkit.craftbukkit.v1_7_R2.inventory.CraftItemStack;

public class NMSUtil {

	private static Field handle;

	static {
		try {
			handle = CraftItemStack.class.getDeclaredField("handle");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("This version of APIPacket is outdated, please report this crash to the author.");
		}
	}

	public static ItemStack getHandle(CraftItemStack stack) {
		try {
			return (ItemStack) handle.get(stack);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

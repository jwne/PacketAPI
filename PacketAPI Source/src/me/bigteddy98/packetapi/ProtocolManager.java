package me.bigteddy98.packetapi;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map.Entry;

import me.bigteddy98.packetapi.api.Cancellable;
import net.minecraft.server.v1_7_R2.EntityPlayer;
import net.minecraft.server.v1_7_R2.NetworkManager;
import net.minecraft.server.v1_7_R2.Packet;
import net.minecraft.util.io.netty.channel.Channel;
import net.minecraft.util.io.netty.channel.ChannelDuplexHandler;
import net.minecraft.util.io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.io.netty.channel.ChannelPromise;

import org.bukkit.craftbukkit.v1_7_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ProtocolManager implements Listener {

	private PacketAPI plugin;
	private Field m;

	private HashMap<String, Channel> channels = new HashMap<String, Channel>();

	public ProtocolManager(PacketAPI plugin) {

		try {
			m = NetworkManager.class.getDeclaredField("m");
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.setAccessible(true);

		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		for (Player p : this.plugin.getServer().getOnlinePlayers()) {
			inject(p);
		}
	}

	@EventHandler
	private void onJoin(PlayerJoinEvent event) {
		inject(event.getPlayer());
	}

	private void inject(final Player p) {
		try {
			EntityPlayer ep = ((CraftPlayer) p).getHandle();
			Channel channel = (Channel) m.get(ep.playerConnection.networkManager);
			channels.put(p.getName(), channel);

			channel.pipeline().addBefore("packet_handler", "PacketAPI", new ChannelDuplexHandler() {

				@Override
				public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
					Cancellable cancel = new Cancellable();
					plugin.packetSend(new PacketWrapper((Packet) msg), cancel, p.getName());
					if (cancel.isCancelled()) {
						return;
					}
					super.write(ctx, msg, promise);
				}

				@Override
				public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
					Cancellable cancel = new Cancellable();
					plugin.packetRecieve(new PacketWrapper((Packet) msg), cancel, p.getName());
					if (cancel.isCancelled()) {
						return;
					}
					super.channelRead(ctx, msg);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disable() {
		HandlerList.unregisterAll(this);
		for (Entry<String, Channel> channel : this.channels.entrySet()) {
			if (this.plugin.getServer().getPlayerExact(channel.getKey()).isOnline()) {
				channel.getValue().pipeline().remove("PacketAPI");
			}
		}
	}
}

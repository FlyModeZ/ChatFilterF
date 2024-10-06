package moe.luoluo.chatfilterf; /* /ChatFilterF.java */

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.HashSet;

import moe.luoluo.chatfilterf.config.ConfigF;
import moe.luoluo.chatfilterf.command.CommandF;
import moe.luoluo.chatfilterf.filter.FilterHandler;
import moe.luoluo.chatfilterf.filter.FilterEntry;
import moe.luoluo.chatfilterf.Instance;

@Mod(
modid=ChatFilterF.MODID, name=ChatFilterF.MODNAME,
version=ChatFilterF.VERSION, clientSideOnly=true
)
public class ChatFilterF {

public static final String MODID = "chatfilterf";
public static final String MODNAME = "ChatFilterF";
public static final String VERSION = "1.0";

private ConfigF config = new ConfigF();

@EventHandler
public void onPreInit(FMLPreInitializationEvent event) {
Instance.INSTANCE.filters = config.load(); }

@EventHandler
public void onInit(FMLInitializationEvent event) {
MinecraftForge.EVENT_BUS.register(new FilterHandler());
ClientCommandHandler.instance.registerCommand(new CommandF()); }

}
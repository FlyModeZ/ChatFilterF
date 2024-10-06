package moe.luoluo.chatfilterf.filter; /* /Filter/FilterHandler.java */

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraft.util.ChatComponentText;

import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatStyle;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;

import net.minecraft.client.Minecraft;

import java.util.regex.Matcher;
import java.util.HashSet;

import moe.luoluo.chatfilterf.Instance;

public class FilterHandler {

private Minecraft minecraft = Minecraft.getMinecraft();

@SubscribeEvent
public void onChat (ClientChatReceivedEvent event) {

/* event.type 0player 1server 2actionbar 3commandresult 4playerloginout*/

String text = event.message.getFormattedText();
Matcher match = null;
for (FilterEntry filter : Instance.INSTANCE.filters) {

match = filter.getPattern().matcher(text);
if (match.find() ) {

text = match.replaceAll(filter.getReplacement());
ChatComponentText output = new ChatComponentText(text);

event.message = output;

minecraft.thePlayer.sendChatMessage(filter.getSaychat());

break;

}}

}}
package moe.luoluo.chatfilterf.command; /* /config/CommandF.java */

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.BlockPos;
import net.minecraft.client.resources.I18n;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import moe.luoluo.chatfilterf.Instance;
import moe.luoluo.chatfilterf.filter.FilterEntry;
import moe.luoluo.chatfilterf.config.ConfigF;

public class CommandF implements ICommand {
/*
/chatfilterf list
/chatfilterf add <name> <pattern> <replacement> <saychat>
/chatfilterf remove <name>
*/

private ConfigF config = new ConfigF();

@Override
public String getCommandName() {return "chatfilterf"; }


@Override
public String getCommandUsage(ICommandSender sender)
{return "/filter <add|remove|list|load|save> [args...]"; }


@Override
public List<String> getCommandAliases() {return Arrays.asList("cf"); }


@Override
public void processCommand(ICommandSender sender, String[] args) {
if (args.length == 0) {
sender.addChatMessage(new ChatComponentText(I18n.format("cff.help")));
return;
}

switch (args[0].toLowerCase()) {

case "list":
sender.addChatMessage(new ChatComponentText(I18n.format("cff.list")));

for (FilterEntry filter : Instance.INSTANCE.filters) {
sender.addChatMessage(new ChatComponentText(filter.getName() + " " + filter.getPattern().pattern()));
}

break;

case "add":
if (args.length >= 4) {
String name = args[1];
String pattern = args[2];
String replacement = args[3];
String saychat = (args.length >= 5) ? args[4] : "";

FilterEntry filter = new FilterEntry(name, pattern, replacement, saychat);
Instance.INSTANCE.filters.add(filter);

sender.addChatMessage(new ChatComponentText(I18n.format("cff.add") + name)); }

break;

case "remove":
if (args.length == 2) {
String name = args[1];
sender.addChatMessage(new ChatComponentText(I18n.format("cff.remove") + name));

Instance.INSTANCE.filters.removeIf(filter -> filter.getName().equals(name)); }
//Java 8+

break;

case "load":

Instance.INSTANCE.filters = config.load();
sender.addChatMessage(new ChatComponentText(I18n.format("cff.load")));
break;

case "save":

config.save(Instance.INSTANCE.filters);
sender.addChatMessage(new ChatComponentText(I18n.format("cff.save")));
break;

default:
sender.addChatMessage(new ChatComponentText(I18n.format("cff.help")));
break;

}}


@Override
public boolean canCommandSenderUseCommand(ICommandSender sender) {return true; }

@Override
public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {

List<String> options = new ArrayList<>();

if (args.length == 1) {
options.add("list");
options.add("add");
options.add("remove");
} else if (args[0].equalsIgnoreCase("add")) {
switch (args.length) {

case 2:
options.add("\"filter name\""); break;

case 3:
options.add("\"regex pattern\""); break;

case 4:
options.add("\"replacement\""); break;

case 5:
options.add("\"saychat\""); break;

}}

return options;
}

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

}
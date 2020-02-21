package vipinx.gangbot.commands;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import vipinx.gangbot.util.Command;
import vipinx.gangbot.util.CommandEnum;

public class HelpCommand extends Command {

    private static Map<String, CommandEnum> commandMap = new HashMap<String, CommandEnum>();

    static {
        commandMap.put("help", CommandEnum.HELP);
        commandMap.put("solve", CommandEnum.SOLVE);
        commandMap.put("image", CommandEnum.IMAGE);

        commandHelpMap.put(CommandEnum.HELP, "Lists commands\nPass command as argument for additional help and usage\nUsage: `g!help`, `g!help [command]`");
    }

    public HelpCommand(MessageReceivedEvent event) {
        super(event);
    }

    public HelpCommand() { /* used to initialize command */ }
    
    public void execCommand() {

        if (args.length == 2) {
            if (commandMap.containsKey(args[1])) {
                switch (commandMap.get(args[1])) {
                    case HELP:
                        showHelp(CommandEnum.HELP);
                        break;
                    case SOLVE:
                        showHelp(CommandEnum.SOLVE);
                        break;
                    case IMAGE:
                        showHelp(CommandEnum.IMAGE);
                        break;
                }
            } else {
                channel.sendMessage("unknown command: " + args[1]).queue();
            }

        } else if (args.length == 1) {

            EmbedBuilder embed = new EmbedBuilder()
                    .setColor(new Color(75, 0, 130))
                    .addField("Prefix", "`g!`", true)
                    .addField("Commands", "`help`, `solve`, `image`", true)
                    .setDescription("Use `g!help [command]` to get help for a command!");
            channel.sendMessage(embed.build()).queue();

        } else {

            channel.sendMessage("too many arguments").queue();

        }
    }

    private void showHelp(CommandEnum commandType) {
        channel.sendMessage(commandHelpMap.get(commandType)).queue();
    }
    
}
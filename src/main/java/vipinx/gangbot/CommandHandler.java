package vipinx.gangbot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import vipinx.gangbot.commands.*;
import vipinx.gangbot.util.CommandEnum;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler extends ListenerAdapter {

    private Map<String, CommandEnum> commandMap = new HashMap<String, CommandEnum>();

    CommandHandler() {
        commandMap.put("g!help", CommandEnum.HELP);
        commandMap.put("g!solve", CommandEnum.SOLVE);
        commandMap.put("g!image", CommandEnum.IMAGE);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || !event.getMessage().getContentRaw().startsWith("g!")) return;

        String messageContent = event.getMessage().getContentRaw();
        String commandName = messageContent.split(" ", 2)[0];
        if (commandMap.containsKey(commandName)) {
            switch (commandMap.get(commandName)) {
                case HELP:
                    new HelpCommand(event);
                    break;
                case SOLVE:
                    new SolveCommand(event);
                    break;
                case IMAGE:
                    new ImageCommand(event);
                    break;
            }
        }
    }

}
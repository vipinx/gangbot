package vipinx.gangbot.util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

public abstract class Command {

    protected MessageReceivedEvent event;
    protected Message message;
    protected MessageChannel channel;
    protected User author;
    protected Member member;
    protected Guild guild;
    protected String messageContent;
    protected String[] args;

    protected static Map<CommandEnum, String> commandHelpMap;

    static {
        commandHelpMap = new HashMap<CommandEnum, String>();
    }

    public Command(MessageReceivedEvent event) {
        this.event = event;
        message = event.getMessage();
        channel = event.getChannel();
        author = event.getAuthor();
        member = event.getMember();
        guild = event.getGuild();
        messageContent = event.getMessage().getContentRaw();
        args = messageContent.split(" ");

        execCommand();
    }

    protected Command() {
        System.out.println(this.getClass().getName() + " initialized");
    }

    public abstract void execCommand();

}
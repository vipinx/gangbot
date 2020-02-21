package vipinx.gangbot.commands;

import java.io.IOException;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import vipinx.gangbot.util.Command;
import vipinx.gangbot.util.CommandEnum;

public class SolveCommand extends Command {

    static {
        commandHelpMap.put(CommandEnum.SOLVE, "Solves an expression\nUsage: `g!solve [expression]`");
    }

    public SolveCommand(MessageReceivedEvent event) {
        super(event);
    }

    public SolveCommand() { /* used to initialize command */ }
    
    @Override
    public void execCommand() {
        if (messageContent.contains("=")) {
            channel.sendMessage("equations not supported").queue();
            return;
        } else if (args.length == 1) {
            channel.sendMessage("no expression entered").queue();
            return;
        }
        try {

            Document document = Jsoup.connect("https://www.google.com/search")
                    .data("q", messageContent.substring("g!solve ".length()))
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:73.0) Gecko/20100101 Firefox/73.0")
                    .get();
            // new HtmlFileFromString(document.toString());

            Element element = document.getElementsByClass("qv3Wpe").first();
            if (element == null) {
                channel.sendMessage("error").queue();
                return;
            }
            String elementString = element.toString();
            String result = elementString.substring(elementString.indexOf(">") + 1, elementString.indexOf("</span>") - 1);
            channel.sendMessage(result).queue();

        } catch (IOException e) {
            e.printStackTrace();
            channel.sendMessage("error: check logs").queue();
        }
    }

}
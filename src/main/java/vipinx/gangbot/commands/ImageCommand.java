package vipinx.gangbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import vipinx.gangbot.util.Command;
import vipinx.gangbot.util.CommandEnum;

import java.util.Random;


public class ImageCommand extends Command {

    static {
        commandHelpMap.put(CommandEnum.IMAGE, "Gets a random image from google given the query\nUsage: `g!help [query]`");
    }

    public ImageCommand(MessageReceivedEvent event) {
        super(event);
    }

    public ImageCommand() { /* used to initialize command */ }

    public void execCommand() {
        Random rand = new Random();
        if (args.length < 2) {
            channel.sendMessage("no search query").queue();
            return;
        }
        String query = args[1];
        if (args.length > 2) {
            StringBuilder queryBuilder = new StringBuilder(query);
            for (int i = 2; i < args.length; i++) {
                queryBuilder.append(" ").append(args[i]);
            }
            query = queryBuilder.toString();
        }

        try {

            String searchURL = "https://www.google.com/search?q=" + query + "&tbm=isch";
            Document document = Jsoup.connect(searchURL).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:73.0) Gecko/20100101 Firefox/73.0").get();
            Elements imagesElements = document.getElementsByTag("img");
            if (imagesElements.size() == 0) {
                channel.sendMessage("no image found").queue();
                return;
            }
            int index = rand.nextInt(imagesElements.size() - 1);
            String imageString = imagesElements.get(index).toString();
            int startIndex = imageString.indexOf("data-iurl=");
            int endIndex = imageString.indexOf("jsname=");
            if (startIndex == -1 || endIndex == -1) {
                channel.sendMessage("error in finding image: check logs").queue();
                System.out.println(imageString);
            } else {
                channel.sendMessage(imageString.substring(startIndex + 11, endIndex - 2)).queue();
            }

        } catch(Exception e) {
            e.printStackTrace();
            channel.sendMessage("error: check logs").queue();
        }
    }

}

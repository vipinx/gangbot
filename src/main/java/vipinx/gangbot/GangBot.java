package vipinx.gangbot;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import vipinx.gangbot.commands.*;

public class GangBot {
    
    public static void main(String[] args) {
        GangBot gangbot = new GangBot();
        gangbot.start(args[0]);
    }
    
    private void start(String token) {
        try {
            JDA api = new JDABuilder(AccountType.BOT).setToken(token).build();
            api.addEventListener(new CommandHandler());
            init();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        new HelpCommand();
        new SolveCommand();
        new ImageCommand();
    }
    
}
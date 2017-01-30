package modules;

import client.ClientManager;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Phost on 30.01.2017.
 */
public class CommandManager {

    public static void commandIn(IChannel channel, String command) throws RateLimitException, DiscordException, MissingPermissionsException, IOException {
        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> tempCharList = new ArrayList<String>();
        String temp = null;
        int i = 0;
        Scanner scanner = new Scanner(command);
        while(scanner.hasNext()){
            args.add(scanner.next());
        }
        System.out.println(args.get(0));
        if(args.get(0).equals("botworking")){
            Commands.botWorkingCommand(channel);
            return;
        }
        if(args.get(0).equals("settimezone")){
            Commands.setTimezoneCommand(channel.getGuild(), args.get(1));
            return;
        }
        if(args.get(0).equals("time") || args.get(0).equals("zeit")){
            Commands.timeCommand(channel);
            return;
        }
        if(args.get(0).equals("date") || args.get(0).equals("datum")){
            Commands.dateCommand(channel);
            return;
        }
        if(args.get(0).equals("weekday") || args.get(0).equals("wochentag")){
            Commands.weekDayCommand(channel);
            return;
        }
        if(args.get(0).equals("invlink") || args.get(0).equals("invitelink")){
            Commands.inviteLinkCommand(channel);
            return;
        }
        if(args.get(0).equals("invlinktesting") || args.get(0).equals("invitelinktesting")){
            Commands.inviteLinkTestingCommand(channel);
            return;
        }
        if(args.get(0).equals("koala")){
            Commands.koalaCommand(channel);
            return;
        }
        if(args.get(0).equals("help") || args.get(0).equals("hilfe")){
            Commands.helpCommand(channel);
            return;
        }
        if(args.get(0).equals("testcommand") && ClientManager.getDeveloperMode()){
            Commands.testCommand(channel);
            return;
        }


    }

}

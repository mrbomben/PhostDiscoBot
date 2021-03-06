package console;

import client.ClientManager;
import sun.misc.Cleaner;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import utils.MessageSender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phost on 17.02.2017.
 */
public class ConsoleCommands {

    public static void readConsoleLineCommand() throws IOException, RateLimitException, DiscordException, MissingPermissionsException {
        ConsoleCommandManager.consoleCommandIn(ConsoleCommandManager.readInputLine());
    }

    public static void announceConsoleCommand(ArrayList<String> args) throws RateLimitException, DiscordException, MissingPermissionsException {
        if(args.size() > 1) {
            List<IChannel> channels = ClientManager.getClientInstance().getChannels(false);
            args.remove(0);
            String out = "";
            int o = args.size();
            while(o >= 1) {
                out += args.get(args.size() - o) + " ";
                o--;
            }
            for (int i = channels.size(); i >= 1; i--) {
                if (channels.get(i - 1).getID().equals(channels.get(i - 1).getGuild().getID())) {
                    MessageSender.sendMessage("" + out, channels.get(i - 1));
                }
            }
        }
    }

    public static void deleteMessageConsoleCommand(String messageID) throws RateLimitException, DiscordException, MissingPermissionsException {
        ClientManager.getClientInstance().getMessageByID(messageID).delete();
    }

    public static void setPlayingConsoleCommand(ArrayList<String> args) {
        args.remove(0);
        String out = "";
        int o = args.size();
        while(o >= 1) {
            out += args.get(args.size() - o) + " ";
            o--;
        }
        ClientManager.setPlaying(out);
    }

    public static void shutdownConsoleCommand() throws DiscordException {
        ClientManager.endClientInstance();
    }

    public static void writeConsoleCommand(ArrayList<String> args) {
        if(args.size() > 2) {
            String ChannelID = args.get(1);
            args.remove(0);
            args.remove(0);
            String out = "";
            int o = args.size();
            while(o >= 1) {
                out += args.get(args.size() - o) + " ";
                o--;
            }
            MessageSender.sendMessage(out, ClientManager.getClientInstance().getChannelByID(ChannelID));

        }
    }

    public static void helpConsoleCommand(IChannel channel){
        MessageSender.sendMessage("```Phost\nrl\nannounce <msg>\ndelmsg <msgID>\nsetplaying <text>\nshutdown\nwrite <channelID> <text>\ndelchannel <channelID>\ncreatechannel <guildID> <channelName>\ndelvchannel <vchannelID>\ncreatevchannel <guildID> <vChannelName>\ncreateinv <guildID>```", channel);
    }

    public static void deleteChannelConsoleCommand(String channelID) {
        ClientManager.getClientInstance().getChannelByID(channelID).delete();
    }

    public static void createChannelConsoleCommand(ArrayList<String> args){
        String guildID = args.get(1);
        args.remove(0);
        args.remove(0);
        String out = "";
        int o = args.size();
        while(o >= 1) {
            out += args.get(args.size() - o) + " ";
            o--;
        }
        ClientManager.getClientInstance().getGuildByID(guildID).createChannel(out);
    }

    public static void deleteVoiceChannelConsoleCommand(String voiceChannelID){
        ClientManager.getClientInstance().getVoiceChannelByID(voiceChannelID).delete();
    }

    public static void createVoiceChannelConsoleCommand(ArrayList<String> args){
        String guildID = args.get(1);
        args.remove(0);
        args.remove(0);
        String out = "";
        int o = args.size();
        while(o >= 1) {
            out += args.get(args.size() - o) + " ";
            o--;
        }
        ClientManager.getClientInstance().getGuildByID(guildID).createVoiceChannel(out);
    }

    public static void createInviteConsoleCommand(String guildID){
        MessageSender.sendMessage(ClientManager.getClientInstance().getGuildByID(guildID).getGeneralChannel().createInvite(0, 0, false, false).getInviteCode(), ClientManager.getClientInstance().getOrCreatePMChannel(ClientManager.getClientInstance().getUserByID(ClientManager.getBotAdminID())));
    }
}

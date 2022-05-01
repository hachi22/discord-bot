package me.hachi.discord_bot;

import java.util.Arrays;

import javax.security.auth.login.LoginException;

import ca.tristan.jdacommands.JDACommands;
import events.EventListeners;
import me.hachi.discord_bot.commands.CommandHello;
import me.hachi.discord_bot.commands.CommandPlay;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class DiscordBot 
{
	public static GatewayIntent[] INTENTS = {GatewayIntent.GUILD_EMOJIS, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES};
    public static void main( String[] args ) throws LoginException
    {
    	
    	JDACommands jdaCommands = new JDACommands("!");
    	jdaCommands.registerCommand(new CommandHello());
    	jdaCommands.registerCommand(new CommandPlay());
       
			JDA jda = JDABuilder.create("OTY5OTc1NTg2NDMwNzg3NTg0.Ym1OKQ.6o6o_NigJw7RC-cywQupcKtSd8Y", Arrays.asList(INTENTS))
					.enableCache(CacheFlag.VOICE_STATE)
					.setActivity(Activity.playing("With your mom"))
					.setStatus(OnlineStatus.ONLINE)
					.addEventListeners(new EventListeners())
					.addEventListeners(jdaCommands)
					.build();
    }
}

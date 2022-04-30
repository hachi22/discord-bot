package me.hachi.discord_bot.commands;

import ca.tristan.jdacommands.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandHello implements ICommand{

	@Override
	public void execute(MessageReceivedEvent messageReceivedEvent) {
		messageReceivedEvent.getChannel().sendMessage("Hello World").queue();;
		
	}

	@Override
	public String getName() {
		return "hello";
	}

}

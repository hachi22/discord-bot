package me.hachi.discord_bot.commands;

import ca.tristan.jdacommands.ExecuteArgs;
import ca.tristan.jdacommands.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandHello implements ICommand{

	@Override
	public String getName() {
		return "hello";
	}

	@Override
	public void execute(ExecuteArgs event) {
		event.getTextChannel().sendMessage("Hello World").queue();
		
	}

	@Override
	public String helpMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean needOwner() {
		// TODO Auto-generated method stub
		return false;
	}

}

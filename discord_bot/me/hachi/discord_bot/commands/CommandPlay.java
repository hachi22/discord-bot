package me.hachi.discord_bot.commands;

import java.net.URI;
import java.net.URISyntaxException;

import ca.tristan.jdacommands.ExecuteArgs;
import ca.tristan.jdacommands.ICommand;
import lavaPlayer.PlayerManager;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class CommandPlay implements ICommand{


	@Override
	public String getName() {
	
		return "play";
	}

	@Override
	public void execute(ExecuteArgs event) {
		
		if(!event.getMemberVoiceState().inAudioChannel()) {
			event.getTextChannel().sendMessage("You need to be in a voice channel for this to work, dumbass").queue();
			return;
		}
		
		if(!event.getSelfVoiceState().inAudioChannel()) {
			final AudioManager audioManager = event.getGuild().getAudioManager();
			final VoiceChannel memberChannel = (VoiceChannel) event.getMemberVoiceState().getChannel();
			
			audioManager.openAudioConnection(memberChannel);
		}
		
		String link = String.join(" ",event.getArgs());
		
		if(!isUrl(link)) {
			link = "youtubeSearch:" + link + " audio";
		}
		
		PlayerManager.getINSTANCE().loadAndPlay(event.getTextChannel(), link);
	}
	
	public boolean isUrl(String url) {
		try {
			new URI(url);
			return true;
		} catch (URISyntaxException e) {
			return false;
		}
	}

	@Override
	public String helpMessage() {
		return "This command is to play music.";
	}

	@Override
	public boolean needOwner() {
	
		return false;
	}

}

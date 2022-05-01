package lavaPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

public class PlayerManager {
	
	private static PlayerManager INSTANCE;
	private final Map<Long, GuildMusicManager> musicManager;
	private final AudioPlayerManager audioPlayerManager;
	
	public PlayerManager() {
		this.musicManager = new HashMap<>();
		this.audioPlayerManager = new DefaultAudioPlayerManager();
		
		AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
		AudioSourceManagers.registerLocalSource(this.audioPlayerManager);
	}
	
	public GuildMusicManager getMusicManager(Guild guild) {
		return this.musicManager.computeIfAbsent(guild.getIdLong(), (guildId) -> {
			final GuildMusicManager guildMusicManager = new GuildMusicManager(this.audioPlayerManager);
			guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());
			return guildMusicManager;
		});
	}
	
	public void loadAndPlay(TextChannel textChannel, String trackUrl) {
		final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());
		
		this.audioPlayerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {

			@Override
			public void trackLoaded(AudioTrack track) {
				musicManager.scheduler.queue(track);
				
				textChannel.sendMessage("Adding to queue**`")
				.append(track.getInfo().title)
				.append(track.getInfo().author)
				.append("`**")
				.queue();
				
			}

			@Override
			public void playlistLoaded(AudioPlaylist playlist) {
				final List<AudioTrack> tracks = playlist.getTracks();
				if(!tracks.isEmpty()) {
					musicManager.scheduler.queue(tracks.get(0));
					textChannel.sendMessage("Adding to queue**`")
					.append(tracks.get(0).getInfo().title)
					.append(tracks.get(0).getInfo().author)
					.append("`**")
					.queue();
				}
				
			}

			@Override
			public void noMatches() {
				
			}

			@Override
			public void loadFailed(FriendlyException exception) {
				
				textChannel.sendMessage("Load Failed")
				.queue();
				
			}
			
		});
		
	}
	
	public static PlayerManager getINSTANCE() {
		if(INSTANCE == null) {
			INSTANCE = new PlayerManager();
		}
		return INSTANCE;
	}

}

package events;
import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListeners extends ListenerAdapter{

	@Override
	public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
		if (!event.getGuild().getId().equals("881625439288840212")) {
			return;
		}
		event.getGuild().getTextChannelById("881625439897026572").sendMessage("Helloooooo " + event.getMember().getAsMention() + "!").queue();
	}
}

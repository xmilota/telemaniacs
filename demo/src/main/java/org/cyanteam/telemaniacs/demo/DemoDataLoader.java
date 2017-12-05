package org.cyanteam.telemaniacs.demo;

import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.enums.ChannelType;
import org.cyanteam.telemaniacs.core.services.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DemoDataLoader {
    private static final Logger logger = LoggerFactory.getLogger(DemoDataLoader.class);

    @Inject
    private ChannelService channelService;

    public void load() {
        Channel hbo = createChannel("HBO", ChannelType.MOVIE, "EN");
        Channel cinestar = createChannel("Cinestar", ChannelType.MOVIE, "EN");
        Channel cn = createChannel("Cartoon Network", ChannelType.CHILDREN, "CZ");
        Channel minimax = createChannel("Minimax", ChannelType.CHILDREN, "HU");
        Channel nova = createChannel("Nova", ChannelType.COMMERCE, "CZ");
        Channel joj = createChannel("JOJ", ChannelType.COMMERCE, "SK");
        Channel discovery = createChannel("Discovery Channel", ChannelType.DOCUMENTARY, "EN");
        Channel natgeo = createChannel("Nat Geo", ChannelType.DOCUMENTARY, "EN");
        Channel mtv = createChannel("MTV", ChannelType.MUSIC, "EN");
        Channel vh1 = createChannel("VH1", ChannelType.MUSIC, "EN");
        Channel eurosport = createChannel("Eurosport", ChannelType.SPORT, "EN");
        Channel golf = createChannel("Golf Channel", ChannelType.SPORT, "EE");
    }

    private Channel createChannel(String name, ChannelType channelType, String language) {
        logger.trace("Channel created: " + name);

        Channel channel = new Channel();
        channel.setName(name);
        channel.setChannelType(channelType);
        channel.setLanguage(language);

        channelService.create(channel);
        return channel;
    }
}

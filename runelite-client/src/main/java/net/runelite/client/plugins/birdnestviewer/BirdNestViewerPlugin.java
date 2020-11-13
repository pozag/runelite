package net.runelite.client.plugins.birdnestviewer;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.ui.overlay.OverlayManager;


import javax.inject.Inject;
import com.google.inject.Provides;

@PluginDescriptor(
        name = "Bird Nest Viewer",
        description = "Mark searchable birds nests",
        tags = {"overlay"}
)

public class BirdNestViewerPlugin extends Plugin {
    @Inject
    private OverlayManager overlayManager;

    @Inject
    private BirdNestViewerOverlay overlay;

    @Inject
    private BirdNestViewerConfig config;

    @Provides
    BirdNestViewerConfig getConfig(ConfigManager configManager) {
        return configManager.getConfig(BirdNestViewerConfig.class);
    }

    @Override
    protected void startUp() {
        overlayManager.add(overlay);
    }

    @Override
    protected void shutDown() throws Exception {
        overlayManager.remove(overlay);
    }
}

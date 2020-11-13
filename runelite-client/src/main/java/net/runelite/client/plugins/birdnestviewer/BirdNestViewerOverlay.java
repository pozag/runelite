package net.runelite.client.plugins.birdnestviewer;

import com.google.common.collect.ImmutableMap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;

import net.runelite.api.Client;
import net.runelite.api.ItemID;

import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.widgets.WidgetItem;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;

@Slf4j
class BirdNestViewerOverlay extends Overlay {
    private static final int INVENTORY_SIZE = 28;

    private final BirdNestViewerPlugin plugin;
    private final BirdNestViewerConfig config;
    private final Client client;

    private static final Map<Integer, String> treeTypes = new ImmutableMap.Builder<Integer, String>().
            put(ItemID.ACORN, "Acorn").
            put(ItemID.WILLOW_SEED, "Willow Seed").
            put(ItemID.MAPLE_SEED, "Maple Seed").
            put(ItemID.YEW_SEED, "Yew Seed").
            put(ItemID.MAGIC_SEED, "Magic Seed").
            build();

    private static final int[] treeSeedIds = new int[] {

    }

    @Inject
    BirdNestViewerOverlay(BirdNestViewerPlugin plugin, BirdNestViewerConfig config, Client client) {
        this.plugin = plugin;
        this.config = config;
        this.client = client;

        log.debug(config.getLowestTreeSeed());

        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        Widget inventoryWidget = client.getWidget(WidgetInfo.INVENTORY);
        for (int i = 0; i < INVENTORY_SIZE; ++i) {
            final WidgetItem targetWidgetItem = inventoryWidget.getWidgetItem(i);
            final Rectangle bounds = targetWidgetItem.getCanvasBounds();

            if (targetWidgetItem.getId() == ItemID.BIRD_NEST_22798) {
                Color configColor = config.getBirdNestColor();
                graphics.draw(bounds);
                graphics.setColor(new Color(configColor.getRed(), configColor.getGreen(), configColor.getBlue(), 60));
                graphics.fill(bounds);
            } else if (targetWidgetItem.getId() == ItemId.)
        }

        return null;
    }

}

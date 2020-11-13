package net.runelite.client.plugins.birdnestviewer;

import com.google.common.collect.ImmutableMap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Map;

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

    private static final Map<String, Integer> treeTypes = new ImmutableMap.Builder<String, Integer>().
            put("ACORN", ItemID.ACORN).
            put("WILLOW", ItemID.WILLOW_SEED).
            put("MAPLE", ItemID.MAPLE_SEED).
            put("YEW", ItemID.YEW_SEED).
            put("MAGIC", ItemID.MAGIC_SEED).
            build();

    private static final Map<String, Integer> fruitTreeTypes = new ImmutableMap.Builder<String, Integer>().
            put("APPLE", ItemID.APPLE_TREE_SEED).
            put("BANANA", ItemID.BANANA_TREE_SEED).
            put("ORANGE", ItemID.ORANGE_TREE_SEED).
            put("CURRY", ItemID.CURRY_TREE_SEED).
            put("PINEAPPLE", ItemID.PINEAPPLE_SEED).
            put("PAPAYA", ItemID.PAPAYA_TREE_SEED).
            put("PALM_TREE", ItemID.PALM_TREE_SEED).
            put("DRAGONFRUIT", ItemID.DRAGONFRUIT_TREE_SEED).
            build();

    @Inject
    BirdNestViewerOverlay(BirdNestViewerPlugin plugin, BirdNestViewerConfig config, Client client) {
        this.plugin = plugin;
        this.config = config;
        this.client = client;

        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        Widget inventoryWidget = client.getWidget(WidgetInfo.INVENTORY);
        for (int i = 0; i < INVENTORY_SIZE; ++i) {
            final WidgetItem targetWidgetItem = inventoryWidget.getWidgetItem(i);
            final Rectangle bounds = targetWidgetItem.getCanvasBounds();

            final Integer targetWidgetItemId = targetWidgetItem.getId();

            Color configColor;

            if (targetWidgetItemId == ItemID.BIRD_NEST_22798) {
                configColor = config.getBirdNestColor();
            } else if (treeTypes.containsValue(targetWidgetItemId) &&
                    targetWidgetItemId <= treeTypes.get(String.valueOf(config.getLowestTreeSeed())))
            {
                configColor = config.getDroppableSeedColor();
            } else if (fruitTreeTypes.containsValue(targetWidgetItemId) &&
                    targetWidgetItemId <= fruitTreeTypes.get(String.valueOf(config.getLowestFruitTreeSeed()))) {
                configColor = config.getDroppableSeedColor();
            } else {
                continue;
            }

            graphics.setColor(configColor);
            graphics.draw(bounds);
            graphics.setColor(new Color(configColor.getRed(), configColor.getGreen(), configColor.getBlue(), 50));
            graphics.fill(bounds);
        }

        return null;
    }

}

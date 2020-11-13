package net.runelite.client.plugins.birdnestviewer;

import java.awt.Color;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import net.runelite.api.ItemID;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("birdnestviewer")
public interface BirdNestViewerConfig extends Config {
    @ConfigItem(
            keyName = "birdNestColor",
            name = "Bird Nest Color",
            description = "Color of bird nests",
            position = 1
    )
    default Color getBirdNestColor() { return Color.GREEN; }

    @ConfigItem(
            keyName = "droppableSeedColor",
            name = "Droppable Seed Color",
            description = "Color of droppable seeds",
            position = 2
    )
    default Color getDroppableSeedColor() { return Color.RED; }

    enum TreeSeeds {
        ACORN,
        WILLOW,
        MAPLE,
        YEW,
        MAGIC
    }

    enum FruitTreeSeeds {
        APPLE,
        BANANA,
        ORANGE,
        CURRY,
        PINEAPPLE,
        PAPAYA,
        PALM,
        DRAGONFRUIT
    }

    @ConfigItem(
            keyName = "lowestTreeSeed",
            name = "Lowest Tree Seed",
            description = "Mark tree seeds below this level for dropping",
            position = 3
    )
    default TreeSeeds getLowestTreeSeed() {

        return TreeSeeds.ACORN;
    }

    @ConfigItem(
            keyName = "lowestFruitTreeSeed",
            name = "Lowest Fruit Tree Seed",
            description = "Mark fruit tree seeds below this level for dropping",
            position = 4
    )
    default  FruitTreeSeeds getLowestFruitTreeSeed() { return  FruitTreeSeeds.APPLE; }
}
package com.lieqiitems.block;

import com.lieqiitems.Item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static com.lieqiitems.LieQiItems.MOD_ID;

public class ModBlocks {
    public static final Block LIEQI_BLOCK = register("lieqi_block", new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.METAL).requiresTool().strength(2.0f,1.0f)));

    //注册方块
    public static Block register(String name, Block block, boolean shouldRegisterItem) {
        Identifier id = Identifier.of(MOD_ID, name);

        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, id, blockItem);
        }

        //自动放入物品组
        ItemGroupEvents.modifyEntriesEvent(ModItems.LIEQI_ITEM_GROUP_KEY).register(
                itemGroup->{
                    itemGroup.add(block.asItem());
                }
        );

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static Block register(String name, Block block) {
        return register(name, block, true);
    }

    public static void initialize() {}
}

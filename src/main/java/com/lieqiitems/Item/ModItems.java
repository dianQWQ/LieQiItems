package com.lieqiitems.Item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.lieqiitems.LieQiItems.MOD_ID;

public class ModItems {
    //物品组
    public static final RegistryKey<ItemGroup> LIEQI_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(),Identifier.of(MOD_ID,"lqitm_group"));
    public static final ItemGroup LIE_QI_ITEM_GROUP_ENTITY = FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.lqitm"))
            .icon(() -> new ItemStack(ModItems.LIEQI))
            .build();

    public static final ItemGroup LIE_QI_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, LIEQI_ITEM_GROUP_KEY, LIE_QI_ITEM_GROUP_ENTITY);

    //物品
    public static final Item LIEQI = register("lieqi", new Item(new Item.Settings().maxCount(16)));

    //注册物品
    private static Item register(String id, Item item) {
        return register(id, item, true);
    }
    private static Item register(String id, Item item, boolean putInToItemGroup){
        Identifier itemID = Identifier.of(MOD_ID,id);

        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);

        if (putInToItemGroup) {
            //自动放入物品组
            ItemGroupEvents.modifyEntriesEvent(LIEQI_ITEM_GROUP_KEY).register(itemGroup->{
                itemGroup.add(registeredItem);
            });
        }
        return registeredItem;
    }

    //添加额外属性
    private static void addExtraProperties() {
        //作为燃料
        FuelRegistry.INSTANCE.add(LIEQI, 30 * 20);
        //堆肥
        CompostingChanceRegistry.INSTANCE.add(LIEQI, 20f);
    }
    public static void initialize() {
        //添加额外属性
        addExtraProperties();
    }
}

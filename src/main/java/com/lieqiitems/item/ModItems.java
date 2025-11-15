package com.lieqiitems.item;

import com.lieqiitems.item.items.LieQi;
import com.lieqiitems.item.items.LieQiStaff;
import com.lieqiitems.item.items.LieQiStaffPlus;
import com.lieqiitems.item.materials.LieQiMaterial;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
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
    public static final LieQi LIEQI = register("lieqi", new LieQi(new Item.Settings().maxCount(16)));
    public static final Item LIEQI_INGOT = register("lieqi_ingot", new Item(new Item.Settings()));
    public static final Item PURE_LIEQI = register("pure_lieqi", new Item(new Item.Settings()));
//    public static final Item LIEQI_WTF = register("lieqi_wtf", new Item(new Item.Settings()),false);

    public static final Item LIEQI_SWORD = register(
            "lieqi_sword",
            new SwordItem(
                    LieQiMaterial.INSTANCE,
                    new Item.Settings().attributeModifiers(SwordItem
                            .createAttributeModifiers(
                                    LieQiMaterial.INSTANCE, 3, -3.0f
                            )
                    )
            )
    );

    public static final Item LIEQI_AXE = register(
            "lieqi_axe",
            new AxeItem(LieQiMaterial.INSTANCE,
                    new Item.Settings().attributeModifiers(AxeItem
                            .createAttributeModifiers(
                                    LieQiMaterial.INSTANCE, 6, -2f
                            )
                    )
            )
    );
    public static final Item LIEQI_HOE = register(
            "lieqi_hoe",
            new HoeItem(LieQiMaterial.INSTANCE,
                    new Item.Settings()
                            .attributeModifiers(HoeItem.createAttributeModifiers(
                                    LieQiMaterial.INSTANCE, -4, -2.5f
                            )
                            )
            )
    );
    public static final Item LIEQI_PICKAXE = register(
            "lieqi_pickaxe",
            new PickaxeItem(LieQiMaterial.INSTANCE,
                    new Item.Settings().attributeModifiers(PickaxeItem
                            .createAttributeModifiers(
                                    LieQiMaterial.INSTANCE, -1, -2.5f
                            )
                    )
            )
    );

    public static final Item LIEQI_SHOVEL = register(
            "lieqi_shovel",
            new ShovelItem(LieQiMaterial.INSTANCE,
                    new Item.Settings().attributeModifiers(
                            ShovelItem.createAttributeModifiers(
                                    LieQiMaterial.INSTANCE, -2, -2.5f
                            )
                    )
            )
    );

    public static final Item LIEQI_BREAD = register(
            "lieqi_bread", new Item(new Item.Settings()
                    .food(new FoodComponent.Builder()
                            .nutrition(5)
                            .saturationModifier(10)
                            .alwaysEdible()
                            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 30 * 20, 10), 0.9f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 15 * 20, 3), 0.1f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST,10*20,255),0.05f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE,1,255),0.001f)
                            .build()
                    )
            )
    );

    public static final Item LIEQI_CORE = register("lieqi_core", new Item(new Item.Settings()));
    public static final LieQiStaff LIEQI_STAFF = register("lieqi_staff", new LieQiStaff(new Item.Settings().maxCount(1)));
    public static final LieQiStaffPlus LIEQI_STAFF_PLUS = register("lieqi_staff_plus", new LieQiStaffPlus(new Item.Settings().maxCount(1)));

    //注册物品
    private static <T extends Item> T register(String id, T item) {
        return register(id, item, true);
    }
    private static <T extends Item> T register(String id, T item, boolean putInToItemGroup){
        Identifier itemID = Identifier.of(MOD_ID,id);

        T registeredItem = Registry.register(Registries.ITEM, itemID, item);

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
        FuelRegistry.INSTANCE.add(LIEQI, 10 * 200);
        //堆肥
        CompostingChanceRegistry.INSTANCE.add(LIEQI, 1600f);
        CompostingChanceRegistry.INSTANCE.add(LIEQI_BREAD, 20f);
    }
    public static void initialize() {
        //添加额外属性
        addExtraProperties();
    }
}

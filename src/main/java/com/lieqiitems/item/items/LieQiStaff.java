package com.lieqiitems.item.items;

import com.lieqiitems.modTools.SpawnTool;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class LieQiStaff extends Item {
    public LieQiStaff(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("itemTooltip.lqitm.lieqi_staff").formatted(Formatting.BOLD).formatted(Formatting.GOLD));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (world.isClient) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        Vec3d lookDirection = user.getRotationVec(1.0f);
        Vec3d spawnPos = user.getEyePos().add(lookDirection.multiply(2f));
        SpawnTool.spawnFireBall(world, spawnPos, lookDirection);

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}

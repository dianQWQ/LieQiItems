package com.lieqiitems.item.items;

import com.lieqiitems.modTools.SpawnTool;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LieQiStaffPlus extends LieQiStaff{
    public LieQiStaffPlus(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (world.isClient) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        Vec3d lookDirection = user.getRotationVec(1.0f);
        Vec3d spawnPos = user.getEyePos().add(lookDirection.multiply(2f));
        SpawnTool.spawnSuperFireBall(world, spawnPos, lookDirection);

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}

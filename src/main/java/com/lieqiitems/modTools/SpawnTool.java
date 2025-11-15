package com.lieqiitems.modTools;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

//生成火球
public class SpawnTool {
    private SpawnTool() {}
    public static void spawnFireBall(World world, Vec3d spawnPos, Vec3d lookDirection) {
        FireballEntity fireballEntity = new FireballEntity(
                EntityType.FIREBALL,
                world
        );

        fireballEntity.setPosition(spawnPos);
        fireballEntity.setVelocity(lookDirection.multiply(2f));
        world.spawnEntity(fireballEntity);
    }

    public static void spawnSuperFireBall(World world, Vec3d spawnPos, Vec3d lookDirection) {
        for (int i = 0; i < 20; i++) {
            spawnFireBall(world,spawnPos.add(lookDirection.multiply(i*4)),lookDirection);
        }
    }
}

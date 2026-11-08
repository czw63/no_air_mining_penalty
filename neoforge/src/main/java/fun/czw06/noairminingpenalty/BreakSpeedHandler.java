package fun.czw06.noairminingpenalty;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class BreakSpeedHandler {

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();

        // 已经踩在方块上：原版本来就没有空中惩罚，什么都不用做
        if (player.onGround()) {
            return;
        }

        // 眼睛在水里时原版还叠了一层水下惩罚，交给 SUBMERGED_MINING_SPEED 属性，别在这里抹掉
        if (player.isEyeInFluid(FluidTags.WATER)) {
            return;
        }

        // 原版在空中时速度 /= 5，这里乘回 5 抵消
        event.setNewSpeed(event.getOriginalSpeed() * 5.0F);
    }
}

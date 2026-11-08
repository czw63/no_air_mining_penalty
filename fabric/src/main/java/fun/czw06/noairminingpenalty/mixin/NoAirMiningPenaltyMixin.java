package fun.czw06.noairminingpenalty.mixin;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Player.class)
public abstract class NoAirMiningPenaltyMixin {

    /**
     * 26.3 的 Player#getDestroySpeed 里只剩一处 5.0F 常量，就是「不在方块上时挖掘速度 / 5」的惩罚
     * （水下惩罚已改成 SUBMERGED_MINING_SPEED 属性，不再用这个常量），所以这里把 5.0F 换成 1.0F。
     */
    @ModifyConstant(
        method = "getDestroySpeed",
        constant = @Constant(floatValue = 5.0F)
    )
    private float removeAirPenalty(float original) {
        return 1.0F;
    }
}

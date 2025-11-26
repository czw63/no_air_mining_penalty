package fun.czw06.noairminingpenalty.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerEntity.class)
public abstract class NoAirMiningPenaltyMixin {

    @ModifyConstant(
        method = "getBlockBreakingSpeed",
        constant = @Constant(floatValue = 5.0F)
    )
    private float removeAirPenalty(float original) {
        // 原版是 f /= 5.0F，这里改成 f /= 1.0F
        return 1.0F;
    }
}

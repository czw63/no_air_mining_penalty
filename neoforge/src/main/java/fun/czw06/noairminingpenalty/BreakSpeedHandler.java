package fun.czw06.noairminingpenalty;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class BreakSpeedHandler {
    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        // 使用字段 onGround，而不是方法 isOnGround()
        if (!event.getEntity().onGround) {
            event.setNewSpeed(event.getOriginalSpeed());
        }
    }
}
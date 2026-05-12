package fun.czw06.noairminingpenalty;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import java.lang.reflect.Field;

public class BreakSpeedHandler {
    private static final Field ON_GROUND_FIELD;

    static {
        try {
            Field field = Entity.class.getDeclaredField("onGround");
            field.setAccessible(true);
            ON_GROUND_FIELD = field;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Failed to find onGround field", e);
        }
    }

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        try {
            Player player = event.getEntity();
            boolean onGround = (boolean) ON_GROUND_FIELD.get(player);
            
            // 核心逻辑：如果不在空中，不做任何修改
            if (onGround) {
                return;
            }

            // 移除空中惩罚：原版在空中挖掘速度变为 1/5，这里乘以 5 恢复
            float originalSpeed = event.getOriginalSpeed();
            float newSpeed = originalSpeed * 5.0f;

            // 避免过度加速（可选，限幅到合理范围）
            float maxSpeed = originalSpeed * 10.0f;
            if (newSpeed > maxSpeed) {
                newSpeed = maxSpeed;
            }

            event.setNewSpeed(newSpeed);

            // 可选：调试输出（生产环境可注释）
            // System.out.println("[No Air Mining Penalty] Removed penalty, speed: " + newSpeed);
        } catch (IllegalAccessException e) {
            // 由于 setAccessible(true)，理论上不会发生
            e.printStackTrace();
        }
    }
}
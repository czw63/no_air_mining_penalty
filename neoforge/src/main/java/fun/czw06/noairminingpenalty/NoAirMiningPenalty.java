package fun.czw06.noairminingpenalty;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(NoAirMiningPenalty.MOD_ID)
public class NoAirMiningPenalty {
    public static final String MOD_ID = "no_air_mining_penalty";

    public NoAirMiningPenalty(IEventBus modBus) {
        NeoForge.EVENT_BUS.register(new BreakSpeedHandler());
    }
}
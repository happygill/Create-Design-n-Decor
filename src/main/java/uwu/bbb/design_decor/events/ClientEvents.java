package uwu.bbb.design_decor.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

import static uwu.bbb.design_decor.Decor.MOD_ID;
import static uwu.bbb.design_decor.Decor.NAME;
import static uwu.bbb.design_decor.DecorClient.forDecor;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientEvents {



    @Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModBusEvents {

        @SubscribeEvent
        public static void onLoadComplete(FMLLoadCompleteEvent event) {
            ModContainer createContainer = ModList.get()
                    .getModContainerById(MOD_ID)
                    .orElseThrow(() -> new IllegalStateException(NAME + " mod container missing on LoadComplete"));
            createContainer.registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                    () -> new ConfigScreenHandler.ConfigScreenFactory(
                            (mc, previousScreen) -> forDecor(previousScreen)));
        }
    }
}

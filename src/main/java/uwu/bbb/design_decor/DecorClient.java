package uwu.bbb.design_decor;

import com.simibubi.create.foundation.config.ui.BaseConfigScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static uwu.bbb.design_decor.Decor.MOD_ID;

public class DecorClient {
    public static void onCtorClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        modEventBus.addListener(DecorClient::clientInit);
    }


    public static void clientInit(final FMLClientSetupEvent event) {
        ModLoadingContext.get().getActiveContainer().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> new BaseConfigScreen(screen, MOD_ID)));
    }

    public static BaseConfigScreen forDecor(Screen parent) {
        return new BaseConfigScreen(parent, MOD_ID);
    }
}

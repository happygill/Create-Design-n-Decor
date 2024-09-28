package uwu.bbb.design_decor.registry.helper;

import com.tterrag.registrate.util.entry.ItemEntry;
import uwu.bbb.design_decor.content.blocks.storage_container.ColoredStorageContainerItem;
import uwu.bbb.design_decor.registry.DecorCreativeModeTabs;
import static uwu.bbb.design_decor.registry.helper.decor.ColorHelper.DefaultColorProvider.*;

import static uwu.bbb.design_decor.Decor.REGISTRATE;

@SuppressWarnings("unused")
public class DecorBlockItems {

    public static final ItemEntry<ColoredStorageContainerItem> COLORED_STORAGE_CONTAINER =
            REGISTRATE.item("red_storage_container", p -> new ColoredStorageContainerItem(p, RED))
                    .lang("Red Storage Container")
                    .model((c, p) -> p.withExistingParent(c.getName(), p.modLoc("item/storage_containers/red")))
                    .tab(() -> DecorCreativeModeTabs.BASE_CREATIVE_TAB)
                    .register();

    public static void register() {}
}
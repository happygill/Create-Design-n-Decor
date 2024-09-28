package uwu.bbb.design_decor.registry;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import uwu.bbb.design_decor.content.blocks.storage_container.ColoredStorageContainerBlockEntity;

import static uwu.bbb.design_decor.Decor.REGISTRATE;

public class DecorBlockEntities {

    public static BlockEntityEntry<ColoredStorageContainerBlockEntity> COLORED_STORAGE_CONTAINER =
            REGISTRATE.blockEntity("colored_storage_container", ColoredStorageContainerBlockEntity::new)
            .validBlock(DecorBlocks.COLORED_STORAGE_CONTAINER)
            .register();

    public static void register() {
    }
}

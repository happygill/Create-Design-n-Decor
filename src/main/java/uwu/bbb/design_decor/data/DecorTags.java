package uwu.bbb.design_decor.data;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static uwu.bbb.design_decor.Decor.REGISTRATE;

public class DecorTags {
    public static void addGenerators() {
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, DecorTags::genBlockTags);
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, DecorTags::genItemTags);
    }

    private static void genItemTags(RegistrateTagsProvider<Item> prov) {

    }

    private static void genBlockTags(RegistrateTagsProvider<Block> prov) {

    }
}

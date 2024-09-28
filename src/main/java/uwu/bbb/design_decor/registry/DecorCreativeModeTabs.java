package uwu.bbb.design_decor.registry;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

import static uwu.bbb.design_decor.Decor.MOD_ID;
import static uwu.bbb.design_decor.Decor.REGISTRATE;

public class DecorCreativeModeTabs {

    public static final CreativeModeTab BASE_CREATIVE_TAB = new DecorCreativeModeTab("base", Items.LODESTONE.getDefaultInstance());










    public static void init() {}

    public static class DecorCreativeModeTab extends CreativeModeTab {

        ItemStack icon;

        public DecorCreativeModeTab(String id, ItemStack icon) {
            super(MOD_ID + "." + id);
            this.icon = icon;
        }

        @Override
        public void fillItemList(@NotNull NonNullList<ItemStack> items) {
            addItems(items, true);
            addBlocks(items);
            addItems(items, false);
        }

        protected Collection<RegistryEntry<Item>> registeredItems() {
            return REGISTRATE.getAll(ForgeRegistries.ITEMS.getRegistryKey());
        }

        public void addBlocks(NonNullList<ItemStack> items) {
            for (RegistryEntry<Item> entry : registeredItems())
                if (entry.get() instanceof BlockItem blockItem)
                    blockItem.fillItemCategory(this, items);
        }

        public void addItems(NonNullList<ItemStack> items, boolean specialItems) {
            ItemRenderer itemRenderer = Minecraft.getInstance()
                    .getItemRenderer();

            for (RegistryEntry<Item> entry : registeredItems()) {
                Item item = entry.get();
                if (item instanceof BlockItem)
                    continue;
                ItemStack stack = new ItemStack(item);
                BakedModel model = itemRenderer.getModel(stack, null, null, 0);
                if (model.isGui3d() == specialItems)
                    item.fillItemCategory(this, items);
            }
        }

        @Override
        @NotNull
        public ItemStack makeIcon() {
            return icon;
        }
    }
}

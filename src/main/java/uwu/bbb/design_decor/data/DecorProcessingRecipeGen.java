package uwu.bbb.design_decor.data;

import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import com.simibubi.create.foundation.utility.RegisteredObjects;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import uwu.bbb.design_decor.Decor;
import uwu.bbb.design_decor.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static uwu.bbb.design_decor.Decor.MOD_ID;

@SuppressWarnings("unused")
public abstract class DecorProcessingRecipeGen extends CreateRecipeProvider {

    protected static final List<DecorProcessingRecipeGen> GENERATORS = new ArrayList<>();


    public static void registerAll(DataGenerator gen) {
        //GENERATORS.add(new WashingRecipeGen(gen));

        gen.addProvider(true, new DataProvider() {

            @Override
            public @NotNull String getName() {
                return Decor.NAME + " Processing Recipes";
            }

            @SuppressWarnings("all")
            @Override
            public void run(@NotNull CachedOutput dc) {
                GENERATORS.forEach(g -> {
                    try {
                        g.run(dc);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    public DecorProcessingRecipeGen(DataGenerator generator) {
        super(generator);
    }

    /**
     * Create a processing recipe with a single itemstack ingredient, using its id
     * as the name of the recipe
     */
    @SuppressWarnings("SameParameterValue")
    protected <T extends ProcessingRecipe<?>> GeneratedRecipe create(String namespace,
                                                                     Supplier<ItemLike> singleIngredient, UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        ProcessingRecipeSerializer<T> serializer = getSerializer();
        GeneratedRecipe generatedRecipe = c -> {
            ItemLike itemLike = singleIngredient.get();
            transform
                    .apply(new ProcessingRecipeBuilder<>(serializer.getFactory(),
                            new ResourceLocation(namespace, RegisteredObjects.getKeyOrThrow(itemLike.asItem())
                                    .getPath())).withItemIngredients(Ingredient.of(itemLike)))
                    .build(c);
        };
        all.add(generatedRecipe);
        return generatedRecipe;
    }

    /**
     * Create a processing recipe with a single itemstack ingredient, using its id
     * as the name of the recipe
     */
    <T extends ProcessingRecipe<?>> GeneratedRecipe create(Supplier<ItemLike> singleIngredient,
                                                           UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        return create(MOD_ID, singleIngredient, transform);
    }


    protected <T extends ProcessingRecipe<?>> GeneratedRecipe createWithDeferredId(Supplier<ResourceLocation> name,
                                                                                   UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        ProcessingRecipeSerializer<T> serializer = getSerializer();
        GeneratedRecipe generatedRecipe =
                c -> transform.apply(new ProcessingRecipeBuilder<>(serializer.getFactory(), name.get()))
                        .build(c);
        all.add(generatedRecipe);
        return generatedRecipe;
    }

    /**
     * Create a new processing recipe, with recipe definitions provided by the
     * function
     */
    public <T extends ProcessingRecipe<?>> GeneratedRecipe create(ResourceLocation name,
                                                                  UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        return createWithDeferredId(() -> name, transform);
    }

    /**
     * Create a new processing recipe, with recipe definitions provided by the
     * function
     */
    public <T extends ProcessingRecipe<?>> GeneratedRecipe create(String name,
                                                                  UnaryOperator<ProcessingRecipeBuilder<T>> transform) {
        return create(Utils.asResource(name), transform);
    }

    protected abstract IRecipeTypeInfo getRecipeType();

    protected <T extends ProcessingRecipe<?>> ProcessingRecipeSerializer<T> getSerializer() {
        return getRecipeType().getSerializer();
    }

    @Override
    public @NotNull String getName() {
        return Decor.NAME + "'s Processing Recipes: " + getRecipeType().getId()
                .getPath();
    }




    //HELPER
    public CreateRecipeProvider.GeneratedRecipe convert(Block block, Block result) {
        return create(() -> block, b -> b.output(result));
    }

    public CreateRecipeProvider.GeneratedRecipe convert(Item item, Item result) {
        return create(() -> item, b -> b.output(result));
    }
    public CreateRecipeProvider.GeneratedRecipe convert(Supplier<ItemLike> item, Supplier<ItemLike> result) {
        return create(item, b -> b.output((ItemLike) result));
    }
    public CreateRecipeProvider.GeneratedRecipe convert(Item item, Item result, float chance) {
        return create(() -> item, b -> b.output(chance, result));
    }
    public CreateRecipeProvider.GeneratedRecipe convert(Item item, Item result1, float chance1, Item result2, float chance2) {
        return create(() -> item, b -> b.output(chance1, result1).output(chance2, result2));
    }
    public CreateRecipeProvider.GeneratedRecipe convert(Supplier<ItemLike> item, Supplier<ItemLike> result, float chance) {
        return create(item, b -> b.output(chance, (ItemLike) result));
    }

    public CreateRecipeProvider.GeneratedRecipe convert(ItemEntry<Item> item, ItemEntry<Item> result) {
        return create(item::get, b -> b.output(result::get));
    }

    public CreateRecipeProvider.GeneratedRecipe secondaryRecipe(Supplier<ItemLike> item, Supplier<ItemLike> first, Supplier<ItemLike> secondary,
                                                                float secondaryChance) {
        return create(item, b -> b.output(first.get(), 1)
                .output(secondaryChance, secondary.get(), 1));
    }

    public CreateRecipeProvider.GeneratedRecipe convertChanceRecipe(ItemLike item, ItemLike result, float chance) {
        return create(Utils.asResource(getItemName(result) + "_from_" + getItemName(item)), b -> b.withItemIngredients(Ingredient.of(item)).output(chance, result, 1));
    }
}
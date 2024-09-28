package uwu.bbb.design_decor.data;

import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import uwu.bbb.design_decor.Decor;
import uwu.bbb.design_decor.registry.DecorLangPartial;

import java.util.function.BiConsumer;

public class DecorDatagen {
    public static void gatherData(GatherDataEvent event) {
        addExtraRegistrateData();

        DataGenerator generator = event.getGenerator();

        if (event.includeServer()) {

            DecorProcessingRecipeGen.registerAll(generator);
        }
    }

    private static void addExtraRegistrateData() {
        DecorTags.addGenerators();

        Decor.REGISTRATE.addDataGenerator(ProviderType.LANG, provider -> {
            BiConsumer<String, String> langConsumer = provider::add;

            providePartialLang(langConsumer);
            providePonderLang();
        });
    }

    private static void providePartialLang(BiConsumer<String, String> consumer) {
        DecorLangPartial.provideLang(consumer);
    }

    private static void providePonderLang() {
    }
}

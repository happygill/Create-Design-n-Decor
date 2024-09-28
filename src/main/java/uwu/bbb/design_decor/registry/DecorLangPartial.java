package uwu.bbb.design_decor.registry;

import java.util.function.BiConsumer;

@SuppressWarnings({"unused"})
public class DecorLangPartial {
    public static void provideLang(BiConsumer<String, String> consumer) {

    }

    private static void consume(BiConsumer<String, String> consumer, String key, String enUS) {
        consumer.accept(key, enUS);
    }
}

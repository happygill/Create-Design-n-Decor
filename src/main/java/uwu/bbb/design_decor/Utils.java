package uwu.bbb.design_decor;

import com.simibubi.create.Create;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import uwu.bbb.design_decor.registry.helper.decor.ColorHelper;

import java.util.Random;

import static uwu.bbb.design_decor.Decor.MOD_ID;

@SuppressWarnings({"deprecation", "unused"})
public class Utils {
    public static final Random RANDOM = Create.RANDOM;

    public static boolean RANDOM_B = RANDOM.nextBoolean();
    public static int RANDOM_I = RANDOM.nextInt();
    public static double RANDOM_D = RANDOM.nextDouble();
    public static float RANDOM_F = RANDOM.nextFloat();
    public static long RANDOM_L = RANDOM.nextLong();

    public static double RANDOM_G = RANDOM.nextGaussian();
    public static double RANDOM_E = RANDOM.nextExponential();


    public static boolean randomChance(int chance) {
        int newChance = Mth.clamp(chance, 0, 100);
        return RANDOM.nextInt(1,  100) <= newChance;
    }

    public static boolean randomChance(int chance, Level level) {
        int newChance = Mth.clamp(chance, 0, 100);
        return level.getRandom().nextInt(1,  100) <= newChance;
    }

    public static boolean randomChance(double chance) {
        int newChance = Mth.clamp(((int) chance * 100), 0, 100);
        return RANDOM.nextInt(1,  100) <= newChance;
    }
    public static boolean randomChance(double chance, Level level) {
        int newChance = Mth.clamp(((int) chance * 100), 0, 100);
        return level.getRandom().nextInt(1,  100) <= newChance;
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static class Colors extends ColorHelper.DefaultColorProvider {}
}

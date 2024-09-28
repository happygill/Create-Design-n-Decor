package uwu.bbb.design_decor.registry.client;

import com.jozufozu.flywheel.core.PartialModel;
import uwu.bbb.design_decor.Utils;

public class DecorPartialModels {



    private static PartialModel block(String path) {
        return new PartialModel(Utils.asResource("block/" + path));
    }
    public static void init() {}
}

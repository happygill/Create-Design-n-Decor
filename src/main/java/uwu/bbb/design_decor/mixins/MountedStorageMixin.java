package uwu.bbb.design_decor.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.contraptions.MountedStorage;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.ItemStackHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.bbb.design_decor.content.blocks.storage_container.ColoredStorageContainerBlockEntity;

@Mixin(value = MountedStorage.class, remap = false)
public class MountedStorageMixin {

    @Shadow private BlockEntity blockEntity;
    @Shadow ItemStackHandler handler;
    @Shadow boolean valid;

    @Inject(at = @At("HEAD"), method = "canUseAsStorage(Lnet/minecraft/world/level/block/entity/BlockEntity;)Z", cancellable = true)
    private static void canUseAsStorage(BlockEntity be, CallbackInfoReturnable<Boolean> cir) {
        if (be instanceof ColoredStorageContainerBlockEntity)
            cir.setReturnValue(true);
    }

    @WrapOperation(method = "<init>(Lnet/minecraft/world/level/block/entity/BlockEntity;)V", at = @At(value = "CONSTANT", args = "classValue=com/simibubi/create/content/logistics/vault/ItemVaultBlockEntity"))
    private boolean MountedStorage(Object object, Operation<Boolean> original) {
        return original.call(object) || object instanceof ColoredStorageContainerBlockEntity;
    }

    @Inject(at = @At("HEAD"), method = "removeStorageFromWorld()V", cancellable = true)
    private void removeStorageFromWorld(CallbackInfo ci) {
        if (blockEntity instanceof ColoredStorageContainerBlockEntity) {
            handler = ((ColoredStorageContainerBlockEntity) blockEntity).getInventoryOfBlock();
            valid = true;
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "addStorageToWorld(Lnet/minecraft/world/level/block/entity/BlockEntity;)V", cancellable = true)
    private void addStorageToWorld(BlockEntity be, CallbackInfo ci) {
        if (be instanceof ColoredStorageContainerBlockEntity) {
            ((ColoredStorageContainerBlockEntity) be).applyInventoryToBlock(handler);
            ci.cancel();
        }
    }
}

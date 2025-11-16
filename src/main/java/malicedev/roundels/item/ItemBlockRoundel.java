package malicedev.roundels.item;

import malicedev.roundels.block.BlockLogicRoundel;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlock;
import net.minecraft.core.lang.I18n;
import org.jetbrains.annotations.NotNull;

public class ItemBlockRoundel extends ItemBlock<BlockLogicRoundel> {
	public ItemBlockRoundel(@NotNull Block<BlockLogicRoundel> block) {
		super(block);
	}

	@Override
	public String getTranslatedName(ItemStack itemstack) {
		int type = (itemstack.getMetadata() & BlockLogicRoundel.MASK_TYPE) >> 4;
		String typeKey = "";
		switch (type) {
			case 0:
				typeKey = "roundel.roundels.light.full";
				break;
			case 1:
				typeKey = "roundel.roundels.light.half";
				break;
			case 2:
				typeKey = "roundel.roundels.opaque.black.full";
				break;
			case 3:
				typeKey = "roundel.roundels.opaque.black.half";
				break;
			case 4:
				typeKey = "roundel.roundels.opaque.white.full";
				break;
			case 5:
				typeKey = "roundel.roundels.opaque.white.half";
				break;
			default:
				typeKey = "roundel.roundels.unknown";
				break;
		}
		return I18n.getInstance().translateNameKey(block.getLogic().baseBlock.getLanguageKey(block.getLogic().baseMetadata)) + " " + I18n.getInstance().translateNameKey(typeKey) + " " + I18n.getInstance().translateNameKey("tile.roundels.roundel");
	}
}

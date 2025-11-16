package malicedev.roundels;

import malicedev.roundels.block.model.BlockModelRoundel;
import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;

public class RoundelsModels implements ModelEntrypoint {
	@Override
	public void initBlockModels(BlockModelDispatcher blockModelDispatcher) {
		ModelHelper.setBlockModel(RoundelsBlocks.TEST, ()->new BlockModelRoundel(RoundelsBlocks.TEST));

	}

	@Override
	public void initItemModels(ItemModelDispatcher itemModelDispatcher) {

	}

	@Override
	public void initEntityModels(EntityRenderDispatcher entityRenderDispatcher) {

	}

	@Override
	public void initTileEntityModels(TileEntityRenderDispatcher tileEntityRenderDispatcher) {

	}

	@Override
	public void initBlockColors(BlockColorDispatcher blockColorDispatcher) {

	}
}

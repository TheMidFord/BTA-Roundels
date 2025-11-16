package malicedev.roundels;

import malicedev.roundels.block.BlockLogicRoundel;
import malicedev.roundels.item.ItemBlockRoundel;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.util.BlockInitEntrypoint;

public class RoundelsBlocks implements BlockInitEntrypoint {

	public static Block<BlockLogicRoundel> TEST;

	@Override
	public void afterBlockInit() {
		TEST = new BlockBuilder(Roundels.MOD_ID)
			.setBlockItem((block)->new ItemBlockRoundel((Block<BlockLogicRoundel>) block))
			.build("test", "test", RoundelsConfig.block("TEST"),
				(block) ->
					new BlockLogicRoundel(block, Material.stone, Blocks.STONE, 0)
			);
	}
}

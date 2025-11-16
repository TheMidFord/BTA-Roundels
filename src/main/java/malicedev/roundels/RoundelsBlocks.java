package malicedev.roundels;

import malicedev.roundels.block.BlockLogicRoundel;
import malicedev.roundels.item.ItemBlockRoundel;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.util.BlockInitEntrypoint;

public class RoundelsBlocks implements BlockInitEntrypoint {

	public static Block<BlockLogicRoundel> TEST;
	public static Block<BlockLogicRoundel> TEST2;

	@Override
	public void afterBlockInit() {
		TEST = makeRoundel("test", Material.stone, Blocks.LOG_OAK, 0);
		TEST2 = makeRoundel("test2", Material.cloth, Blocks.WOOL, 5);

		Roundels.LOGGER.info("Using {} IDs.", RoundelsConfig.currentId - RoundelsConfig.getStartingId());
	}

	public Block<BlockLogicRoundel> makeRoundel(String name, Material material, Block<?> baseBlock, int baseMetadata) {
		return new BlockBuilder(Roundels.MOD_ID)
			.setBlockItem((block)->new ItemBlockRoundel((Block<BlockLogicRoundel>) block))
			.build(name, name, RoundelsConfig.currentId++,
				(block) ->
					new BlockLogicRoundel(block, material, baseBlock, baseMetadata)
			);
	}
}

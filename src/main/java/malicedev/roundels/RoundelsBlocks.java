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

	@Override
	public void afterBlockInit() {
		TEST = makeRoundel("test","test","TEST", Material.stone, Blocks.LOG_OAK, 0);
	}

	public Block<BlockLogicRoundel> makeRoundel(String translationKey, String name, String configId, Material material, Block<?> baseBlock, int baseMetadata) {
		return new BlockBuilder(Roundels.MOD_ID)
			.setBlockItem((block)->new ItemBlockRoundel((Block<BlockLogicRoundel>) block))
			.build(translationKey, name, RoundelsConfig.block(configId),
				(block) ->
					new BlockLogicRoundel(block, material, baseBlock, baseMetadata)
			);
	}
}

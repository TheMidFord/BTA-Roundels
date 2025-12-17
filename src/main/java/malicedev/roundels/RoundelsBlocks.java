package malicedev.roundels;

import malicedev.roundels.block.BlockLogicRoundel;
import malicedev.roundels.item.ItemBlockRoundel;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.sound.BlockSound;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.util.BlockInitEntrypoint;

public class RoundelsBlocks implements BlockInitEntrypoint {

	public static Block<BlockLogicRoundel> TEST;
	public static Block<BlockLogicRoundel> TEST2;
	public static Block<BlockLogicRoundel> TEST3;

	@Override
	public void afterBlockInit() {
		TEST = makeRoundel("test", Material.wood, BlockSounds.WOOD, Blocks.LOG_OAK, 0);
		TEST2 = makeRoundel("test2", Material.cloth, BlockSounds.CLOTH, Blocks.WOOL, 5);
		TEST3 = makeRoundel("test3", Material.wood, BlockSounds.WOOD, Blocks.PLANKS_OAK, 0);

		Roundels.LOGGER.info("Using {} IDs.", RoundelsConfig.currentId - RoundelsConfig.getStartingId());
	}

	public Block<BlockLogicRoundel> makeRoundel(String name, Material material, BlockSound sound, Block<?> baseBlock, int baseMetadata) {
		return new BlockBuilder(Roundels.MOD_ID)
			.setBlockItem((block)->new ItemBlockRoundel((Block<BlockLogicRoundel>) block))
			.setBlockSound(sound)
			.build(name, name, RoundelsConfig.currentId++,
				(block) ->
					new BlockLogicRoundel(block, material, baseBlock, baseMetadata)
			);
	}
}

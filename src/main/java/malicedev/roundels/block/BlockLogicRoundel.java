package malicedev.roundels.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.core.block.BlockLogicFullyRotatable.directionToMeta;

public class BlockLogicRoundel extends BlockLogic {

	public static final int MASK_TYPE = 0b1111_0000;
	public static final int MASK_ALL_SIDES = 0b0000_1000;
	public static final int MASK_DIRECTION = 0b0000_0111;

	public final Block<?> baseBlock;
	public final int baseMetadata;

	public BlockLogicRoundel(Block<?> block, Material material, Block<?> baseBlock, int baseMetadata) {
		super(block, material);
		this.baseBlock = baseBlock;
		this.baseMetadata = baseMetadata;
	}

	@Override
	public int getPlacedBlockMetadata(@Nullable Player player, ItemStack stack, World world, int x, int y, int z, Side side, double xPlaced, double yPlaced) {
		return stack.getMetadata();
	}

	@Override
	public void onBlockPlacedByMob(World world, int x, int y, int z, @NotNull Side side, Mob mob, double xPlaced, double yPlaced) {
		final Direction direction = mob.getPlacementDirection(side).getOpposite();

		int metadata = world.getBlockMetadata(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z, metadata | directionToMeta(direction));
	}

	@Override
	public void onBlockPlacedOnSide(World world, int x, int y, int z, @NotNull Side side, double xPlaced, double yPlaced) {
		int metadata = world.getBlockMetadata(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z, metadata | directionToMeta(side.getDirection()));
	}

	/*@Override
	public String getLanguageKey(int meta) {

		return baseBlock.getLanguageKey(baseMetadata) + ;
	}*/
}

package malicedev.roundels.block.model;

import malicedev.roundels.block.BlockLogicRoundel;
import net.minecraft.client.render.Lighting;
import net.minecraft.client.render.block.model.BlockModel;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.WorldSource;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class BlockModelRoundel extends BlockModelStandard<BlockLogicRoundel> {

	public static final IconCoordinate[] TYPES = {
		TextureRegistry.getTexture("roundels:block/roundels_overlays/light_full"),
		TextureRegistry.getTexture("roundels:block/roundels_overlays/light_half"),
		TextureRegistry.getTexture("roundels:block/roundels_overlays/opaque_black_full"),
		TextureRegistry.getTexture("roundels:block/roundels_overlays/opaque_black_half"),
		TextureRegistry.getTexture("roundels:block/roundels_overlays/opaque_white_full"),
		TextureRegistry.getTexture("roundels:block/roundels_overlays/opaque_white_half"),
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null,
		null
	};

	public BlockModelRoundel(Block<BlockLogicRoundel> block) {
		super(block);
	}

	@Override
	public boolean render(Tessellator tessellator, int x, int y, int z) {
		boolean rendered = super.render(tessellator, x, y, z);
		BlockLogicRoundel logic = block.getLogic();
		WorldSource world = renderBlocks.blockAccess;
		int metadata = world.getBlockMetadata(x, y, z);
		int allSides = metadata & BlockLogicRoundel.MASK_ALL_SIDES;
		int type = (metadata & BlockLogicRoundel.MASK_TYPE) >> 4;
		if(type == 0 || type == 1){
			return rendered;
		}
		IconCoordinate tex = TYPES[type];
		renderBlocks.enableAO = false;
		if(allSides == 0b0000_1000){
			this.renderBottomFace(tessellator, block.getBounds(), x + Direction.DOWN.getOffsetX() / 1000F, y + Direction.DOWN.getOffsetY() / 1000F, z + Direction.DOWN.getOffsetZ() / 1000F, tex);
			this.renderTopFace(tessellator, block.getBounds(), x + Direction.UP.getOffsetX() / 1000F, y + Direction.UP.getOffsetY() / 1000F, z + Direction.UP.getOffsetZ() / 1000F, tex);
			this.renderNorthFace(tessellator, block.getBounds(), x + Direction.NORTH.getOffsetX() / 1000F, y + Direction.NORTH.getOffsetY() / 1000F, z + Direction.NORTH.getOffsetZ() / 1000F, tex);
			this.renderSouthFace(tessellator, block.getBounds(), x + Direction.SOUTH.getOffsetX() / 1000F, y + Direction.SOUTH.getOffsetY() / 1000F, z + Direction.SOUTH.getOffsetZ() / 1000F, tex);
			this.renderWestFace(tessellator, block.getBounds(), x + Direction.WEST.getOffsetX() / 1000F, y + Direction.WEST.getOffsetY() / 1000F, z + Direction.WEST.getOffsetZ() / 1000F, tex);
			this.renderEastFace(tessellator, block.getBounds(), x + Direction.EAST.getOffsetX() / 1000F, y + Direction.EAST.getOffsetY() / 1000F, z + Direction.EAST.getOffsetZ() / 1000F, tex);
		} else {
			int sideId = metadata & BlockLogicRoundel.MASK_DIRECTION;
			Side side = Side.getSideById(sideId);
			switch (side) {
				case BOTTOM:
					this.renderBottomFace(tessellator, block.getBounds(), x + Direction.DOWN.getOffsetX() / 1000F, y + Direction.DOWN.getOffsetY() / 1000F, z + Direction.DOWN.getOffsetZ() / 1000F, tex);
					break;
				case TOP:
					this.renderTopFace(tessellator, block.getBounds(), x + Direction.UP.getOffsetX() / 1000F, y + Direction.UP.getOffsetY() / 1000F, z + Direction.UP.getOffsetZ() / 1000F, tex);
					break;
				case NORTH:
					this.renderNorthFace(tessellator, block.getBounds(), x + Direction.NORTH.getOffsetX() / 1000F, y + Direction.NORTH.getOffsetY() / 1000F, z + Direction.NORTH.getOffsetZ() / 1000F, tex);
					break;
				case SOUTH:
					this.renderSouthFace(tessellator, block.getBounds(), x + Direction.SOUTH.getOffsetX() / 1000F, y + Direction.SOUTH.getOffsetY() / 1000F, z + Direction.SOUTH.getOffsetZ() / 1000F, tex);
					break;
				case WEST:
					this.renderWestFace(tessellator, block.getBounds(), x + Direction.WEST.getOffsetX() / 1000F, y + Direction.WEST.getOffsetY() / 1000F, z + Direction.WEST.getOffsetZ() / 1000F, tex);
					break;
				case EAST:
					this.renderEastFace(tessellator, block.getBounds(), x + Direction.EAST.getOffsetX() / 1000F, y + Direction.EAST.getOffsetY() / 1000F, z + Direction.EAST.getOffsetZ() / 1000F, tex);
					break;
			}
		}
		return true;
	}

	@Override
	public void renderBlockWithBounds(Tessellator tessellator, AABB bounds, int metadata, float brightness, float alpha, @Nullable Integer lightmapCoordinate) {
		super.renderBlockWithBounds(tessellator, bounds, metadata, brightness, alpha, lightmapCoordinate);
		BlockLogicRoundel logic = block.getLogic();
		WorldSource world = renderBlocks.blockAccess;
		int allSides = metadata & BlockLogicRoundel.MASK_ALL_SIDES;
		int type = (metadata & BlockLogicRoundel.MASK_TYPE) >> 4;
		IconCoordinate tex = TYPES[type];

		if(allSides == 0b0000_1000){
			tessellator.startDrawingQuads();

			tessellator.setNormal(0.0f, -1.0f, 0.0f);
			renderBottomFace(tessellator, bounds, 0.0, 0.0, 0.0, tex);

			tessellator.setNormal(0.0f, 1.0f, 0.0f);
			renderTopFace(tessellator, bounds, 0.0, 0.0, 0.0, tex);

			tessellator.setNormal(0.0f, 0.0f, -1.0f);
			renderNorthFace(tessellator, bounds, 0.0, 0.0, 0.0, tex);

			tessellator.setNormal(0.0f, 0.0f, 1.0f);
			renderSouthFace(tessellator, bounds, 0.0, 0.0, 0.0, tex);

			tessellator.setNormal(-1.0f, 0.0f, 0.0f);
			renderWestFace(tessellator, bounds, 0.0, 0.0, 0.0, tex);

			tessellator.setNormal(1.0f, 0.0f, 0.0f);
			renderEastFace(tessellator, bounds, 0.0, 0.0, 0.0, tex);

			tessellator.draw();
		} else {
			int sideId = metadata & BlockLogicRoundel.MASK_DIRECTION;
			Side side = Side.getSideById(sideId);
			tessellator.startDrawingQuads();
			if(sideId == 0){
				tessellator.setNormal(0.0f, 0.0f, -1.0f);
				renderSouthFace(tessellator, bounds, 0.0, 0.0, 0.0, tex);
				tessellator.draw();
				return;
			}
			switch (side) {
				case BOTTOM:
					tessellator.setNormal(0.0f, -1.0f, 0.0f);
					renderBottomFace(tessellator, bounds, 0.0, 0.0, 0.0, tex);
					break;
				case TOP:
					tessellator.setNormal(0.0f, 1.0f, 0.0f);
					renderTopFace(tessellator, bounds, 0.0, 0.0, 0.0, tex);
					break;
				case NORTH:
					tessellator.setNormal(0.0f, 0.0f, -1.0f);
					renderNorthFace(tessellator, bounds, 0.0, 0.0, 0.0, tex);
					break;
				case SOUTH:
					tessellator.setNormal(0.0f, 0.0f, 1.0f);
					renderSouthFace(tessellator, bounds, 0.0, 0.0, 0.0, tex);
					break;
				case WEST:
					tessellator.setNormal(-1.0f, 0.0f, 0.0f);
					renderWestFace(tessellator, bounds, 0.0, 0.0, 0.0, tex);
					break;
				case EAST:
					tessellator.setNormal(1.0f, 0.0f, 0.0f);
					renderEastFace(tessellator, bounds, 0.0, 0.0, 0.0, tex);
					break;
			}
			tessellator.draw();
		}

	}

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int data) {
		BlockLogicRoundel logic = block.getLogic();
		BlockModel<?> model = BlockModelDispatcher.getInstance().getDispatch(logic.baseBlock);
		return model.getBlockTextureFromSideAndMetadata(side, logic.baseMetadata);
	}

	@Override
	public IconCoordinate getBlockOverbrightTextureFromSideAndMeta(Side side, int metadata) {
		int allSides = metadata & BlockLogicRoundel.MASK_ALL_SIDES;
		int type = (metadata & BlockLogicRoundel.MASK_TYPE) >> 4;
		IconCoordinate tex = TYPES[type];
		if(type != 0 && type != 1) {
			return null;
		}
		if(allSides == 0b0000_1000) {
			return tex;
		} else {
			int sideId = metadata & BlockLogicRoundel.MASK_DIRECTION;
			Side texSide = Side.getSideById(sideId);
			if(texSide == side) {
				return tex;
			}
		}
		return null;
	}

	@Override
	public boolean shouldSideBeColored(WorldSource blockAccess, int x, int y, int z, int side, int meta) {
		return false;
	}

	@Override
	public boolean hasOverbright() {
		return true;
	}
}

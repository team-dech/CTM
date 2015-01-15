/**
 * All code has been provided by Team-Dech but WARDOGSK93 has ported it to 1.7.10
 */
package dechmods.ctm.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dechmods.ctm.ConnectedTexturesMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockGlassCT extends BlockCTBase
{
	public BlockGlassCT(String... types)
	{
		super(Material.glass, Block.soundTypeGlass, CreativeTabs.tabBlock, "glass", "glass", "woodGlass", "clearGlass");
	}

	//Methods to make this block act like vanilla glass

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 0;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderBlockPass()
	{
		return 0;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
		Block block = world.getBlock(x, y, z);

		if (this == Blocks.glass || this == Blocks.glass_pane || this == ConnectedTexturesMod.glass)
		{
			if (world.getBlockMetadata(x, y, z) != world.getBlockMetadata(x - Facing.offsetsXForSide[side], y - Facing.offsetsYForSide[side], z - Facing.offsetsZForSide[side]))
			{
				return true;
			}

			if (block == this)
			{
				return false;
			}
		}

		return !false && block == this ? false : super.shouldSideBeRendered(world, x, y, z, side);
	}
}

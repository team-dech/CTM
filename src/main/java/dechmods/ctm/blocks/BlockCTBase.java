/**
 * All code has been provided by Team-Dech but WARDOGSK93 has ported it to 1.7.10
 *
 * WARDOGSK93 Added a base block for other mod devs to use
 */
package dechmods.ctm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Base block for adding connected textures to a block
 *
 * extend this block to give your block connected textures
 */
public class BlockCTBase extends Block
{
	protected IIcon[][] textures;
	protected String cleanName;
	protected String[] types;
	protected int amount;

	public static int[] textureRefByID = { 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 16, 16, 20, 20, 16, 16, 28, 28, 21, 21, 46, 42, 21, 21, 43, 38, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 16, 16, 20, 20, 16, 16, 28, 28, 25, 25, 45, 37, 25, 25, 40, 32, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 7, 7, 24, 24, 7, 7, 10, 10, 29, 29, 44, 41, 29, 29, 39, 33, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 7, 7, 24, 24, 7, 7, 10, 10, 8, 8, 36, 35, 8, 8, 34, 11 };

	/**
	 * @param material
	 * The material to act like
	 * @param soundType
	 * The sound form the block on break and place
	 * @param creativeTab
	 * The creative tab to display on
	 * @param blockName
	 * The blocks base name (its code name)
	 * @param types
	 * The types of blocks (example the glass would be "new String[] {"glass", "woodGlass", "clearGlass"}" don't pass any and the blocks name will be used)
	 */
	public BlockCTBase(Material material, Block.SoundType soundType, CreativeTabs creativeTab, String blockName, String... types)
	{
		super(material);

		setStepSound(soundType);
		setCreativeTab(creativeTab);
		setBlockName(blockName);
		setHardness(0.3F);

		this.amount = types == null || types.length <= 0 ? 1 : types.length;
		this.types = amount <= 0 ? new String[] { cleanName } : types;
		this.cleanName = blockName;
	}

	@Override public void registerBlockIcons(IIconRegister register)
	{
		textures = new IIcon[amount][47];

		for(int meta = 0; meta < amount; meta++)
		{
			for(int i = 0; i < 47; i++)
			{
				textures[meta][i] = register.registerIcon("ctm:" + types[meta] + "/" + types[meta] + "_" + (i + 1));
			}
		}
	}

	@Override public IIcon getIcon(int side, int metaData)
	{
		return metaData < 3 ? textures[metaData][0] : textures[0][0];
	}

	@Override public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		boolean[] bitMatrix = new boolean[8];
		int thisMeta = world.getBlockMetadata(x, y, z);

		if (side == 0 || side == 1)
		{
			bitMatrix[0] = world.getBlock(x - 1, y, z - 1) == this && world.getBlockMetadata(x - 1, y, z - 1) == thisMeta;
			bitMatrix[1] = world.getBlock(x, y, z - 1) == this && world.getBlockMetadata(x, y, z - 1) == thisMeta;
			bitMatrix[2] = world.getBlock(x + 1, y, z - 1) == this && world.getBlockMetadata(x + 1, y, z - 1) == thisMeta;
			bitMatrix[3] = world.getBlock(x - 1, y, z) == this && world.getBlockMetadata(x - 1, y, z) == thisMeta;
			bitMatrix[4] = world.getBlock(x + 1, y, z) == this && world.getBlockMetadata(x + 1, y, z) == thisMeta;
			bitMatrix[5] = world.getBlock(x - 1, y, z + 1) == this && world.getBlockMetadata(x - 1, y, z + 1) == thisMeta;
			bitMatrix[6] = world.getBlock(x, y, z + 1) == this && world.getBlockMetadata(x, y, z + 1) == thisMeta;
			bitMatrix[7] = world.getBlock(x + 1, y, z + 1) == this && world.getBlockMetadata(x + 1, y, z + 1) == thisMeta;
		}
		if (side == 2 || side == 3)
		{
			bitMatrix[0] = world.getBlock(x + (side == 2 ? 1 : -1), y + 1, z) == this && world.getBlockMetadata(x + (side == 2 ? 1 : -1), y + 1, z) == thisMeta;
			bitMatrix[1] = world.getBlock(x, y + 1, z) == this && world.getBlockMetadata(x, y + 1, z) == thisMeta;
			bitMatrix[2] = world.getBlock(x + (side == 3 ? 1 : -1), y + 1, z) == this && world.getBlockMetadata(x + (side == 3 ? 1 : -1), y + 1, z) == thisMeta;
			bitMatrix[3] = world.getBlock(x + (side == 2 ? 1 : -1), y, z) == this && world.getBlockMetadata(x + (side == 2 ? 1 : -1), y, z) == thisMeta;
			bitMatrix[4] = world.getBlock(x + (side == 3 ? 1 : -1), y, z) == this && world.getBlockMetadata(x + (side == 3 ? 1 : -1), y, z) == thisMeta;
			bitMatrix[5] = world.getBlock(x + (side == 2 ? 1 : -1), y - 1, z) == this && world.getBlockMetadata(x + (side == 2 ? 1 : -1), y - 1, z) == thisMeta;
			bitMatrix[6] = world.getBlock(x, y - 1, z) == this && world.getBlockMetadata(x, y - 1, z) == thisMeta;
			bitMatrix[7] = world.getBlock(x + (side == 3 ? 1 : -1), y - 1, z) == this && world.getBlockMetadata(x + (side == 3 ? 1 : -1), y - 1, z) == thisMeta;
		}
		if (side == 4 || side == 5)
		{
			bitMatrix[0] = world.getBlock(x, y + 1, z + (side == 5 ? 1 : -1)) == this && world.getBlockMetadata(x, y + 1, z + (side == 5 ? 1 : -1)) == thisMeta;
			bitMatrix[1] = world.getBlock(x, y + 1, z) == this && world.getBlockMetadata(x, y + 1, z) == thisMeta;
			bitMatrix[2] = world.getBlock(x, y + 1, z + (side == 4 ? 1 : -1)) == this && world.getBlockMetadata(x, y + 1, z + (side == 4 ? 1 : -1)) == thisMeta;
			bitMatrix[3] = world.getBlock(x, y, z + (side == 5 ? 1 : -1)) == this && world.getBlockMetadata(x, y, z + (side == 5 ? 1 : -1)) == thisMeta;
			bitMatrix[4] = world.getBlock(x, y, z + (side == 4 ? 1 : -1)) == this && world.getBlockMetadata(x, y, z + (side == 4 ? 1 : -1)) == thisMeta;
			bitMatrix[5] = world.getBlock(x, y - 1, z + (side == 5 ? 1 : -1)) == this && world.getBlockMetadata(x, y - 1, z + (side == 5 ? 1 : -1)) == thisMeta;
			bitMatrix[6] = world.getBlock(x, y - 1, z) == this && world.getBlockMetadata(x, y - 1, z) == thisMeta;
			bitMatrix[7] = world.getBlock(x, y - 1, z + (side == 4 ? 1 : -1)) == this && world.getBlockMetadata(x, y - 1, z + (side == 4 ? 1 : -1)) == thisMeta;
		}

		int idBuilder = 0;

		for (int i = 0; i <= 7; i++)
		{
			idBuilder = idBuilder + (bitMatrix[i] ? (i == 0 ? 1 : (i == 1 ? 2 : (i == 2 ? 4 : (i == 3 ? 8 : (i == 4 ? 16 : (i == 5 ? 32 : (i == 6 ? 64 : 128))))))) : 0);
		}

		return idBuilder > 255 || idBuilder < 0 ? textures[thisMeta][0] : textures[thisMeta][textureRefByID[idBuilder]];
	}
}

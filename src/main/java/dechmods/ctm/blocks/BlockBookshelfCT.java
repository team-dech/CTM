/**
 * All code has been provided by Team-Dech but WARDOGSK93 has ported it to 1.7.10
 */
package dechmods.ctm.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockBookshelfCT extends BlockCTBase
{   
    public IIcon[] textures = new IIcon[4];

    public BlockBookshelfCT(Material material, SoundType soundType, CreativeTabs creativeTab, String blockName)
    {
        super(material, soundType, creativeTab, blockName, null);

        this.amount = 1;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegistry)
    {
        for (int i = 0; i < 4; i++)
        {
            textures[i] = iconRegistry.registerIcon("ctm:" + cleanName + "/" + cleanName + "_" + i);
        }
    }
    
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return side < 2 ? Blocks.planks.getIcon(0, 0) : textures[0];
    }
    
    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        boolean[] hasNeighbor = new boolean[2];
        
        if (side < 2) return Blocks.planks.getIcon(0, 0);
        
        if (side == 2 || side == 3)
        {
            hasNeighbor[0] = world.getBlock(x + (side == 2 ? 1 : -1), y, z) == this;
            hasNeighbor[1] = world.getBlock(x + (side == 3 ? 1 : -1), y, z) == this;
        }
        if (side == 4 || side == 5)
        {
            hasNeighbor[0] = world.getBlock(x, y, z + (side == 5 ? 1 : -1)) == this;
            hasNeighbor[1] = world.getBlock(x, y, z + (side == 4 ? 1 : -1)) == this;
        }
        
        return hasNeighbor[0] && hasNeighbor[1] ? textures[2] : (hasNeighbor[0] ? textures[3] : (hasNeighbor[1] ? textures[1] : textures[0]));
    }

    //Methods to make this block act like a vanilla bookshelf

    @Override
    public int quantityDropped(Random rand)
    {
        return 3;
    }

    @Override
    public Item getItemDropped(int i, Random rand, int j)
    {
        return Items.book;
    }
}

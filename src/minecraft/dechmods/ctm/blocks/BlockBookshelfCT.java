package dechmods.ctm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBookshelf;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class BlockBookshelfCT extends BlockBookshelf
{   
    public Icon[] textures = new Icon[4];
    
    public BlockBookshelfCT()
    {
        super(47);
        setHardness(1.5F);
        setStepSound(Block.soundWoodFootstep);
        setUnlocalizedName("bookshelf");
    }

    @Override
    public void registerIcons(IconRegister iconRegistry)
    {
        for (int i = 0; i < 4; i++)
            textures[i] = iconRegistry.registerIcon("ctm:bookshelf/bookshelf_" + i);
    }
    
    @Override
    public Icon getIcon(int side, int meta)
    {
        return side < 2 ? Block.planks.getIcon(0, 0) : textures[0];
    }
    
    @Override
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side)
    {
        boolean[] hasNeighbor = new boolean[2];
        
        if (side < 2) return Block.planks.getIcon(0, 0);
        
        if (side == 2 || side == 3)
        {
            hasNeighbor[0] = world.getBlockId(x + (side == 2 ? 1 : -1), y, z) == this.blockID;
            hasNeighbor[1] = world.getBlockId(x + (side == 3 ? 1 : -1), y, z) == this.blockID;
        }
        if (side == 4 || side == 5)
        {
            hasNeighbor[0] = world.getBlockId(x, y, z + (side == 5 ? 1 : -1)) == this.blockID;
            hasNeighbor[1] = world.getBlockId(x, y, z + (side == 4 ? 1 : -1)) == this.blockID;
        }
        
        return hasNeighbor[0] && hasNeighbor[1] ? textures[2] : (hasNeighbor[0] ? textures[3] : (hasNeighbor[1] ? textures[1] : textures[0]));
    }
}

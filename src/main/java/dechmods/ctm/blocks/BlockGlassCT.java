package dechmods.ctm.blocks;

import dechmods.ctm.ConnectedTexturesMod;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGlassCT extends BlockGlass
{
    public IIcon[][] textures = new IIcon[3][47];
    public static String[] types = new String[] { "glass", "woodGlass", "clearGlass" };
    
    public BlockGlassCT()
    {
        super(Material.glass, false);
        setHardness(0.3F);
        setStepSound(soundTypeGlass);
        setBlockName("glass");
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconRegistry)
    {
        for (int meta = 0; meta < 3; meta++)
            for (int i = 0; i < 47; i++)
                textures[meta][i] = iconRegistry.registerIcon("ctm:" + types[meta] + "/" + types[meta] + "_" + (i + 1));
    }
    
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return meta < 3 ? textures[meta][0] : textures[0][0];
    }
    
    @Override
    public int getDamageValue(World world, int x, int y, int z)
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
            idBuilder = idBuilder + (bitMatrix[i] ? (i == 0 ? 1 : (i == 1 ? 2 : (i == 2 ? 4 : (i == 3 ? 8 : (i == 4 ? 16 : (i == 5 ? 32 : (i == 6 ? 64 : 128))))))) : 0);
        
        return idBuilder > 255 || idBuilder < 0 ? textures[thisMeta][0] : textures[thisMeta][ConnectedTexturesMod.textureRefByID[idBuilder]];
    }
}

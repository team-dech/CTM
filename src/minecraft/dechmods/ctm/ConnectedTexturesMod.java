package dechmods.ctm;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import dechmods.ctm.blocks.BlockBookshelfCT;
import dechmods.ctm.blocks.BlockGlassCT;
import dechmods.ctm.blocks.ItemBlockGlass;

@Mod(modid = "ctm", name = "Connected Textures Mod", version = "0.3")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ConnectedTexturesMod
{
    public static Block glass, bookshelf;
    
    public static int[] textureRefByID = { 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 16, 16, 20, 20, 16, 16, 28, 28, 21, 21, 46, 42, 21, 21, 43, 38, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 16, 16, 20, 20, 16, 16, 28, 28, 25, 25, 45, 37, 25, 25, 40, 32, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 7, 7, 24, 24, 7, 7, 10, 10, 29, 29, 44, 41, 29, 29, 39, 33, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 7, 7, 24, 24, 7, 7, 10, 10, 8, 8, 36, 35, 8, 8, 34, 11 };
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        Block.blocksList[20] = null;
        glass = new BlockGlassCT();
        
        Item.itemsList[20] = null;
        Item.itemsList[20] = new ItemBlockGlass(20).setUnlocalizedName("glass");
        
        Block.blocksList[47] = null;
        bookshelf = new BlockBookshelfCT();
        
        LanguageRegistry.instance().addStringLocalization("tile.glass.name", "Glass");
        LanguageRegistry.instance().addStringLocalization("tile.glass.1.name", "Wood Encased Glass");
        LanguageRegistry.instance().addStringLocalization("tile.glass.2.name", "Clear Glass");
        
        GameRegistry.addShapelessRecipe(new ItemStack(glass.blockID, 1, 0), new ItemStack(glass, 1, Short.MAX_VALUE));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(glass.blockID, 8, 1), "GGG", "GWG", "GGG", 'G', glass, 'W', "plankWood"));
        FurnaceRecipes.smelting().addSmelting(glass.blockID, new ItemStack(glass.blockID, 1, 2), 0.2F);
    }
}
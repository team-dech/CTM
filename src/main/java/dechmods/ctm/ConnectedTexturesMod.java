/**
 * All code has been provided by Team-Dech but WARDOGSK93 has ported it to 1.7.10
 */
package dechmods.ctm;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dechmods.ctm.blocks.BlockBookshelfCT;
import dechmods.ctm.blocks.BlockGlassCT;
import dechmods.ctm.blocks.ItemBlockCTBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid = "ctm", name = "Connected Textures Mod", version = "1.0")
public class ConnectedTexturesMod
{
    public static Block glass;
    public static Block bookshelf;

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
		//TODO: find away to override the vanilla blocks but for now just add recipes to convert vanilla to modded

        //Create blocks
        glass = new BlockGlassCT();
        bookshelf = new BlockBookshelfCT(Material.wood, Block.soundTypeWood, CreativeTabs.tabBlock, "bookshelf");

        //Register blocks
        GameRegistry.registerBlock(bookshelf, "bookshelf_ctm");
        GameRegistry.registerBlock(glass, ItemBlockCTBase.class, "glass_ctm");

        //Add conversion recipes (vanilla -> modded and modded -> vanilla)
        GameRegistry.addShapelessRecipe(new ItemStack(glass, 1, Short.MAX_VALUE), new ItemStack(Blocks.glass, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.glass, 1), new ItemStack(glass, 1, Short.MAX_VALUE));
        GameRegistry.addShapelessRecipe(new ItemStack(bookshelf, 1), new ItemStack(Blocks.bookshelf, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.bookshelf, 1), new ItemStack(bookshelf, 1));

        //Add other recipes
        GameRegistry.addShapelessRecipe(new ItemStack(glass, 1, 0), new ItemStack(glass, 1, Short.MAX_VALUE));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(glass, 8, 1), "WWW", "WGW", "WWW", 'G', glass, 'W', "plankWood"));
        GameRegistry.addSmelting(glass, new ItemStack(glass, 1, 2), 0.2F);
    }
}
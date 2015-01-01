package dechmods.ctm;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dechmods.ctm.blocks.*;
import net.minecraft.block.Block;
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
		//TODO: find away to override the vanilla blocks
		
        glass = new BlockGlassCT();
        GameRegistry.registerBlock(glass, ItemBlockGlass.class, "glass_ctm");

        bookshelf = new BlockBookshelfCT();
        GameRegistry.registerBlock(bookshelf, "bookshelf_ctm");

        GameRegistry.addShapelessRecipe(new ItemStack(glass, 1, 0), new ItemStack(glass, 1, Short.MAX_VALUE));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(glass, 8, 1), "WWW", "WGW", "WWW", 'G', glass, 'W', "plankWood"));
        GameRegistry.addSmelting(glass, new ItemStack(glass, 1, 2), 0.2F);
    }
}
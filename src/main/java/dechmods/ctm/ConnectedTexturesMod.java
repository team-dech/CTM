/**
 * All code has been provided by Team-Dech but WARDOGSK93 has ported it to 1.7.10
 */
package dechmods.ctm;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import dechmods.ctm.blocks.BlockBookshelfCT;
import dechmods.ctm.blocks.BlockGlassCT;
import dechmods.ctm.blocks.ItemBlockCTBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid = "ctm", name = "Connected Textures Mod", version = "1.0", guiFactory = "dechmods.ctm.GuiFactory", canBeDeactivated = false)
public class ConnectedTexturesMod
{
    public static boolean enableGlass;
    public static boolean enableBookshelf;

    public static Configuration config;

    public static Block glass;
    public static Block bookshelf;

    private boolean notifyPlayerOfRestart = false;

    @EventHandler
    public void preLoad(FMLPreInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(this);

        config = new Configuration(event.getSuggestedConfigurationFile());

        config.setCategoryRequiresMcRestart(Configuration.CATEGORY_GENERAL, true);
        config.setCategoryComment(Configuration.CATEGORY_GENERAL, "All config values in this category will require you to restart Minecraft");

        syncConfig();
    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
		//TODO: find away to override the vanilla blocks but for now just add recipes to convert vanilla to modded

        //Create blocks
        if(enableGlass)
        {
            glass = new BlockGlassCT();

            //Register block
            GameRegistry.registerBlock(glass, ItemBlockCTBase.class, "glass_ctm");

            //Add conversion recipes (vanilla -> modded and modded -> vanilla)
            GameRegistry.addShapelessRecipe(new ItemStack(glass, 1, Short.MAX_VALUE), new ItemStack(Blocks.glass, 1));
            GameRegistry.addShapelessRecipe(new ItemStack(Blocks.glass, 1), new ItemStack(glass, 1, Short.MAX_VALUE));

            //Add other recipes
            GameRegistry.addShapelessRecipe(new ItemStack(glass, 1, 0), new ItemStack(glass, 1, Short.MAX_VALUE));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(glass, 8, 1), "WWW", "WGW", "WWW", 'G', glass, 'W', "plankWood"));
            GameRegistry.addSmelting(glass, new ItemStack(glass, 1, 2), 0.2F);
        }
        if(enableBookshelf)
        {
            bookshelf = new BlockBookshelfCT(Material.wood, Block.soundTypeWood, CreativeTabs.tabBlock, "bookshelf");

            //Register block
            GameRegistry.registerBlock(bookshelf, "bookshelf_ctm");

            //Add conversion recipes (vanilla -> modded and modded -> vanilla)
            GameRegistry.addShapelessRecipe(new ItemStack(bookshelf, 1), new ItemStack(Blocks.bookshelf, 1));
            GameRegistry.addShapelessRecipe(new ItemStack(Blocks.bookshelf, 1), new ItemStack(bookshelf, 1));
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.modID.equals("ctm"))
        {
            syncConfig();
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        if(notifyPlayerOfRestart)//Tell the player if any changes require a minecraft restart
        {
            EntityPlayer player = event.player;
            Side side = FMLCommonHandler.instance().getSide();

            player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[" + EnumChatFormatting.DARK_PURPLE +  "CTM" + EnumChatFormatting.AQUA + "]" + EnumChatFormatting.WHITE + " A restart is pending please restart your client in order for your changes to take effect"));
        }
    }

    private void syncConfig()
    {
        enableBookshelf = config.getBoolean("Enable Bookshelf Block", Configuration.CATEGORY_GENERAL, true, "Enables connected textures on the Bookshelf block (requires Minecarft restart)");
        enableGlass = config.getBoolean("Enable Glass Block", Configuration.CATEGORY_GENERAL, true, "Enables connected textures on the Glass (requires Minecarft restart)");

        if(config.hasChanged())
        {
            notifyPlayerOfRestart = true;
            config.save();
        }
        else notifyPlayerOfRestart = false;
    }
}
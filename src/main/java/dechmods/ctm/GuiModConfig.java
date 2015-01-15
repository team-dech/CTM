package dechmods.ctm;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class GuiModConfig extends GuiConfig
{
	public GuiModConfig(GuiScreen parentScreen)
	{
		super(parentScreen,
				new ConfigElement(ConnectedTexturesMod.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				"ctm",
				true,
				true,
				GuiConfig.getAbridgedConfigPath(ConnectedTexturesMod.config.toString()));
	}
}

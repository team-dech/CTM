package dechmods.ctm.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlockGlass extends ItemBlock
{
    public ItemBlockGlass(Block block)
    {
        super(block);
        setHasSubtypes(true);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        if (stack.getItemDamage() == 0) return "tile.glass";
        return stack.getItemDamage() < 3 ? "tile.glass." + stack.getItemDamage() : "tile.glass";
    }
    
    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }
    
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List itemList)
    {
        itemList.add(new ItemStack(item, 1, 0));
        itemList.add(new ItemStack(item, 1, 1));
        itemList.add(new ItemStack(item, 1, 2));
        itemList.add(new ItemStack(item, 1, 3));
    }
}

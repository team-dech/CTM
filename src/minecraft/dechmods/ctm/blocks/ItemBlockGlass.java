package dechmods.ctm.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockGlass extends ItemBlock
{
    public ItemBlockGlass(int id)
    {
        super(id - 256);
        setHasSubtypes(true);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        if (stack.getItemDamage() == 0) return super.getUnlocalizedName();
        return stack.getItemDamage() < 3 ? super.getUnlocalizedName() + "." + stack.getItemDamage() : super.getUnlocalizedName() + "." + 1;
    }
    
    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }
    
    @Override
    public void getSubItems(int id, CreativeTabs tab, List itemList)
    {
        itemList.add(new ItemStack(id, 1, 0));
        itemList.add(new ItemStack(id, 1, 1));
        itemList.add(new ItemStack(id, 1, 2));
    }
}

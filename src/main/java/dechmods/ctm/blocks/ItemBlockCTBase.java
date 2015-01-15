/**
 * All code has been provided by Team-Dech but WARDOGSK93 has ported it to 1.7.10
 */
package dechmods.ctm.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlockCTBase extends ItemBlock
{
    protected BlockCTBase block;

    public ItemBlockCTBase(Block block)
    {
        super(block);
        setHasSubtypes(true);

        this.block = (BlockCTBase) field_150939_a;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        if (stack.getItemDamage() == 0) return "tile." + block.cleanName;
        return stack.getItemDamage() < 3 ? "tile." + block.cleanName + "." + stack.getItemDamage() : "tile." + block.cleanName;
    }
    
    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }
    
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List itemList)
    {
        for(int i = 0; i < block.amount; i++)
        {
            itemList.add(new ItemStack(item, 1, i));
        }
    }
}

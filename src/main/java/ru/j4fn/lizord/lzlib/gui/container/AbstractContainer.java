package ru.j4fn.lizord.lzlib.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.j4fn.lizord.lzlib.blocks.AbstractContainerTileEntity;

public abstract class AbstractContainer extends Container {
    AbstractContainerTileEntity te;

    public AbstractContainer(InventoryPlayer playerInventory, AbstractContainerTileEntity te){
        this.te = te;
        addPlayerSlots(playerInventory);
        addOwnSlots();
    }

    public abstract void addOwnSlots();

    public void addPlayerSlots(InventoryPlayer inv){
        for (int l = 0; l < 3; ++l) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.addSlotToContainer(new Slot(inv, j1 + (l + 1) * 9, 8 + j1 * 18, 84 + l * 18));
            }
        }
        for (int i1 = 0; i1 < 9; ++i1) {
            this.addSlotToContainer(new Slot(inv, i1, 8 + i1 * 18, 142));
        }
    }
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < te.getSize()) {
                if (!this.mergeItemStack(itemstack1, te.getSize(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, te.getSize(), false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return te.canInteractWith(playerIn);
    }
}

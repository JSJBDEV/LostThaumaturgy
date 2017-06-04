package com.pengu.lostthaumaturgy.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.pengu.lostthaumaturgy.inventory.slot.SlotOutput;
import com.pengu.lostthaumaturgy.tile.TileInfuserDark;

public class ContainerInfuserDark extends Container
{
	public final TileInfuserDark tile;
	
	public ContainerInfuserDark(TileInfuserDark tile, EntityPlayer player)
	{
		if(tile.sucked <= 0F)
			tile.initiator = player.getGameProfile().getId();
		
		this.tile = tile;
		
		addSlotToContainer(new Slot(tile.infuserItemStacks, 1, 80, 16));
		addSlotToContainer(new Slot(tile.infuserItemStacks, 2, 132, 54));
		addSlotToContainer(new Slot(tile.infuserItemStacks, 3, 111, 118));
		addSlotToContainer(new Slot(tile.infuserItemStacks, 4, 48, 118));
		addSlotToContainer(new Slot(tile.infuserItemStacks, 5, 25, 54));
		addSlotToContainer(new SlotOutput(tile.infuserItemStacks, 0, 80, 72));
		for(int j = 0; j < 9; ++j)
			addSlotToContainer(new Slot(player.inventory, j, 8 + j * 18, 216));
		for(int i = 0; i < 3; ++i)
			for(int k = 0; k < 9; ++k)
				addSlotToContainer(new Slot(player.inventory, k + i * 9 + 9, 8 + k * 18, 158 + i * 18));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return tile.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = getSlot(index);
		if(slot != null)
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if(index <= 5 && !mergeItemStack(itemstack1, 6, inventorySlots.size(), false))
				return ItemStack.EMPTY;
			if(index > 5 && !mergeItemStack(itemstack1, 0, 5, false))
				return ItemStack.EMPTY;
			if(!itemstack1.isEmpty())
				slot.onSlotChanged();
			if(itemstack1.getCount() != itemstack.getCount())
				slot.putStack(itemstack1);
			else
				return ItemStack.EMPTY;
		}
		return itemstack;
	}
}
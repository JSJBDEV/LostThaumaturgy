package com.pengu.lostthaumaturgy.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import com.mrdimka.hammercore.client.utils.RenderUtil;
import com.pengu.lostthaumaturgy.LTInfo;
import com.pengu.lostthaumaturgy.inventory.ContainerDarkGenerator;
import com.pengu.lostthaumaturgy.tile.TileDarknessGenerator;

public class GuiDarkGenerator extends GuiContainer
{
	public final TileDarknessGenerator tile;
	public final ResourceLocation gui = new ResourceLocation(LTInfo.MOD_ID, "textures/gui/gui_dark_gen.png");
	
	public GuiDarkGenerator(TileDarknessGenerator tile, InventoryPlayer player)
	{
		super(new ContainerDarkGenerator(tile, player));
		this.tile = tile;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		mc.getTextureManager().bindTexture(gui);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		float progress = ((float) tile.progress.get() / 90000F) * 64F;
		RenderUtil.drawTexturedModalRect(guiLeft + 56, guiTop + 64, 192, 64, progress, 8);
		
		int light = mc.world.getLight(tile.getPos());
		drawTexturedModalRect(guiLeft + 80, guiTop + 24, 176, light * 16, 16, 16);
		
		int moon = tile.getWorld().getMoonPhase();
		drawTexturedModalRect(guiLeft + 84, guiTop + 44, 192, moon * 8, 8, 8);
	}
}
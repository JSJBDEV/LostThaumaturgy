package com.pengu.lostthaumaturgy.net.zap;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.pengu.hammercore.HammerCore;
import com.pengu.hammercore.net.packetAPI.IPacket;
import com.pengu.hammercore.net.packetAPI.IPacketListener;

public class PacketSpawnGeneratorZap implements IPacket, IPacketListener<PacketSpawnGeneratorZap, IPacket>
{
	public Vec3d start, end;
	
	public PacketSpawnGeneratorZap(Vec3d start, Vec3d end)
	{
		this.start = start;
		this.end = end;
	}
	
	public PacketSpawnGeneratorZap()
	{
	}
	
	@Override
	public IPacket onArrived(PacketSpawnGeneratorZap packet, MessageContext context)
	{
		if(context.side == Side.CLIENT)
			packet.spawn();
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public void spawn()
	{
		HammerCore.particleProxy.spawnSlowZap(Minecraft.getMinecraft().world, start, end, Color.CYAN.getRGB(), 20, .15F);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setDouble("sx", start.x);
		nbt.setDouble("sy", start.y);
		nbt.setDouble("sz", start.z);
		nbt.setDouble("ex", end.x);
		nbt.setDouble("ey", end.y);
		nbt.setDouble("ez", end.z);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		start = new Vec3d(nbt.getDouble("sx"), nbt.getDouble("sy"), nbt.getDouble("sz"));
		end = new Vec3d(nbt.getDouble("ex"), nbt.getDouble("ey"), nbt.getDouble("ez"));
	}
}
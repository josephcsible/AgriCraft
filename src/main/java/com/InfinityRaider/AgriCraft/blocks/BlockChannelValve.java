package com.InfinityRaider.AgriCraft.blocks;

import com.InfinityRaider.AgriCraft.AgriCraft;
import com.InfinityRaider.AgriCraft.creativetab.AgriCraftTab;
import com.InfinityRaider.AgriCraft.reference.Constants;
import com.InfinityRaider.AgriCraft.tileentity.TileEntityValve;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockChannelValve extends BlockContainer {

    public BlockChannelValve() {
        super(Material.wood);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        setHarvestLevel("axe", 0);
        this.setCreativeTab(AgriCraftTab.agriCraftTab);
        this.setBlockBounds(4*Constants.unit, 0, 4*Constants.unit, 12*Constants.unit, 1, 12*Constants.unit);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!world.isRemote) {
            TileEntity te = world.getTileEntity(x, y, z);
            if(te !=null && te instanceof TileEntityValve) {
                TileEntityValve valve = (TileEntityValve) te;
                boolean wasPowered = valve.isPowered();
                boolean isPowered = world.isBlockIndirectlyGettingPowered(x, y, z);
                if(isPowered!=wasPowered) {
                    valve.setPowered(isPowered);
                    valve.markDirty();
                }
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityValve();
    }

    @Override
    public int getRenderType() {
        return AgriCraft.proxy.getRenderId(Constants.valveId);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int i) {
        return true;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return Blocks.planks.getIcon(0, 0);
    }
}
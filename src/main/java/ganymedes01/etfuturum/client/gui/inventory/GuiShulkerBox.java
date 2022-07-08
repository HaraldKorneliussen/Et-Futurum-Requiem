package ganymedes01.etfuturum.client.gui.inventory;

import org.lwjgl.opengl.GL11;

import ganymedes01.etfuturum.inventory.ContainerChestGeneric;
import ganymedes01.etfuturum.tileentities.TileEntityShulkerBox;
import ganymedes01.etfuturum.tileentities.TileEntityShulkerBox.ShulkerBoxType;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiShulkerBox extends GuiContainer {
	
	private static final ResourceLocation 
		backgroundCopper = new ResourceLocation("etfuturum:textures/gui/container/ironshulkerbox/coppercontainer.png"),
		backgroundIron = new ResourceLocation("etfuturum:textures/gui/container/ironshulkerbox/ironcontainer.png"),
		backgroundSilver = new ResourceLocation("etfuturum:textures/gui/container/ironshulkerbox/silvercontainer.png"),
		backgroundGold = new ResourceLocation("etfuturum:textures/gui/container/ironshulkerbox/goldcontainer.png"),
		backgroundDiamond = new ResourceLocation("etfuturum:textures/gui/container/ironshulkerbox/diamondcontainer.png");
	private IInventory upperChestInventory;
	private IInventory lowerChestInventory;
	/** window height is calculated with these values; the more rows, the heigher */
	private int inventoryRows;
	
	public GuiShulkerBox(IInventory p_i1083_1_, IInventory p_i1083_2_) {
		super(new ContainerChestGeneric(p_i1083_1_, p_i1083_2_, ((TileEntityShulkerBox)p_i1083_2_).getRowSize()));
		this.upperChestInventory = p_i1083_1_;
		this.lowerChestInventory = p_i1083_2_;
		
		ShulkerBoxType type = ((TileEntityShulkerBox)p_i1083_2_).type;
		this.xSize = type.getXSize();
		this.ySize = type.getYSize();
		this.allowUserInput = false;
		this.inventoryRows = p_i1083_2_.getSizeInventory() / type.getRowSize();
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		this.fontRendererObj.drawString(this.lowerChestInventory.hasCustomInventoryName() ? this.lowerChestInventory.getInventoryName() : I18n.format(this.lowerChestInventory.getInventoryName(), new Object[0]), 8, 6, 4210752);
		this.fontRendererObj.drawString(this.upperChestInventory.hasCustomInventoryName() ? this.upperChestInventory.getInventoryName() : I18n.format(this.upperChestInventory.getInventoryName(), new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(backgroundDiamond);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
		this.drawTexturedModalRect(k, l + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
	}
}

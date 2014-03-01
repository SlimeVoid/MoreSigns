package com.slimevoid.moresigns.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.slimevoid.moresigns.core.MSBlocks;
import com.slimevoid.moresigns.core.lib.BlockLib;
import com.slimevoid.moresigns.tileentities.TileEntitySign;


public class TileEntitySignRenderer extends TileEntitySpecialRenderer {
	private SignModel	mtsSignModel;

	public TileEntitySignRenderer() {
		mtsSignModel = new SignModel();
	}

	public void renderTileEntitySignAt(TileEntitySign mtstileentitysign, double d, double d1, double d2, float f, ResourceLocation texture) {
		Block block = mtstileentitysign.getBlockType();
		GL11.glPushMatrix();
		float f1 = 0.6666667F;
		if (block == MSBlocks.mtSignPost.me) {
			GL11.glTranslatef(	(float) d + 0.5F,
								(float) d1 + 0.75F * f1,
								(float) d2 + 0.5F);
			float f2 = (mtstileentitysign.getBlockMetadata() * 360) / 16F;
			GL11.glRotatef(	-f2,
							0.0F,
							1.0F,
							0.0F);
			mtsSignModel.mtsSignStick.showModel = true;
		} else {
			int i = mtstileentitysign.getBlockMetadata();
			float f3 = 0.0F;
			if (i == 2) {
				f3 = 180F;
			}
			if (i == 4) {
				f3 = 90F;
			}
			if (i == 5) {
				f3 = -90F;
			}
			GL11.glTranslatef(	(float) d + 0.5F,
								(float) d1 + 0.75F * f1,
								(float) d2 + 0.5F);
			GL11.glRotatef(	-f3,
							0.0F,
							1.0F,
							0.0F);
			GL11.glTranslatef(	0.0F,
								-0.3125F,
								-0.4375F);
			mtsSignModel.mtsSignStick.showModel = false;
		}
		this.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glScalef(	f1,
						-f1,
						-f1);
		mtsSignModel.renderSign();
		GL11.glPopMatrix();
		FontRenderer fontrenderer = getFontRenderer();
		float f4 = 0.01666667F * f1;
		GL11.glTranslatef(	0.0F,
							0.5F * f1,
							0.07F * f1);
		GL11.glScalef(	f4,
						-f4,
						f4);
		GL11.glNormal3f(0.0F,
						0.0F,
						-1F * f4);
		GL11.glDepthMask(false);
		int j = 0;
		for (int k = 0; k < mtstileentitysign.signText.length; k++) {
			String s = mtstileentitysign.signText[k];
			if (k == mtstileentitysign.mtsLineBeingEdited) {
				s = (new StringBuilder()).append("> ").append(s).append(" <").toString();
				fontrenderer.drawString(s,
										-fontrenderer.getStringWidth(s) / 2,
										k
												* 10
												- mtstileentitysign.signText.length
												* 5,
										j);
			} else {
				fontrenderer.drawString(s,
										-fontrenderer.getStringWidth(s) / 2,
										k
												* 10
												- mtstileentitysign.signText.length
												* 5,
										j);
			}
		}

		GL11.glDepthMask(true);
		GL11.glColor4f(	1.0F,
						1.0F,
						1.0F,
						1.0F);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity mtstileentity, double d, double d1, double d2, float f) {
		if (mtstileentity instanceof TileEntitySign) {
			TileEntitySign mtstileentitysign = (TileEntitySign) mtstileentity;
			if (mtstileentitysign != null) {
				ResourceLocation signTexture = BlockLib.getSignTexture(mtstileentitysign.getTexture());
				renderTileEntitySignAt(	mtstileentitysign,
										d,
										d1,
										d2,
										f,
										signTexture);
			}
		}
	}
}

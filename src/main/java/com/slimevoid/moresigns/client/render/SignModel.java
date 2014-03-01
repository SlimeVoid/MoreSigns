package com.slimevoid.moresigns.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class SignModel extends ModelBase {
	public ModelRenderer	mtsSignBoard;
	public ModelRenderer	mtsSignStick;

	public SignModel() {
		mtsSignBoard = new ModelRenderer(this, 0, 0);
		mtsSignBoard.addBox(-12F,
							-14F,
							-1F,
							24,
							12,
							2,
							0.0F);
		mtsSignStick = new ModelRenderer(this, 0, 14);
		mtsSignStick.addBox(-1F,
							-2F,
							-1F,
							2,
							14,
							2,
							0.0F);
	}

	public void renderSign() {
		mtsSignBoard.render(0.0625F);
		mtsSignStick.render(0.0625F);
	}
}

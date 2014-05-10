package net.slimevoid.moresigns.client.guis;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ChatAllowedCharacters;
import net.slimevoid.library.util.helpers.PacketHelper;
import net.slimevoid.moresigns.core.MSBlocks;
import net.slimevoid.moresigns.network.packets.PacketUpdateSign;
import net.slimevoid.moresigns.tileentities.TileEntitySign;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class GuiEditSign extends GuiScreen {
    private Minecraft           mc = FMLClientHandler.instance().getClient();
    protected String            mtsScreenTitle;
    private TileEntitySign      mtsEntitySign;
    private int                 updateCounter;
    private int                 mtsEditLine;
    private static final char[] allowedCharacters;

    public GuiEditSign(TileEntitySign mtstileentitysign) {
        mtsScreenTitle = "Edit sign message:";
        mtsEditLine = 0;
        mtsEntitySign = mtstileentitysign;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 120, "Done"));
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        PacketHelper.sendToServer(new PacketUpdateSign(mtsEntitySign));
    }

    @Override
    public void updateScreen() {
        updateCounter++;
    }

    @Override
    protected void actionPerformed(GuiButton guibutton) {
        if (!guibutton.enabled) {
            return;
        }
        if (guibutton.id == 0) {
            mtsEntitySign.onInventoryChanged();
            mc.displayGuiScreen(null);
        }
    }

    @Override
    protected void keyTyped(char c, int i) {
        if (i == 200) {
            mtsEditLine = mtsEditLine - 1 & 3;
        }
        if (i == 208 || i == 28) {
            mtsEditLine = mtsEditLine + 1 & 3;
        }
        if (i == 14 && mtsEntitySign.signText[mtsEditLine].length() > 0) {
            mtsEntitySign.signText[mtsEditLine] = mtsEntitySign.signText[mtsEditLine].substring(0,
                                                                                                mtsEntitySign.signText[mtsEditLine].length() - 1);
        }
        if (ChatAllowedCharacters.isAllowedCharacter(c)
            && mtsEntitySign.signText[mtsEditLine].length() < 15) {
            mtsEntitySign.signText[mtsEditLine] += c;
        }
    }

    @Override
    public void drawScreen(int i, int j, float f) {
        drawDefaultBackground();
        drawCenteredString(this.fontRendererObj,
                           mtsScreenTitle,
                           width / 2,
                           40,
                           0xffffff);
        GL11.glPushMatrix();
        GL11.glTranslatef(width / 2,
                          0.0F,
                          50F);
        float f1 = 93.75F;
        GL11.glScalef(-f1,
                      -f1,
                      -f1);
        GL11.glRotatef(180F,
                       0.0F,
                       1.0F,
                       0.0F);
        Block block = mtsEntitySign.getBlockType();
        if (block == MSBlocks.mtSignPost.me) {
            float f2 = (mtsEntitySign.getBlockMetadata() * 360) / 16F;
            GL11.glRotatef(f2,
                           0.0F,
                           1.0F,
                           0.0F);
            GL11.glTranslatef(0.0F,
                              -1.0625F,
                              0.0F);
        } else {
            int k = mtsEntitySign.getBlockMetadata();
            float f3 = 0.0F;
            if (k == 2) {
                f3 = 180F;
            }
            if (k == 4) {
                f3 = 90F;
            }
            if (k == 5) {
                f3 = -90F;
            }
            GL11.glRotatef(f3,
                           0.0F,
                           1.0F,
                           0.0F);
            GL11.glTranslatef(0.0F,
                              -1.0625F,
                              0.0F);
        }
        if ((updateCounter / 6) % 2 == 0) {
            mtsEntitySign.mtsLineBeingEdited = mtsEditLine;
        }
        TileEntityRendererDispatcher.instance.renderTileEntityAt(mtsEntitySign,
                                                                 -0.5D,
                                                                 -0.75D,
                                                                 -0.5D,
                                                                 0.0F);
        mtsEntitySign.mtsLineBeingEdited = -1;
        GL11.glPopMatrix();
        super.drawScreen(i,
                         j,
                         f);
    }

    static {
        allowedCharacters = ChatAllowedCharacters.allowedCharacters;
    }
}
package com.takenokoshi.mekut.gui.misc;

import org.jetbrains.annotations.NotNull;

import com.takenokoshi.mekaddonlib.gui.GuiMekALMultiPageMachine;
import com.takenokoshi.mekaddonlib.inventory.container.MekALMultiPageContainer;
import com.takenokoshi.mekut.blockentity.misc.BEUniversalStorage;

import mekanism.client.gui.element.gauge.GaugeType;
import mekanism.client.gui.element.gauge.GuiChemicalGauge;
import mekanism.client.gui.element.gauge.GuiEnergyGauge;
import mekanism.client.gui.element.gauge.GuiFluidGauge;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class GuiUniversalStorage extends GuiMekALMultiPageMachine<BEUniversalStorage> {

    public GuiUniversalStorage(MekALMultiPageContainer<BEUniversalStorage> container, Inventory inv,
            Component title) {
        super(container, inv, title);
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiChemicalGauge(tile::getChemicalTank, () -> tile.getChemicalTanks(null),
                GaugeType.STANDARD, this, 25, 10));
        addRenderableWidget(new GuiEnergyGauge(tile.getEnergyContainer(),
                GaugeType.SMALL_MED, this, 241, 10));
        addRenderableWidget(new GuiFluidGauge(tile::getFluidTank, () -> tile.getFluidTanks(null),
                GaugeType.STANDARD, this, 241, 64));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        renderInventoryText(guiGraphics);
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }

}

package com.takenokoshi.mekut.registries;

import java.util.EnumMap;
import java.util.Map;

import com.jerry.genextras.common.config.GeneratorsExtraConfig;
import com.jerry.mekextras.api.ExtraUpgrade;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeUpgradeable;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.takenokoshi.mekaddonlib.registration.GuiSizedMachineRegistryObject;
import com.takenokoshi.mekaddonlib.registration.MachineDeferredRegister;
import com.takenokoshi.mekut.blockentity.abs.BEAbstractCompactFusionReactor;
import com.takenokoshi.mekut.blockentity.factory.BEEnergizedSmeltingFactory;
import com.takenokoshi.mekut.blockentity.factory.BEExtraEnergizedSmeltingFactory;
import com.takenokoshi.mekut.blockentity.machine.BECompactNaquadahReactor;
import com.takenokoshi.mekut.core.MekUtConstants;

import mekanism.api.Upgrade;
import mekanism.common.attachments.component.AttachedSideConfig;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.config.MekanismConfig;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.tier.FactoryTier;
import mekanism.generators.common.registries.GeneratorsSounds;

public class MekUtExtrasMachines {
    public static final MachineDeferredRegister MACHINES = new MachineDeferredRegister(MekUtConstants.MODID);

    public static final GuiSizedMachineRegistryObject<BECompactNaquadahReactor> COMPACT_NAQUADAH_REACTOR = MACHINES
            .registerGuiSized("compact_naquadah_reactor",
                    BEAbstractCompactFusionReactor.SIDE_CONFIG,
                    BECompactNaquadahReactor
                            .getContainerAdder(GeneratorsExtraConfig.extraGenerators.reactorFuelCapacity)::accept,
                    BECompactNaquadahReactor::new,
                    builder -> builder
                            .withSimple(Capabilities.LASER_RECEPTOR),
                    BECompactNaquadahReactor.class,
                    builder -> builder
                            .withSideConfig(TransmissionType.values())
                            .withSound(GeneratorsSounds.FUSION_REACTOR)
                            .withSupportedUpgrades(Upgrade.MUFFLING));

    public static final Map<ExtraFactoryTier, GuiSizedMachineRegistryObject<BEExtraEnergizedSmeltingFactory>> EXTRA_SMELTING_FACTORIES = new EnumMap<>(
            ExtraFactoryTier.class);
    static {
        final ExtraFactoryTier[] tiers = new ExtraFactoryTier[] {
                ExtraFactoryTier.ABSOLUTE, ExtraFactoryTier.SUPREME, ExtraFactoryTier.COSMIC, ExtraFactoryTier.INFINITE,
        };
        for (final ExtraFactoryTier tier : tiers) {
            EXTRA_SMELTING_FACTORIES.put(tier, MACHINES.registerGuiSized(
                    tier.getAdvanceTier().getLowerName() + "_smelting_factory",
                    AttachedSideConfig.CHEMICAL_OUT_MACHINE,
                    BEEnergizedSmeltingFactory.getContainerAdder(tier.processes)::accept,
                    BEExtraEnergizedSmeltingFactory::new,
                    BEExtraEnergizedSmeltingFactory.class,
                    MekUtMachines.TWEAKED_ENERGIZED_SMELTER.descriptionEntry,
                    builder -> builder
                            .withEnergyConfig(
                                    MekanismConfig.usage.energizedSmelter,
                                    () -> MekanismConfig.storage.energizedSmelter.getAsLong() * tier.processes)
                            .withSideConfig(TransmissionType.ITEM, TransmissionType.ENERGY,
                                    TransmissionType.CHEMICAL)
                            .with(new ExtraAttributeTier<>(tier))
                            .withSupportedUpgrades(Upgrade.ENERGY, Upgrade.SPEED, Upgrade.MUFFLING,
                                    ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE)));
        }
        MekUtMachines.ENERGIZED_SMELTING_FACTORIES.get(FactoryTier.ULTIMATE).getBlockType()
                .add(new ExtraAttributeUpgradeable(EXTRA_SMELTING_FACTORIES.get(ExtraFactoryTier.ABSOLUTE)::getBlock));
        for (int i = 0; i < tiers.length - 1; i++) {
            EXTRA_SMELTING_FACTORIES.get(tiers[i]).getBlockType()
                    .add(new ExtraAttributeUpgradeable(EXTRA_SMELTING_FACTORIES.get(tiers[i + 1])::getBlock));
        }
    }
}

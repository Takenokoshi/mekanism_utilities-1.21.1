package com.takenokoshi.mekut.core;

import fr.iglee42.evolvedmekanism.interfaces.InitializableEnum;
import fr.iglee42.evolvedmekanism.tiers.EMFactoryTier;
import mekanism.common.tier.FactoryTier;

public class EvoMekModule {

    public static FactoryTier[] getFactoryTiers() {
        ((InitializableEnum) (Object) FactoryTier.BASIC).evolvedmekanism$initNewValues();
        return new FactoryTier[] {
                FactoryTier.BASIC,
                FactoryTier.ADVANCED,
                FactoryTier.ELITE,
                FactoryTier.ULTIMATE,
                EMFactoryTier.OVERCLOCKED,
                EMFactoryTier.QUANTUM,
                EMFactoryTier.DENSE,
                EMFactoryTier.MULTIVERSAL,
                EMFactoryTier.CREATIVE,
        };
    }
}

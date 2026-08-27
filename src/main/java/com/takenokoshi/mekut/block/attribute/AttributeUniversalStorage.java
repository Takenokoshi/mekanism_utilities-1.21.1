package com.takenokoshi.mekut.block.attribute;

import com.takenokoshi.mekut.tier.UniversalStorageTier;

import mekanism.common.block.attribute.Attribute;

public final class AttributeUniversalStorage implements Attribute {

    public final UniversalStorageTier tier;

    public AttributeUniversalStorage(UniversalStorageTier tier){
        this.tier = tier;}
}

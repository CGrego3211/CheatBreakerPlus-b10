/*
 * Decompiled with CFR 0.151.
 */
package me.travis.wurstplus.wurstplustwo.hacks.render;

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;

public class WurstplusCapes
extends WurstplusHack {
    WurstplusSetting cape = this.create("Cape", "CapeCape", "New", this.combobox("New"));

    public WurstplusCapes() {
        super(WurstplusCategory.WURSTPLUS_RENDER);
        this.name = "Capes";
        this.tag = "Capes";
        this.description = "see epic capes behind epic dudes";
    }
}


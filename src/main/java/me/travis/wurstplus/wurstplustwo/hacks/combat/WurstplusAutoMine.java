/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.BlockPos
 */
package me.travis.wurstplus.wurstplustwo.hacks.combat;

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.util.WurstplusBreakUtil;
import me.travis.wurstplus.wurstplustwo.util.WurstplusEntityUtil;
import me.travis.wurstplus.wurstplustwo.util.WurstplusMessageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class WurstplusAutoMine
extends WurstplusHack {
    WurstplusSetting end_crystal = this.create("End Crystal", "MineEndCrystal", false);
    WurstplusSetting range = this.create("Range", "MineRange", 4, 0, 6);

    public WurstplusAutoMine() {
        super(WurstplusCategory.WURSTPLUS_COMBAT);
        this.name = "Auto Mine";
        this.tag = "AutoMine";
        this.description = "jumpy is now never going to use the client again";
    }

    @Override
    protected void enable() {
        BlockPos target_block = null;
        for (EntityPlayer player : WurstplusAutoMine.mc.world.playerEntities) {
            BlockPos p;
            if (WurstplusAutoMine.mc.player.getDistance((Entity)player) > (float)this.range.get_value(1) || (p = WurstplusEntityUtil.is_cityable(player, this.end_crystal.get_value(true))) == null) continue;
            target_block = p;
        }
        if (target_block == null) {
            WurstplusMessageUtil.send_client_message("cannot find block");
            this.disable();
        }
        WurstplusBreakUtil.set_current_block(target_block);
    }

    @Override
    protected void disable() {
        WurstplusBreakUtil.set_current_block(null);
    }
}


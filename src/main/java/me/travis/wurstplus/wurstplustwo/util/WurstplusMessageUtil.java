/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.Style
 *  net.minecraft.util.text.TextComponentBase
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.event.HoverEvent
 *  net.minecraft.util.text.event.HoverEvent$Action
 */
package me.travis.wurstplus.wurstplustwo.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.HoverEvent;

public class WurstplusMessageUtil {
    public static final Minecraft mc = Minecraft.getMinecraft();
    public static ChatFormatting g = ChatFormatting.GOLD;
    public static ChatFormatting b = ChatFormatting.BLUE;
    public static ChatFormatting a = ChatFormatting.DARK_AQUA;
    public static ChatFormatting r = ChatFormatting.RESET;
    public static ChatFormatting y = ChatFormatting.RED;
    public static String opener = y + "CheatBreakerPlus" + a + " >> " + r;

    public static void toggle_message(WurstplusHack module) {
        if (module.is_active()) {
            if (module.get_tag().equals("AutoCrystal")) {
                WurstplusMessageUtil.client_message_simple(opener + ChatFormatting.DARK_GREEN + "ac goes" + ChatFormatting.GREEN + " brrr ");
            } else {
                WurstplusMessageUtil.client_message_simple(opener + a + module.get_name() + ChatFormatting.GREEN + " Enabled");
            }
        } else if (module.get_tag().equals("AutoCrystal")) {
            WurstplusMessageUtil.client_message_simple(opener + y + "ac" + y + " stopped " + y + "brrr :(");
        } else {
            WurstplusMessageUtil.client_message_simple(opener + a + module.get_name() + ChatFormatting.RED + " Disabled");
        }
    }

    public static void client_message_simple(String message) {
        if (WurstplusMessageUtil.mc.player != null) {
            ITextComponent itc = new TextComponentString(message).setStyle(new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (ITextComponent)new TextComponentString("frank alachi"))));
            WurstplusMessageUtil.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(itc, 5936);
        }
    }

    public static void client_message(String message) {
        if (WurstplusMessageUtil.mc.player != null) {
            WurstplusMessageUtil.mc.player.sendMessage((ITextComponent)new ChatMessage(message));
        }
    }

    public static void send_client_message_simple(String message) {
        WurstplusMessageUtil.client_message(ChatFormatting.RED + "CheatBreakerPlus" + " " + r + message);
    }

    public static void send_client_message(String message) {
        WurstplusMessageUtil.client_message(ChatFormatting.RED + "CheatBreakerPlus" + " " + r + message);
    }

    public static void send_client_error_message(String message) {
        WurstplusMessageUtil.client_message(ChatFormatting.RED + "CheatBreakerPlus" + " " + r + message);
    }

    public static class ChatMessage
    extends TextComponentBase {
        String message_input;

        public ChatMessage(String message) {
            Pattern p = Pattern.compile("&[0123456789abcdefrlosmk]");
            Matcher m = p.matcher(message);
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
                String replacement = "\u00a7" + m.group().substring(1);
                m.appendReplacement(sb, replacement);
            }
            m.appendTail(sb);
            this.message_input = sb.toString();
        }

        public String getUnformattedComponentText() {
            return this.message_input;
        }

        public ITextComponent createCopy() {
            return new ChatMessage(this.message_input);
        }
    }
}


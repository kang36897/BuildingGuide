package com.tiger.curious.guide.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * Created by bkang016 on 2/15/18.
 */

public class EChineseUtils {


    public static String getSpellCapitalChar(String characters) {
        StringBuilder sBuilder = new StringBuilder();

        char[] chars = characters.toCharArray();

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 128) {
                try {
                    sBuilder.append(PinyinHelper.toHanyuPinyinStringArray(chars[i], format)[0].charAt(0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                sBuilder.append(chars[i]);
            }


        }

        return sBuilder.toString();

    }
}

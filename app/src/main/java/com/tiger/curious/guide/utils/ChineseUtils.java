package com.tiger.curious.guide.utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by bkang016 on 5/16/17.
 */

public class ChineseUtils {

    final static int GB_SP_DIFF = 160;

    final static int[] secPosValueList = new int[]{
            1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472,
            3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5600
    };

    final static char[] firstLetter = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'w', 'x', 'y', 'z'
    };

    public static String getSpells(String characters) {
        String temp = characters.trim();

        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < temp.length(); i++) {

            char ch = temp.charAt(i);
            if (((ch >> 7) == 0) || ch == 160) {

            } else {
                char spell = getFirstLetter(ch);

                buffer.append(spell);
            }

        }

        return buffer.toString();
    }

    private static Character getFirstLetter(char ch) {
        byte[] uniCode = null;
        try {
            uniCode = String.valueOf(ch).getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        if (uniCode[0] < 128 && uniCode[0] > 0) {
            return null;
        }


        return convert(uniCode);
    }

    private static char convert(byte[] bytes) {
        char result = '-';

        int secPosValue = 0;
        int i;

        for (i = 0; i < bytes.length; i++) {
            bytes[i] -= GB_SP_DIFF;
        }

        secPosValue = bytes[0] * 100 + bytes[1];
        for (i = 0; i < 23; i++) {
            if (secPosValue >= secPosValueList[i] && secPosValue < secPosValueList[i + 1]) {
                result = firstLetter[i];
                break;
            }
        }

        return result;
    }


}

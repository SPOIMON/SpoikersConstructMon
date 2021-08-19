package com.spoimon.spoikers_construct_mon.utils;

import java.util.Locale;

/**
 * 文字列関連の汎用処理をまとめたクラス
 * @author riku1227
 */
public class StringUtil {
    /**
     * 文字列の一番最初の文字を大文字に変換する
     * @param value 最初の文字を大文字にする文字列
     * @return 最初の文字を大文字にした文字列
     */
    public static String toFirstUpper(String value) {
        return value.substring(0, 1).toUpperCase(Locale.US) + value.substring(1);
    }
}

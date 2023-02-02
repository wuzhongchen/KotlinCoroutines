package com.demo.kotlin02;

import java.util.Random;

/**
 * @author ningchuanqi
 * @description
 */
public class _6JavaAnonymousClass {

    public static void main(String[] args) {
        //静态内部类
        showOnBoard("卫生纸", new MyDisCountWords());
        //匿名内部类
        showOnBoard("牙膏", new DiscountWords() {
            @Override
            public String getDiscountWords(String goodsName, int hour) {
                int currentYear = 2017;
                return String.format(String.format("%d年，双11%s促销倒计时：%d 小时",currentYear,goodsName,hour));
            }
        });
        /**
         *     对标AnnoymousFunc3.kt
         */
        showOnBoard("牙膏", (goodsName, hour) -> {
            int currentYear = 2017;
            return String.format(String.format("%d年，双11%s促销倒计时：%d 小时",currentYear,goodsName,hour));
        });
    }

    public interface DiscountWords{
        String getDiscountWords(String goodsName,int hour);
    }

    public static void showOnBoard(String goodsName,DiscountWords discountWords){
        int hour = new Random().nextInt(24);
        System.out.println(discountWords.getDiscountWords(goodsName,hour));
    }

    static class MyDisCountWords implements DiscountWords {

        @Override
        public String getDiscountWords(String goodsName, int hour) {
            int currentYear = 2019;
            return String.format(String.format("%d年，双11%s促销倒计时：%d 小时",currentYear,goodsName,hour));
        }
    }

}

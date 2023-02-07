package com.demo.kotlin10;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

import java.io.IOException;

/**
 * @author ningchuanqi
 * @description
 */
public class Jhava {

    private int hitPoints = 3232320;

    public static void main(String[] args) {
        System.out.println(Hero.makeProclamation());
        Spellbook spellbook = new Spellbook();
        for (String spell : spellbook.spells) {
            System.out.println(spell);
        }

        //Java要支持，重载
        Hero.handOverFood("apple");
        //伴生对象
        //Spellbook.Companion.getMAX_SPELL_COUNT();
        System.out.println(Spellbook.MAX_SPELL_COUNT);
        Spellbook.getSpellbookGreeting();

        /*try {
            //强制在编译期进行处理
            new Jhava().extendHandInFriendship();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try{
            Hero.acceptApology();
        }catch (IOException e){
            System.out.println("Caught!");
        }

        Function1<String, Unit> translator = Hero.getTranslator();
        translator.invoke("TRUCE");

    }

    @NonNull
    public String utterGreeting() {
        return "HELLO";
    }

    @Nullable
    public String determineFriendshipLevel() {
        return null;
    }

    public int getHitPoints() {
        System.out.println("-------getHitPoints--------");
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void extendHandInFriendship() throws IOException {
        throw new IOException();
    }
}

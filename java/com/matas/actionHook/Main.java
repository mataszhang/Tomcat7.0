package com.matas.actionHook;

/**
 *
 *@author matas
 *@date  2018/8/28 11:49
 *@email   mataszhang@163.com
 */
public class Main {
    public static void main(String[] args) {
          ActionHook actionHook = new Http11Processor();
          Reponse reponse = new Reponse(actionHook);
          reponse.action(ActionEnum.CLOSE,null);
    }
}

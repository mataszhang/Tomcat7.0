package com.matas.actionHook;

/**
 * @author matas
 * @date 2018/8/28 11:48
 * @email mataszhang@163.com
 */
public class Reponse {
    private ActionHook actionHook;

    public Reponse(ActionHook actionHook) {
        this.actionHook = actionHook;
    }

    public void action(ActionEnum code, Object param) {
        actionHook.action(code, param);
    }
}

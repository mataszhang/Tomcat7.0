package com.matas.actionHook;

/**
 * @author matas
 * @date 2018/8/28 11:46
 * @email mataszhang@163.com
 */
public interface ActionHook {
    void action(ActionEnum code, Object param);
}

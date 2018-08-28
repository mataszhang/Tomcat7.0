package com.matas.actionHook;

/**
 * @author matas
 * @date 2018/8/28 11:48
 * @email mataszhang@163.com
 */
public class Http11Processor implements ActionHook {
    @Override
    public void action(ActionEnum code, Object param) {
        switch (code) {
            case CLOSE:
                System.out.println("receive CLOSE action");
                break;
            case COMMIT:
                System.out.println("receive COMMIT action");
                break;
        }
    }
}

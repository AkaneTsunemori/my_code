package code.design.mode.struct.pattern.proxy.staticproxy2;

/**
 * 这就是代理模式的一个应用，除了 打印日志，它还可以用来做权限管理。
 * 读者看到这里可能已经发现了，
 * 这个代理类看起来和装饰模式的 FilterInputStream 一模一样，但两者的目的不同，
 * 装饰模式是为了 增强功能或添加功能，代理模式主要是为了加以控制。
 * 装饰模式 对于不同的装饰器会单独的实现接口, 代理模式代理者和被代理者都是实现的同一个接口
 */
public class Test {
    public static void main(String[] args) {
        HttpUtil httpUtil = new HttpUtil();
        HttpProxy proxy = new HttpProxy(httpUtil);
        proxy.request("request data");
        proxy.onSuccess("received result");
    }
}

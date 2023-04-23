package code.design.mode.struct.pattern.proxy.dynamic;

import code.design.mode.struct.pattern.proxy.staticproxy2.HttpUtil;
import code.design.mode.struct.pattern.proxy.staticproxy2.IHttp;

/**
 * 动态代理与静态代理的原理一模一样，只是换了一种写法。
 * 使用动态代理，需要把一个类传入，然后根据它正在调用的方法名判断是否需要加以控制。
 */
public class Test {
    public static void main(String[] args) {
        HttpUtil iHttp = new HttpUtil();
        HttpProxy httpProxy = new HttpProxy();
        IHttp proxyIHttp = httpProxy.getInstance(iHttp);
        proxyIHttp.request("shiro");
        proxyIHttp.onSuccess("suzumiyaharuhi");

    }
}

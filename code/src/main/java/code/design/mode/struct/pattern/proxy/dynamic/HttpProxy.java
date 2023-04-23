package code.design.mode.struct.pattern.proxy.dynamic;

import code.design.mode.struct.pattern.proxy.staticproxy2.HttpUtil;
import code.design.mode.struct.pattern.proxy.staticproxy2.IHttp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HttpProxy implements InvocationHandler {
    IHttp iHttp;

    public HttpProxy() {
    }

    public HttpProxy(IHttp iHttp) {
        this.iHttp = iHttp;
    }

    public IHttp getInstance(HttpUtil iHttp){
        this.iHttp = iHttp;
        return (IHttp) Proxy.newProxyInstance(IHttp.class.getClassLoader(),iHttp.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if(method.getName().equals("request")){
            System.out.println("send data :"+args[0]);
            result = method.invoke(iHttp,args[0]);
        }else if(method.getName().equals("onSuccess")){
            System.out.println("receive data: "+ args[0]);
            result = method.invoke(iHttp,args[0]);
        }
        return result;
    }
}

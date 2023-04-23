package code.design.mode.struct.pattern.proxy.dynamic;

import code.design.mode.struct.pattern.proxy.staticproxy2.IHttp;

public class FakeHttpProxy {
    private IHttp ihttp;

    public FakeHttpProxy(IHttp ihttp) {
        this.ihttp = ihttp;
    }
    public void visit(String method, Object[] args){
        if(method.equals("request")){
            System.out.println("send data "+ args[0].toString());
            ihttp.request(args[0].toString());

        }else if(method.equals("onSuccess")){
            System.out.println("receive data "+ args[0].toString());
            ihttp.onSuccess(args[0].toString());
        }
    }
}

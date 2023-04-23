package code.design.mode.struct.pattern.proxy.staticproxy2;

public class HttpProxy implements IHttp {
    IHttp iHttp;

    public HttpProxy(IHttp iHttp) {
        this.iHttp = iHttp;
    }

    @Override
    public void request(String sendData) {
        System.out.println("onProxy: send data: ");
        iHttp.request(sendData);

    }

    @Override
    public void onSuccess(String receivedData) {
        System.out.println("onProxy: receive data: ");
        iHttp.onSuccess(receivedData);
    }
}

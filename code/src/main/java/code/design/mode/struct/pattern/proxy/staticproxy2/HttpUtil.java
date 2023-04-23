package code.design.mode.struct.pattern.proxy.staticproxy2;

public class HttpUtil implements IHttp{
    @Override
    public void request(String sendData) {
        System.out.println("HttpUtil on request...");
    }

    @Override
    public void onSuccess(String receivedData) {
        System.out.println("HttpUtil receive data completed");
    }
}

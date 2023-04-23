package code.design.mode.struct.pattern.proxy.staticproxy2;

public interface IHttp {
    void request(String sendData);
    void onSuccess(String receivedData);
}

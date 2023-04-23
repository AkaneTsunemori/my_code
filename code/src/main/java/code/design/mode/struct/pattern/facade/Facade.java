package code.design.mode.struct.pattern.facade;

public class Facade {
    private Browser browser;
    private Idea idea;
    private Wechat wechat;

    public Facade(Browser browser, Idea idea, Wechat wechat) {
        this.browser = browser;
        this.idea = idea;
        this.wechat = wechat;
    }
    void open(){
        browser.open();
        idea.open();
        wechat.open();
    }
    void close(){
        browser.close();
        idea.close();
        wechat.close();
    }
}

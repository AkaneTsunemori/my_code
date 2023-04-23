package code.design.mode.struct.pattern.decorator.iodecorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) throws IOException {
        io();
    }

    public static void io() throws IOException {
        try (InputStream in = new BufferedInputStream(new FileInputStream("code/design/mode/struct/pattern/decorator/iodecorator/readme.txt"))) {
            byte[] buffer = new byte[1024];
            //read() 方法会读取输入流的下一个字节，并返回字节表示的 int 值（0~255），返回 -1 表示已读到末尾。由于它是抽象方法，所以具体的逻辑交由子类实现。
            while (in.read(buffer) != -1) {
                System.out.println(new String(buffer));
            }
        }
    }
}

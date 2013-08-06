package us.codecraft.webmagic.scheduler;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author code4crafter@gmail.com <br>
 * @date: 13-7-14 <br>
 * Time: 下午9:20 <br>
 */
public enum HessianSerializer {
    INSTANCE;
    public <T> byte[] serialize(T v) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(baos);
        hessian2Output.writeObject(v);
        hessian2Output.close();
        return baos.toByteArray();
    }

    @SuppressWarnings("unchecked")
    public <T> T deSerialize(byte[] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Hessian2Input hessian2Input = new Hessian2Input(bais);
        T t = (T) hessian2Input.readObject();
        hessian2Input.close();
        return t;
    }
}

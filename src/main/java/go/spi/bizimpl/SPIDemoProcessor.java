package go.spi.bizimpl;

import com.google.auto.service.AutoService;
import go.spi.XProcessor;

import java.util.Map;

@AutoService(value = XProcessor.class)
public class SPIDemoProcessor implements XProcessor {
    @Override
    public void process(Map<String, Object> source) {
        source.put("extra", 666);
    }
}

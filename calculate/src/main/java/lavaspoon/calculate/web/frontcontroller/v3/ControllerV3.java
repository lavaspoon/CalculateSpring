package lavaspoon.calculate.web.frontcontroller.v3;

import lavaspoon.calculate.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String,String> paramMap);
}

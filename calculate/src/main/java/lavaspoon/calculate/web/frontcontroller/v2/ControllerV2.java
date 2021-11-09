package lavaspoon.calculate.web.frontcontroller.v2;

import lavaspoon.calculate.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    //기존의 Controller1과 같은데 반환을 MyView 로 반환함
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

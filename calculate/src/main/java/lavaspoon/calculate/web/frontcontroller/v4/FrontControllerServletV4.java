package lavaspoon.calculate.web.frontcontroller.v4;

import lavaspoon.calculate.web.frontcontroller.ModelView;
import lavaspoon.calculate.web.frontcontroller.MyView;
import lavaspoon.calculate.web.frontcontroller.v4.ControllerV4;
import lavaspoon.calculate.web.frontcontroller.v4.controller.MemberFormControllerV4;
import lavaspoon.calculate.web.frontcontroller.v4.controller.MemberListControllerV4;
import lavaspoon.calculate.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// * : /v1/ 하위로 어떤 URL 이 들어와도 이 서블릿이 호출됨
@WebServlet(name="frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    //어떤 URL이 들어오면 ControllerV1 을 실행하라는것 <key = URL, value = 컨트롤러>
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    //기본 생성자 ( 서블릿이 처음 실행될때 위의 Map에 아래의 값을 넣어두게 됨)
    public FrontControllerServletV4() {
        //url이 들어오면 객체 인스턴스가 실행되게 세팅
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");

        //요청한 URL을 그대로 받아옴
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        //요청한 URI로 Map에서 꺼내면 해당 객체 인스턴스가 반환됨, 인터페이스를 사용하면 아래 코드를 일관성 있게 사용 가능
        /*
       해당 인스턴스 객체의 클래스 implement ControllerV1 즉, 부모가 ControllerV1 이기 때문에 다형성에 의해 인터페이스
       를 가져올수 있다.
       ControllerV1 controller = new MemberListControllerV1(); 와 같음
       부모는 자식을 다 가져올 수 있다.
        */
        ControllerV4 controller = controllerMap.get(requestURI);
        //예외처리
        if(controller == null) {
            response.setStatus(404);
            return;
        }
        //opt+command+M
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();//추가
        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName);

        view.render(model, request, response);
    }

    private MyView viewResolver(String viewName) {
        MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return view;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        //paramMap 을 다 꺼내야함
        //Key: paramName, value: request.getParameter(paramName) 로 값을 찾아옴
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}

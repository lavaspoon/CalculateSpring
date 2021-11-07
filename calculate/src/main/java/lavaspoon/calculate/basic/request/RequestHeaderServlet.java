package lavaspoon.calculate.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printStartLine(req);
        printHeaders(req);
    }

    private void printStartLine(HttpServletRequest req) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("req.getMethod() = " + req.getMethod()); //GET
        System.out.println("req.getProtocal() = " + req.getProtocol()); //
        //®HTTP/1.1
        System.out.println("req.getScheme() = " + req.getScheme()); //http
        // http://localhost:8080/request-header
        System.out.println("req.getRequestURL() = " + req.getRequestURL());
        // /request-test
        System.out.println("req.getRequestURI() = " + req.getRequestURI());
        //username=hi
        System.out.println("req.getQueryString() = " +
                req.getQueryString());
        System.out.println("req.isSecure() = " + req.isSecure()); //https
        //사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }
    private void printHeaders(HttpServletRequest req) {
        System.out.println("--- Headers - start ---");
        req.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": " + req.getHeader(headerName)));
                        System.out.println("--- Headers - end ---");
        System.out.println();
    }
}

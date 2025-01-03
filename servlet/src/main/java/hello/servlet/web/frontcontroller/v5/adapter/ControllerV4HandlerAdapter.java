package hello.servlet.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

	@Override
	public boolean supports(Object handler) {
		return (handler instanceof ControllerV4);
	}

	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException, IOException {

		// 4. handler 호출
		ControllerV4 controller = (ControllerV4) handler;
		Map<String, String> paramMap = this.createParamMap(request);
		Map<String, Object> model = new HashMap<>();
		
		String viewName = controller.process(paramMap, model);
		
		// ControllerV4는 뷰의 이름을 반환한다. 하지만 어댑터는 ModelView를 만들어 반환한다.
		ModelView mv = new ModelView(viewName);
		mv.setModel(model);
		
		return mv;
	}

	private Map<String, String> createParamMap(HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<>();
		request.getParameterNames().asIterator()
			.forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
		return paramMap;
	}
	
}

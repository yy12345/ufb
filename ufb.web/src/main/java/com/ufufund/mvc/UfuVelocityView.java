package com.ufufund.mvc;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;


/**
 * 实现通过request uri 自动加载layout，并实现layout中可以个性化设置如title，script等信息
 * layout配置文件见layout.xml，将layout.xml放置在classpath下
 * 
 */
public class UfuVelocityView extends VelocityToolboxView {
	private static LayoutConfig layoutConfig = new LayoutConfig();
	private static Logger logger = LoggerFactory.getLogger(UfuVelocityView.class);
	private final String CONTROLPATH = "control/";

	@Override
	protected void renderMergedTemplateModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String uri = request.getRequestURI();
		if(uri.endsWith("family/index.htm")){
			model.put("family_module", "index");
			model.put("family_container", "class=\"containerBox\"");
		}else if(uri.indexOf("family/account/") > 0){
			model.put("family_module", "account");
			model.put("family_container", "id=\"page\"");
		}else if(uri.indexOf("family/uft/") > 0){
			model.put("family_module", "uft");
			if(uri.endsWith("pay_result.htm")){
				model.put("family_page", "pay_result");
				model.put("family_container", "class=\"container\"");
			}else{
				model.put("family_container", "id=\"page\"");
			}
		}else if(uri.indexOf("family/ufb/") > 0){
			model.put("family_module", "ufb");
			model.put("family_container", "class=\"container\"");
			if(uri.endsWith("pay_index.htm") || uri.endsWith("cash_index.htm")
					|| uri.endsWith("query_index.htm") || uri.endsWith("query_detail.htm")
					|| uri.endsWith("auto_pause.htm")|| uri.endsWith("auto_stop.htm")){
				model.put("family_container", "class=\"container center\"");
			}else if(uri.endsWith("ufb_index.htm")){
				model.put("family_container", "id=\"page\"");
				model.put("family_page", "ufb_index");
			}
		}else if(uri.indexOf("family/setting/") > 0){
			model.put("family_module", "setting");
			model.put("family_container", "class=\"container setting\"");
			if(uri.endsWith("card_update.htm")){
				model.put("family_container", "class=\"container center\"");
			}
		}
		else if(uri.indexOf("family/help/") > 0){
			model.put("family_module", "help");
			model.put("family_container", "class=\"container help\"");
		}
		
		if(uri.indexOf("corp/") > 0){
			model.put("corp_module", "corp");
			model.put("corp_container", "id=\"page\"");
			if(uri.endsWith("index.htm")||uri.endsWith("auth_index.htm")){
				model.put("corp_container", "class=\"container\"");
			}
			if(uri.endsWith("code_index.htm")){
				model.put("corp_container", "class=\"page\"");
			}
			
			if(uri.indexOf("help/") > 0){
				model.put("corp_module", "help");
				model.put("corp_container", "class=\"container help\"");
			}
			
			String corp_page = uri.substring(uri.lastIndexOf('/')+1, uri.lastIndexOf('.'));
			model.put("corp_page", corp_page);
		}
		
		
		exposeHelpers(model, request);
		Context velocityContext = createVelocityContext(model, request,
				response);

		exposeHelpers(velocityContext, request, response);
		exposeToolAttributes(velocityContext, request);

		doRender(velocityContext, request, response);
	}

	private void doRender(Context context, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String layoutPath = getLayoutUrl(request);
		if (StringUtils.isEmpty(layoutPath)) {
			doRender(context, response);
			return;
		}
		renderScreenContent(context);
		mergeTemplate(getTemplate(layoutPath), context, response);
	}

	/**
	 * 解析head，title，body，script，并设置在velocityContext中，在layout中可以直接使用 script
	 * 只会解析写在head内的script，body中的script不解析
	 * 
	 * @param context
	 * @throws Exception
	 */
	private void renderScreenContent(Context context) throws Exception {
		StringWriter sw = new StringWriter();
		Template screenContentTemplate = getTemplate(getUrl());
		screenContentTemplate.merge(context, sw);
		String screen = sw.toString();
		Pattern pattern = Pattern.compile("(?<=<body>)[\\S\\s]*(?=</body>)");
		Matcher matcher = pattern.matcher(screen);
		if (matcher.find()) {
			context.put("body", matcher.group());
		} else {
			context.put("body", screen);
		}
		pattern = Pattern.compile("(?<=<head>)[\\S\\s]*(?=</head>)");
		matcher = pattern.matcher(screen);
		if (matcher.find()) {
			String head = matcher.group(0);
			// title
			pattern = Pattern.compile("<title.*</title>");
			matcher = pattern.matcher(head);
			String title;
			if (matcher.find()) {
				title = matcher.group();
				head = matcher.replaceAll("");
				context.put("title", title);
			}
			// script
			pattern = Pattern.compile("<script[\\s\\S]*?</script>");
			matcher = pattern.matcher(head);
			StringBuffer script = new StringBuffer();
			while (matcher.find()) {
				script.append(matcher.group());
			}
			if (script.length() > 0) {
				head = matcher.replaceAll("");
				context.put("script", script);
			}
			// style
			pattern = Pattern.compile("<style[\\s\\S]*?</style>");
			matcher = pattern.matcher(head);
			StringBuffer style = new StringBuffer();
			while (matcher.find()) {
				style.append(matcher.group());
			}
			if (style.length() > 0) {
				head = matcher.replaceAll("");
				context.put("style", style);
			}
			context.put("head", head);
		}
	}

	private String getLayoutUrl(HttpServletRequest request) {
		
		String path = request.getRequestURI();
		String host = request.getHeader("host");
		AntPathMatcher pathMatcher = new AntPathMatcher();
		// excludes，根据path或者host匹配
		for (String s : layoutConfig.getExcludes()) {
			if (pathMatcher.match(s, path) || pathMatcher.match(s, host)) {
				return null;
			}
		}
		// controller响应的vm页面：根据vm文件路径匹配
		for (LayoutEntry e : layoutConfig.getLayouts()) {
			if (pathMatcher.match(e.getUrlPattern(), super.getUrl())) {
				return e.getLayoutUrl();
			}
		}
		// default默认布局
		return layoutConfig.getDefaultLayout();
	}

	/**
	 * 所有controller处理的vm必须在control目录下
	 */
	@Override
	public String getUrl() {
		return CONTROLPATH + super.getUrl();
	}
	
	/**
	 * layout.xml 解析，url匹配从上往下匹配，配置url匹配模式按匹配范围大小从上到下配置
	 * 
	 */
	static class LayoutConfig {

		@SuppressWarnings("unchecked")
		LayoutConfig() {
			errorPages = new ArrayList<String>();
			excludes = new ArrayList<String>();
			layouts = new ArrayList<LayoutEntry>();
			SAXReader reader = new SAXReader();
			Document document = null;
			try {
				document = reader.read(this.getClass().getResourceAsStream(
						"/layouts.xml"));
			} catch (DocumentException e) {
				logger.error("load layouts.xml error", e);
			}
			Element root = document.getRootElement();
			List<Element> elements = root.elements();
			String name = "";
			for (Element element : elements) {
				name = element.getName();
				if ("errorPages".equals(name)) {
					List<Element> errorPageList = element.elements();
					for (Element errorPage : errorPageList) {
						this.errorPages.add(errorPage.getText());
					}
					this.errorPageLayout = element.attributeValue("page");
				}
				if ("excludes".equals(name)) {
					List<Element> excludeList = element.elements();
					for (Element exclude : excludeList) {
						this.excludes.add(exclude.getText());
					}
				}
				if ("layout".equals(name)) {
					List<Element> decorators = element.elements();
					for (Element decorator : decorators) {
						this.layouts.add(new LayoutEntry(decorator.getTextTrim(),
										element.attributeValue("page")));
					}
				}
				if ("true".equals(element.attributeValue("default"))) {
					this.defaultLayout = element.attributeValue("page");
				}
			}
			if (this.defaultLayout == null && this.layouts.size() > 0) {
				this.defaultLayout = this.layouts.get(0).getLayoutUrl();
			}
		}

		private List<String> excludes;
		private List<String> errorPages;
		private List<LayoutEntry> layouts;
		private String defaultLayout;
		private String errorPageLayout;

		public List<String> getExcludes() {
			return excludes;
		}

		public void setExcludes(List<String> excludes) {
			this.excludes = excludes;
		}

		public List<String> getErrorPages() {
			return errorPages;
		}

		public void setErrorPages(List<String> errorPages) {
			this.errorPages = errorPages;
		}

		public List<LayoutEntry> getLayouts() {
			return layouts;
		}

		public void setLayouts(List<LayoutEntry> layouts) {
			this.layouts = layouts;
		}

		public String getDefaultLayout() {
			return defaultLayout;
		}

		public void setDefaultLayout(String defaultLayout) {
			this.defaultLayout = defaultLayout;
		}

		public String getErrorPageLayout() {
			return errorPageLayout;
		}

		public void setErrorPageLayout(String errorPageLayout) {
			this.errorPageLayout = errorPageLayout;
		}

	}

	static class LayoutEntry {
		private String urlPattern;
		private String layoutUrl;

		LayoutEntry(String urlPattern, String layoutUrl) {
			this.urlPattern = urlPattern;
			this.layoutUrl = layoutUrl;
		}

		public String getUrlPattern() {
			return urlPattern;
		}

		public void setUrlPattern(String urlPattern) {
			this.urlPattern = urlPattern;
		}

		public String getLayoutUrl() {
			return layoutUrl;
		}

		public void setLayoutUrl(String layoutUrl) {
			this.layoutUrl = layoutUrl;
		}

	}
}

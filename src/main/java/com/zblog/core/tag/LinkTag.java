package com.zblog.core.tag;
import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import com.zblog.core.utils.AppUtils;

public class LinkTag extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8997239144745198831L;
	private String id;
	private String href;
	private String media;
	private String rel="stylesheet";
	private String type="text/css";
	private String title;
	private void addAttribute(StringBuffer sb,String name,String value){
		if(StringUtils.isNotBlank(value)){
			sb.append(MessageFormat.format(" {0}=\"{1}\" ",name,value));
		}
	}
	public String version(String uri){
		uri=StringUtils.isBlank(uri)?"":uri;
		if(uri.indexOf("?")>-1){
			uri+="&v="+AppUtils.version();
		}else{
			uri+="?v="+AppUtils.version();
		}
		return uri;
	}
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		String baseurl = AppUtils.baseurl();
		StringBuffer sb = new StringBuffer();
		addAttribute(sb, "id", id);
		addAttribute(sb, "media", media);
		addAttribute(sb, "type", type);
		addAttribute(sb, "rel", rel);
		addAttribute(sb, "title", title);
		addAttribute(sb, "href", baseurl+"/"+version(href));
        try {
			out.println( MessageFormat.format("<link {0}/>",
					sb.toString()) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}
	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
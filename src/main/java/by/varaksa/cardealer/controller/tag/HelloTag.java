package by.varaksa.cardealer.controller.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class HelloTag extends TagSupport {
    private String firstname;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String tag = "Welcome, " + firstname;

            pageContext.getOut().write("<hr/>" + tag + "<hr/>");
        } catch (IOException exception) {
            throw new JspException(exception);
        }
        return SKIP_BODY;
    }
}

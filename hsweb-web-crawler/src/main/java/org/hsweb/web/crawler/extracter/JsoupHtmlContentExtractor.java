package org.hsweb.web.crawler.extracter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupHtmlContentExtractor implements HtmlContentExtractor {
    private String select;

    public JsoupHtmlContentExtractor(String select) {
        this.select = select;
    }

    @Override
    public String parse(String html) {
        Document document = Jsoup.parse(html);
        if (select == null)
            return document.text();
        return document.select(select).text();
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }
}

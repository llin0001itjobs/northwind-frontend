package org.llin.demo.northwind.model.entity.links;

public class Link {
    private String href;
    private String id;
    private String label;

    public Link() {}
    public Link(String label) { this.label = label; }

    public String getHref() { return href; }
    public void setHref(String href) { this.href = href; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    @Override
    public String toString() {
        return "Link [id=" + id + ", label=" + label + ", href=" + href + "]";
    }
}

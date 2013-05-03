package com.wadpam.open.tag.json;

import com.wadpam.open.json.JBaseObject;

public class JTag extends JBaseObject {

    /** Tag name, maybe not used */
    private String name;

    /** The Tag description */
    private String description;

    /** Tag icon url */
    private String iconUrl;

    /** Tag priority order */
    private Long   priority;

    /** Key of ref */
    private String appArg0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getAppArg0() {
        return appArg0;
    }

    public void setAppArg0(String appArg0) {
        this.appArg0 = appArg0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

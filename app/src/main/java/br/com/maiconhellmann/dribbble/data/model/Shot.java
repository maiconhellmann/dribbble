package br.com.maiconhellmann.dribbble.data.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shot {

    private Integer id;
    private String title;
    private String description;
    private Integer width;
    private Integer height;
    private Integer viewsCount;
    private Integer likesCount;
    private Integer commentsCount;
    private Integer attachmentsCount;
    private Integer reboundsCount;
    private Integer bucketsCount;
    private String createdAt;
    private String updatedAt;
    private String htmlUrl;
    private String attachmentsUrl;
    private String bucketsUrl;
    private String commentsUrl;
    private String likesUrl;
    private String projectsUrl;
    private String reboundsUrl;
    private Boolean animated;
    private List<String> tags = null;
    private Object team;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getAttachmentsCount() {
        return attachmentsCount;
    }

    public void setAttachmentsCount(Integer attachmentsCount) {
        this.attachmentsCount = attachmentsCount;
    }

    public Integer getReboundsCount() {
        return reboundsCount;
    }

    public void setReboundsCount(Integer reboundsCount) {
        this.reboundsCount = reboundsCount;
    }

    public Integer getBucketsCount() {
        return bucketsCount;
    }

    public void setBucketsCount(Integer bucketsCount) {
        this.bucketsCount = bucketsCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getAttachmentsUrl() {
        return attachmentsUrl;
    }

    public void setAttachmentsUrl(String attachmentsUrl) {
        this.attachmentsUrl = attachmentsUrl;
    }

    public String getBucketsUrl() {
        return bucketsUrl;
    }

    public void setBucketsUrl(String bucketsUrl) {
        this.bucketsUrl = bucketsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getLikesUrl() {
        return likesUrl;
    }

    public void setLikesUrl(String likesUrl) {
        this.likesUrl = likesUrl;
    }

    public String getProjectsUrl() {
        return projectsUrl;
    }

    public void setProjectsUrl(String projectsUrl) {
        this.projectsUrl = projectsUrl;
    }

    public String getReboundsUrl() {
        return reboundsUrl;
    }

    public void setReboundsUrl(String reboundsUrl) {
        this.reboundsUrl = reboundsUrl;
    }

    public Boolean getAnimated() {
        return animated;
    }

    public void setAnimated(Boolean animated) {
        this.animated = animated;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Object getTeam() {
        return team;
    }

    public void setTeam(Object team) {
        this.team = team;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
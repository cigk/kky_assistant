package com.kuaikuaiyu.assistant.model.domain;

public class StrollItem {
	private int id;
	private String name;
	private String code;
	private Object tags;
	private String platforms;
	private int	type;
	private int	pageMode;
	private String description;
	private int sourceId;
	private String userName;
	private String userLoginName;
	private String createUser;
	private String createTime;
	private Object  image;
	private String cover;
	private int sort;
	private int dataCount;
	private int showCount;
	private int price;
	private Object  avatar;
	private int useCount;
	private String topicName;
	private String sourceUser;
	private int isCompanyTemp;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPageMode() {
		return pageMode;
	}

	public void setPageMode(int pageMode) {
		this.pageMode = pageMode;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int courceId) {
		this.sourceId = courceId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Object getImage() {
		return image;
	}

	public void setImage(Object image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Object getTags() {
		return tags;
	}

	public void setTags(Object tags) {
		this.tags = tags;
	}

	public Object getAvatar() {
		return avatar;
	}

	public void setAvatar(Object avatar) {
		this.avatar = avatar;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public void setSourceUser(String sourceUser) {
		this.sourceUser = sourceUser;
	}

	public void setIsCompanyTemp(int isCompanyTemp) {
		this.isCompanyTemp = isCompanyTemp;
	}

	public String getPlatforms() {
		return platforms;
	}

	public int getUseCount() {
		return useCount;
	}

	public String getTopicName() {
		return topicName;
	}

	public String getSourceUser() {
		return sourceUser;
	}

	public int getIsCompanyTemp() {
		return isCompanyTemp;
	}

	public StrollItem(int id, int type, int pageMode, int sourceId, int sort,
			int dataCount, int showCount, int price, Object image, String name,
			String code, String description, String userName,
			String userLoginName, String createUser, String createTime,
			String cover, Object tags, Object avatar,int isCompanyTemp,String sourceUser,String topicName,int useCount,String platforms) {
		super();
		this.id = id;
		this.type = type;
		this.pageMode = pageMode;
		this.sourceId = sourceId;
		this.sort = sort;
		this.dataCount = dataCount;
		this.showCount = showCount;
		this.price = price;
		this.image = image;
		this.name = name;
		this.code = code;
		this.description = description;
		this.userName = userName;
		this.userLoginName = userLoginName;
		this.createUser = createUser;
		this.createTime = createTime;
		this.cover = cover;
		this.tags = tags;
		this.avatar = avatar;
		this.platforms = platforms;
		this.useCount = useCount;
		this.isCompanyTemp = isCompanyTemp;
		this.sourceUser = sourceUser;
		this.topicName = topicName;
	}

	public StrollItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StrollItem{" +
				"id=" + id +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", tags=" + tags +
				", platforms='" + platforms + '\'' +
				", type=" + type +
				", pageMode=" + pageMode +
				", description='" + description + '\'' +
				", sourceId=" + sourceId +
				", userName='" + userName + '\'' +
				", userLoginName='" + userLoginName + '\'' +
				", createUser='" + createUser + '\'' +
				", createTime='" + createTime + '\'' +
				", image=" + image +
				", cover='" + cover + '\'' +
				", sort=" + sort +
				", dataCount=" + dataCount +
				", showCount=" + showCount +
				", price=" + price +
				", avatar=" + avatar +
				", useCount=" + useCount +
				", topicName='" + topicName + '\'' +
				", sourceUser='" + sourceUser + '\'' +
				", isCompanyTemp=" + isCompanyTemp +
				'}';
	}
}

package com.testleaf.matschie.servicenow.serialization.pojos;

import com.google.gson.annotations.SerializedName;

public class CreateRecordPayload {

	private String description;
	@SerializedName(value = "short_description")
	private String shortDescription;
	private String category;

	public CreateRecordPayload() {

	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
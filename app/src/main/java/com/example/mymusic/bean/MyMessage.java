package com.example.mymusic.bean;

public class MyMessage {
/*    @SerializedName("id")
    private int id;*/
    private String description;
    private String image_url;

   /* public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
*/
    public String getDesription() {
        return description;
    }

    public void setDesription(String desription) {
        this.description = desription;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String imageUrl) {
        this.image_url = imageUrl;
    }

    @Override
    public String toString() {
        return "Message{" +
                ", desription='" + description + '\'' +
                ", imageUrl='" + image_url + '\'' +
                '}';
    }
}

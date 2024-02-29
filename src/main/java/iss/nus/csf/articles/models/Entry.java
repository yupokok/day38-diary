package iss.nus.csf.articles.models;

import org.bson.Document;

public class Entry {
    
    private String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    private String title;
    private String date;
    private String text;
    private String mood;
    private String imageUrl;
    
    public Entry updateUrl(String url) {
        Entry entry = new Entry();
        entry.setImageUrl(url);
        return entry;
	}

    public Document toDocument() {
		Document doc = new Document();
		doc.put("title", title);
        doc.put("date", date);
		doc.put("text", text);
		doc.put("url", imageUrl);
		doc.put("mood", mood);

		return doc;
	}
    
    public Entry() {
    }
    
    public Entry(String id, String title, String date, String text, String mood, String imageUrl) {
        this.title = title;
        this.date = date;
        this.text = text;
        this.mood = mood;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getMood() {
        return mood;
    }
    public void setMood(String mood) {
        this.mood = mood;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

 
    
}
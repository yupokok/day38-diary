package iss.nus.csf.articles.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import iss.nus.csf.articles.models.Entry;

@Repository
public class EntryRepo {

    @Autowired
    private MongoTemplate mongo;

    // db.entries.insert({})


    public String saveEntry(Entry entry) {
		Document doc = mongo.insert(entry.toDocument(), "entries");
		return doc.getObjectId("_id").toHexString();
	}

   
}

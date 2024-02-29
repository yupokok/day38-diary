package iss.nus.csf.articles.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import iss.nus.csf.articles.models.Entry;
import iss.nus.csf.articles.repositories.EntryRepo;
import iss.nus.csf.articles.repositories.ImageRepo;



@Service
public class EntryService {

    @Autowired
    private AmazonS3 s3;

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private ImageRepo imgRepo;
    @Autowired
    private EntryRepo entryRepo;


     public Entry saveEntryToMongo(Entry entry, MultipartFile image) throws IOException{

        String imageId = UUID.randomUUID().toString().substring(0, 8);
        String url = imgRepo.saveImageToS3(imageId, image.getInputStream(), image.getContentType());
        entry = entry.updateUrl(url);
        entryRepo.saveEntry(entry);
        return entry;

        }

    public List<Entry> getAllEntries(){

        return mongoTemplate.findAll(Entry.class, "entries");
    }

}

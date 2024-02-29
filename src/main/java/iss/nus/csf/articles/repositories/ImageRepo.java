package iss.nus.csf.articles.repositories;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

@Repository
public class ImageRepo {
    
    @Autowired
    private AmazonS3 s3;

    @Autowired
    MongoTemplate mongoTemplate;

    public String saveImageToS3(String id, InputStream is, String contentType){
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);

        PutObjectRequest putReq = new PutObjectRequest("vttp-kim", "images/%s".formatted(id), is, metadata);
        putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);

        s3.putObject(putReq);
        
        return s3.getUrl("vttp-kim", "images/%s".formatted(id)).toString();
    }

    public String getImageUrl(String id){
        String url = "https://vttp-kim.sgp1.digitaloceanspaces.com/images/%s".formatted(id);
        return url; 
    }



}

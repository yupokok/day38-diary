package iss.nus.csf.articles.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import iss.nus.csf.articles.models.Entry;
import iss.nus.csf.articles.service.EntryService;

@Controller
@RequestMapping
public class DiaryController {

   
   @Autowired
   private EntryService entryService;

   @PostMapping(path = "/diary", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public ModelAndView postMethodName(@RequestPart MultipartFile image,
         @RequestPart String title,
         @RequestPart String date,
         @RequestPart String mood,
         @RequestPart String text) {

      ModelAndView mav;
      Entry entry = new Entry();

      try {
                                                               // String picId = imageSvc.save(myfile.getInputStream(),
                                                               // myfile.getContentType());
                                                               // mav.addObject("imageUrl", "/picture/%s".formatted(picId));
         entry = entryService.saveEntryToMongo(entry, image);
         System.out.println(image.toString());
         mav = new ModelAndView("diary");
         mav.addObject("entry", entry);

      } catch (IOException ex) {
         ex.printStackTrace();
         mav = new ModelAndView("error");
         mav.addObject("imageUrl", "/joker.png");
      }

      return mav;

   }
}
package com.example.demo.controller;

import com.example.demo.po.FriendCircle;
import com.example.demo.po.Picture;
import com.example.demo.po.User;
import com.example.demo.repository.FriendCircleRepository;
import com.example.demo.repository.PictureRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.Result;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/friendcircle")
public class FriendCircleController {
    String filePath = "src/main/resources/static/";
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private FriendCircleRepository friendCircleRepository;
    private Result userResult;
    @Autowired
    private UserRepository userRepository;
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "getfriendcircle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getFriendCircle() {
        userResult = new Result();
        Gson gson = new Gson();
        List<FriendCircle> friendCircle = friendCircleRepository.findAll();
        if (friendCircle.size() == 0) {
            userResult.setCode(-1);
            return gson.toJson(userResult);
        }
        userResult.setCode(1);
        userResult.setFriendCircles(friendCircle);
        System.out.println("userResult>>>>>"+gson.toJson(userResult));
        return gson.toJson(userResult);
    }

    @RequestMapping(value = "updatefriendcircle",method = RequestMethod.POST )
    @ResponseBody
    public String updateFriendCircle(@RequestParam(value = "id") String id,@RequestParam(value = "username") String username){
        System.out.println("id>>>>>"+id);
        System.out.println("username>>>>>"+username);
        FriendCircle friendCircle = friendCircleRepository.findById(Integer.valueOf(id));
        String dianzan = friendCircle.getDianzan();
        System.out.println("dianzan>>>>>"+dianzan);
        if (dianzan.indexOf(","+username)!=-1){
            dianzan = dianzan.replace(","+username,"");
            System.out.println("dianzan.indexOf(\",\"+username)!=-1>>>>>"+dianzan);
        }else if (dianzan.indexOf(username)!=-1){
            dianzan = dianzan.replace(username,"");
            System.out.println("dianzan.indexOf(username)!=-1>>>>>"+dianzan);
        } else {
            if (dianzan==null||dianzan.equals("")){
                dianzan = username;
            }else {
                dianzan = dianzan+","+username;
            }
        }

        friendCircle.setDianzan(dianzan);
        friendCircleRepository.save(friendCircle);
        userResult = new Result();
        userResult.setCode(1);
        Gson gson = new Gson();
        userResult.setFriendCircle(friendCircle);
        System.out.println("userResult>>>>>"+gson.toJson(userResult));
        return gson.toJson(userResult);
    }
    //多文件上传
    @RequestMapping(value = "addfriendcircle", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(@RequestParam(value = "username") String username,@RequestParam(value = "content") String content,@RequestParam(value = "number") String number,HttpServletRequest request) {
        System.out.println("username>>>>>"+username);
        System.out.println("content>>>>>"+content);
        System.out.println("number>>>>>"+number);
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            System.out.println(paraName+": "+request.getParameter(paraName));
        }
        List<MultipartFile> files =new ArrayList<>() ;
        for (int i = 0;i<Integer.valueOf(number)-1;i++){
            files.add(((MultipartHttpServletRequest) request)
                    .getFile(String.valueOf(i)));
        }
        System.out.println("files.size()>>>>>"+files.size());

        MultipartFile file = null;
        BufferedOutputStream stream = null;
        User user = userRepository.getByUsername(username);
        FriendCircle friendCircle = new FriendCircle();
        friendCircle.setDianzan("");
        friendCircle.setContent(content);
        friendCircle.setCreateTime(new Date());
        friendCircle.setUsername(username);
        friendCircle.setHeadPic(user.getHeadPic());
        List<Picture> pics = new ArrayList<Picture>();
        Picture picture = new Picture();
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath+file.getOriginalFilename())));
                    BufferedImage sourceImg = ImageIO.read(file.getInputStream());
                    System.out.println("文件名："+file.getOriginalFilename());
                    String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                    System.out.println("old.getName())>>>>>"+suffix);
                    File old = new File(filePath+file.getOriginalFilename());
                    if (old.exists()){
                        System.out.println("old.exists())>>>>>"+suffix);
                        old.renameTo(new File(filePath+username+new Date().getDate()+"."+suffix));
                    }
                    System.out.println("w:"+sourceImg.getWidth()+"h"+sourceImg.getHeight());
                    picture.setHeight(sourceImg.getWidth());
                    picture.setWidth(sourceImg.getHeight());
                    System.out.println("old.getName())>>>>>"+old.getName());
                    picture.setUrl(old.getName());

                    pictureRepository.save(picture);
                    pics.add(picture);
                    stream.write(bytes);
                    stream.close();

                } catch (Exception e) {
                    stream = null;
                    return "You failed to upload " + i + " => "
                            + e.getMessage();
                }
            } else {
                return "You failed to upload " + i
                        + " because the file was empty.";
            }
        }
        friendCircle.setPics(pics);
        friendCircleRepository.save(friendCircle);
        userResult = new Result();
        userResult.setCode(1);
        Gson gson = new Gson();
        userResult.setFriendCircle(friendCircle);
        System.out.println("userResult>>>>>"+gson.toJson(userResult));
        return gson.toJson(userResult);
    }

}

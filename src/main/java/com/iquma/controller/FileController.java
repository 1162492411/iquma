package com.iquma.controller;

import com.iquma.pojo.Attachment;
import com.iquma.service.AttachmentService;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;


@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    private AttachmentService attachmentService;
    private static final String DOMAIN = "http://localhost:8080";

    //获取目前登录用户的id
    private String initUserId() {
        return String.valueOf(SecurityUtils.getSubject().getSession().getAttribute("userid"));
    }

    //上传图像到服务器--通用
    private String upload(HttpServletRequest request, String type) {
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        System.out.println(myFileName);
                        //重命名上传后的文件名,以固定前缀加当前时间加用户id命名
                        String fileName = type + "-" + initUserId() + "-" + System.currentTimeMillis() + "." + myFileName.substring(myFileName.lastIndexOf('.') + 1);
                        //定义上传路径
                        String path = request.getServletContext().getRealPath("/static/" + type + "/" + fileName);
                        System.out.println("上传路径是" + path);
                        File localFile = new File(path);
                        try {
                            file.transferTo(localFile);
                            System.out.println("已成功上传文件");
                        } catch (IOException e) {
                            return "error";
                        }
                        if("img".equals(type))//如果是图片需要传回预览地址
                            return DOMAIN +  "/static/" + type + "/" + fileName;
                        else if("avatar".equals(type))//如果是头像需要传回相对服务器的地址
                            return "/static/" + type + "/" + fileName;
                        else {//如果是上传附件，保存信息到数据库并返回插入的记录的id
                            Attachment attachment = new Attachment(initUserId(), new Date(), fileName, file.getSize(), path);
                            if (attachmentService.insert(attachment)) {
                                return String.valueOf(attachment.getId());
                            }
                        }
                    }
                }
            }

        }
        return "error";
    }

    @RequestMapping(value = "upload/avatar", method = RequestMethod.POST)
    public
    @ResponseBody
    String avatarUpload(HttpServletRequest request) {
        return upload(request, "avatar");
    }

    @RequestMapping(value = "upload/image", method = RequestMethod.POST)
    public
    @ResponseBody
    String imgUpload(HttpServletRequest request) {
        return upload(request, "img");
    }


    @RequestMapping(value = "upload/file", method = RequestMethod.POST)
    public
    @ResponseBody
    String fileUpload(HttpServletRequest request) {
        return upload(request, "file");
    }


    @RequestMapping(value = "download/{id}")
    public ResponseEntity<byte[]> testResponseEntity(@PathVariable Integer id, HttpSession session,Attachment attachment) throws IOException{
        attachment = attachmentService.selectById(id);
        byte[] body=null;
        ServletContext servletContext=session.getServletContext();

        InputStream in=servletContext.getResourceAsStream(attachment.getPath());
        System.out.println("成功查询文件");
        body=new byte[in.available()];
        in.read(body);

        HttpHeaders headers=new HttpHeaders();
        //响应头的名字和响应头的值
        headers.add("Content-Disposition", "attachment;filename=" + attachment.getName());

        HttpStatus statusCode=HttpStatus.OK;

        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
        return response;
    }

}

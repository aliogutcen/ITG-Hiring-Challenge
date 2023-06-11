package com.ogutcenali.utility;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class ImageUpload {

    public String imageUpload(MultipartFile file) {
        Map config = new HashMap();
        config.put("cloud_name", "doa04qdhh");
        config.put("api_key", "261194321947226");
        config.put("api_secret", "K5_9m33MSDBvu4MZuHhHWeFxNeA");
        Cloudinary cloudinary = new Cloudinary(config);
        try {
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            System.out.println(url + " --------------------------");
            return url;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

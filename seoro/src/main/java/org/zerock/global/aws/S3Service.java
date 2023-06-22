package org.zerock.global.aws;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class S3Service {
	@Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;

    public String uploadFile(MultipartFile multipartFile, Object dir) throws IOException {
        String fileName = multipartFile.getOriginalFilename();

        //파일 형식 구하기
        String ext = fileName.split("\\.")[1];
        String contentType = "";

        //content type을 지정해서 올려주지 않으면 자동으로 "application/octet-stream"으로 고정이 되서 링크 클릭시 웹에서 열리는게 아니라 자동 다운이 시작
        switch (ext) {
            case "jpeg":
                contentType = "image/jpeg";
                break;
            case "png":
                contentType = "image/png";
                break;
            case "svg":
                contentType = "image/svg+xml";
                break;
        }

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            
//            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
//            Date now = new Date();
//            String nowTime1 = sdf1.format(now);
            // 파일 이름이 겹치면 안들어감 -> 경로: dir + 파일 이름 + 현재 시간 + 1~100 랜덤 난수 발생 
            amazonS3.putObject(new PutObjectRequest(bucket, dir + "/" + fileName, multipartFile.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }

        //object 정보 가져오기
        ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(bucket);
        List<S3ObjectSummary> objectSummaries = listObjectsV2Result.getObjectSummaries();
        
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
//        Date now = new Date();
//        String nowTime1 = sdf1.format(now);

        for (S3ObjectSummary object: objectSummaries) {
            System.out.println("object = " + object.toString());
        }
        return amazonS3.getUrl(bucket, dir + "/" + fileName).toString();
    }

}

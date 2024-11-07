package science.examplerediska.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import science.examplerediska.ObjectS3Config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3Service {

    private AmazonS3 s3;
    private final ObjectS3Config s3Config;


    public String uploadFile(String fileName, MultipartFile file) {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(s3Config.getAccessKey(), s3Config.getSecretKey());
        s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withEndpointConfiguration(
                        new AmazonS3ClientBuilder.EndpointConfiguration(
                                "storage.yandexcloud.net","ru-central1"
                        )
                )
                .build();
        try (InputStream inputStream = file.getInputStream()) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            s3.putObject(s3Config.getBucketName(), fileName, inputStream, metadata);
            return getFileUrl(fileName); // Возвращаем URL загруженного файла
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }


    public String getFileUrl(String fileName) {
        URL url = s3.getUrl(s3Config.getBucketName(), fileName);
        return url.toString();
    }
}

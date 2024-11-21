package science.examplerediska.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import science.examplerediska.ObjectS3Config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3Service {

    private AmazonS3 s3;
    private final ObjectS3Config s3Config;

    private AmazonS3 getS3Client() {
        if (s3 == null) {
            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(
                    s3Config.getAccessKey(), s3Config.getSecretKey()
            );
            s3 = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .withEndpointConfiguration(
                            new AmazonS3ClientBuilder.EndpointConfiguration(
                                    "storage.yandexcloud.net", "ru-central1"
                            )
                    )
                    .build();
        }
        return s3;
    }

    public String uploadFile(String fileName, MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            getS3Client().putObject(s3Config.getBucketName(), fileName, inputStream, metadata);
            return getFileUrl(fileName); // Возвращаем URL загруженного файла
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    public ByteArrayOutputStream downloadFile(String fileName) {
        try {
            S3Object s3Object = getS3Client().getObject(s3Config.getBucketName(), fileName);
            InputStream is = s3Object.getObjectContent();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            return outputStream;
        } catch (Exception e) {
            log.error("Error downloading file {}: {}", fileName, e.getMessage());
            throw new RuntimeException("Failed to download file", e);
        }
    }

    public String getFileUrl(String fileName) {
        URL url = getS3Client().getUrl(s3Config.getBucketName(), fileName);
        return url.toString();
    }
}


package aithings.camAI.controlpanel.config.minio;


import aithings.camAI.controlpanel.utils.constant.MinioConstant;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    @Value("${minio.accessToken}")
    private String accessToken;

    @Value("${minio.secrete}")
    private String secrete;

    @Value("${minio.url}")
    private String url;

    @Bean
    public MinioClient connectMinioClient() {
        try {
                MinioClient minioClient = MinioClient
                        .builder()
                        .endpoint(HttpUrl.get(url))
                        .credentials(accessToken,secrete)
                        .build();

                if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(MinioConstant.BUCKET_VIDEO_VIOLATION).build())){
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(MinioConstant.BUCKET_VIDEO_VIOLATION).build());
                }
                if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(MinioConstant.BUCKET_IMAGE_VIOLATION).build())){
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(MinioConstant.BUCKET_IMAGE_VIOLATION).build());
                }
                if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(MinioConstant.BUCKET_IMAGE_LICENSEPLATE_VIOLATION).build())){
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(MinioConstant.BUCKET_IMAGE_LICENSEPLATE_VIOLATION).build());
                }
                return minioClient;
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}

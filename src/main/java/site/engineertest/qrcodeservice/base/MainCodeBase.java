package site.engineertest.qrcodeservice.base;

public interface MainCodeBase {

    byte[] getNewQRCode(String url, int width, int height);
}

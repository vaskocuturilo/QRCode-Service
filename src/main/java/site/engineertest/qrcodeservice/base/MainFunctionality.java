package site.engineertest.qrcodeservice.base;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class MainFunctionality {

    public static void createQACode(String url, int width, int height, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height);
        } catch (WriterException exception) {
            throw new WriterException("Can't encode " + exception.getMessage());
        }

        Path path = FileSystems.getDefault().getPath(filePath);
        try {
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (IOException exception) {
            throw new IOException("Can't writeToPath " + exception.getMessage());
        }
    }

    public static byte[] getNewQRCode(String url, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height);
        } catch (WriterException exception) {
            throw new WriterException("Can't encode " + exception.getMessage());
        }

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig con = new MatrixToImageConfig(0xFF000002, 0xFFFFC041);

        try {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
        } catch (IOException exception) {
            throw new IOException("Can't writeToPath " + exception.getMessage());
        }

        byte[] pngData = pngOutputStream.toByteArray();

        return pngData;
    }
}

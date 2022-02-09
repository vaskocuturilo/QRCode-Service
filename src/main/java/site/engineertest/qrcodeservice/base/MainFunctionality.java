package site.engineertest.qrcodeservice.base;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainFunctionality implements MainCodeBase {
    @Override
    public byte[] getNewQRCode(String url, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height);
        } catch (WriterException exception) {
            throw new RuntimeException("Can't encode " + exception.getMessage());
        }
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig con = new MatrixToImageConfig(0xFF000002, 0xFFFFFF);
        try {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
        } catch (IOException exception) {
            throw new RuntimeException("Can't writeToPath " + exception.getMessage());
        }
        byte[] pngData = pngOutputStream.toByteArray();

        return pngData;
    }
}

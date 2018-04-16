package fr.husta.test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class QRCodeEncoderTest {

    public static final String BLACK_SQUARE_HALF_DOWN = "\u2584"; // ▄
    public static final String BLACK_SQUARE_HALF_UP = "\u2580"; // ▀
    public static final String BLACK_SQUARE_FULL = "\u2588"; // █
    public static final String WHITE_SQUARE = "\u00A0"; // ' '

    @Test
    public void testDisplayUnicode() {
        System.out.println("BLACK DOWN : [" + BLACK_SQUARE_HALF_DOWN + "]");
        System.out.println("BLACK UP   : [" + BLACK_SQUARE_HALF_UP + "]");
        System.out.println("BLACK FULL : [" + BLACK_SQUARE_FULL + "]");
        System.out.println("WHITE      : [" + WHITE_SQUARE + "]");
    }

    @Test
    public void testTextGeneration() throws WriterException {
        QRCode qrCode = null;

        // ErrorCorrectionLevel = High
        qrCode = Encoder.encode("Hello", ErrorCorrectionLevel.H);
        System.out.println(qrCode);

        // ErrorCorrectionLevel = Low
        qrCode = Encoder.encode("Hello", ErrorCorrectionLevel.L);
        System.out.println(qrCode);
    }

    @Test
    public void testQRCodeWriter() throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode("Hello", BarcodeFormat.QR_CODE, 20, 20);
        System.out.println(bitMatrix);

        System.out.println(bitMatrix.toString(BLACK_SQUARE_FULL + " ", WHITE_SQUARE + " "));

        System.out.println(bitMatrix.toString(BLACK_SQUARE_FULL + BLACK_SQUARE_FULL, WHITE_SQUARE + WHITE_SQUARE));

        System.out.println(bitMatrix.toString(BLACK_SQUARE_FULL, WHITE_SQUARE));
    }

    @Test
    public void testTextGenerationUTF_8() throws WriterException {
        QRCode qrCode = null;

        // EncodeHintType.CHARACTER_SET = UTF-8
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        qrCode = Encoder.encode("Hello", ErrorCorrectionLevel.H, hints);
        System.out.println(qrCode);
    }

}
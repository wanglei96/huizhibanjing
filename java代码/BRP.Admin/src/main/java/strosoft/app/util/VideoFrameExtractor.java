package strosoft.app.util;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class VideoFrameExtractor {
    /**
     * 提取视频帧
     *
     * @param videoFilePath
     * @param outputImagePath
     * @throws IOException
     * @throws JCodecException
     */
    public static void extract(String videoFilePath, String outputImagePath, int frameIndex) throws IOException, JCodecException {
        Picture picture = FrameGrab.getFrameFromFile(new File(videoFilePath), frameIndex);
        if (picture != null) {
            BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
            ImageIO.write(bufferedImage, "jpg", new File(outputImagePath));
        }
    }

    /**
     * 提取视频帧
     * @param videoFilePath
     * @param outputImagePath
     * @throws IOException
     * @throws JCodecException
     */
    public static void extract(String videoFilePath, String outputImagePath) throws IOException, JCodecException {
        extract(videoFilePath, outputImagePath, 0);
    }

    public static void main(String[] args) throws JCodecException, IOException {
        String videoFilePath = "D:\\Temp\\test2.mp4";
        String outputImagePath = "D:\\Temp\\test2.jpg";
        extract(videoFilePath, outputImagePath);
    }
}
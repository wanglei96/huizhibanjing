package strosoft.app.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.util.DefaultXmlPrettyPrinter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class XmlJsonConverter {
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();


    /**
     * 将JSON字符串转换为XML
     *
     * @param json input string
     * @return XML string
     * @throws Exception
     */
    public static String fromJsonToXml(String json) throws Exception {
        Object root = jsonMapper.readValue(json, Object.class);
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        strBuilder.append(xmlMapper.writer(new DefaultXmlPrettyPrinter()).writeValueAsString(root));
        return strBuilder.toString();
    }

    /**
     * 将JSON数据流转换为XML
     *
     * @param jsonStream inputStream
     * @return XML string
     * @throws Exception
     */
    public static String fromJsonToXml(InputStream jsonStream) throws Exception {
        Object root = jsonMapper.readValue(jsonStream, Object.class);
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        strBuilder.append(xmlMapper.writer(new DefaultXmlPrettyPrinter()).writeValueAsString(root));
        jsonStream.close();
        return strBuilder.toString();
    }

    /**
     * 将JSON 文件转换为XML。直接保存到文件
     *
     * @param inputPath  输入文件路径
     * @param outputPath 输出文件路径
     * @throws Exception
     * @apiNote Example: Path inputPath = Paths.get("/home/sample.json");
     */
    public static void fromJsonToXmlFile(Path inputPath, Path outputPath) throws Exception {
        //AUTO CLOSED writer & reader
        try (BufferedReader br = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8);
             BufferedWriter bw = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)
        ) {
            Object root = jsonMapper.readValue(br, Object.class);
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            xmlMapper.writer(new DefaultXmlPrettyPrinter()).writeValue(bw, root);
        }

    }

    /**
     * 将XML字符串转换为 JSON
     *
     * @param xml input XML string
     * @return json string
     * @throws Exception
     */
    public static String fromXmlToJson(String xml) throws Exception {
        Object root = xmlMapper.readValue(xml, Object.class);
        String outputString = jsonMapper.writeValueAsString(root);
        return outputString;
    }

    /**
     * 将XML数据流转换为JSON
     *
     * @param xmlStream XML InputStream
     * @return json string
     * @throws Exception
     */
    public static String fromXmlToJson(InputStream xmlStream) throws Exception {
        Object root = xmlMapper.readValue(xmlStream, Object.class);
        String outputString = jsonMapper.writeValueAsString(root);
        xmlStream.close();
        return outputString;
    }

    /**
     * 将XML文件转换为JSON。直接保存到文件
     *
     * @param inputPath  传入数据流.
     * @param outputPath 传出数据流
     * @throws Exception
     */
    public static void fromXmlToJsonFile(Path inputPath, Path outputPath) throws Exception {
        try (BufferedReader br = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8);
             BufferedWriter bw = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)
        ) {
            Object root = xmlMapper.readValue(br, Object.class);
            jsonMapper.writerWithDefaultPrettyPrinter().writeValue(bw, root);
        }
        //^AUTO CLOSED writer & reader
    }


}

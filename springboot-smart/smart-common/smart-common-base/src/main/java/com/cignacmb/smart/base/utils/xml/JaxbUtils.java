package com.cignacmb.smart.base.utils.xml;

import com.cignacmb.smart.base.utils.basic.CharsetUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class JaxbUtils {
    /**
     * JavaBean转换成xml 默认编码UTF-8
     *
     * @param obj
     * @return
     */
    public static String convert2Xml(Object obj) {
        return convert2Xml(obj, CharsetUtils.UTF_8);
    }

    /**
     * JavaBean转换成xml
     *
     * @param obj
     * @param encoding
     * @return
     */
    private static String convert2Xml(Object obj, String encoding) {
        String result = "";
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();//.replaceAll(" standalone=\"yes\"", "")
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * xml转换成JavaBean
     *
     * @param xml
     * @param c
     * @return
     */
    public static <T> T convert2Bean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;

    }
}
